package com.brad.engine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Display {

    private int width;
    private int height;
    public static String pixelChar;
    private Pixel[][] pixels;
    public static Pixel center;
    public static JFrame frame  = new JFrame("WINDOW");
    public int frameCount = 0;
    private BufferedImage image;


    public Display(int w, int h){
        this.width = w;
        this.height = h;
        this.center = new Pixel(h/2, w/2, -100);
        pixelChar = "██";//"▒";
        image = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);

    }


    public void Update(Scene scene){
        createInitPixels();
        for(SceneObject object : scene.objects){
            ArrayList<Pixel> converted = convert(outlinePathCoordinates(object));

            for(Pixel pixel : converted){
                if(isPixelInBounds(pixel)){
                    pixels[pixel.x][pixel.y].value = pixel.value;//Color.red.getRGB();//"color";//"\u001B[30m"+pixelChar+"\u001B[0m";
                }

            }

        }
        drawPixelsWindow();
        //drawPixelsTerminal();
    }

    private void drawPixelsWindow(){
        //frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        //frame  = new JFrame("WINDOW");

        for(Pixel[] row : pixels){
            String tempRow = "";
            for(Pixel pixel : row) {
                if(isPixelInBounds(pixel)){
                    image.setRGB(pixel.x, pixel.y, pixel.value);
                }
            }
        }

        frame.setVisible(true);
        frame.add(new JLabel(new ImageIcon(image)));
        frame.pack();
        // Better to DISPOSE than EXIT
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //frame.repaint();
        frameCount++;
        System.out.println(frameCount);
    }

    private void drawPixelsTerminal(){
        clearScreen();
        for(Pixel[] row : pixels){
            String tempRow = "";
            for(Pixel pixel : row){
                //System.out.print(pixel.print() + " =-= ");
                if(pixel.x == center.x && pixel.y == center.y){
                    tempRow += "\u001B[31m"+pixel.value+"\u001B[0m";
                } else {
                    tempRow += pixel.value; //"\x1b[37a"+this.char+"\x1b[0m"
                }

            }
            System.out.println(tempRow);
        }
    }

    private void createInitPixels(){
        pixels = new Pixel[height][width];

        for (int x = 0; x < height; x++){
            for (int y = 0; y < width; y++){
                pixels[x][y] = new Pixel(x, y, 0);
                pixels[x][y].value = Color.gray.getRGB();
            }
        }
    }


    private ArrayList<Pixel> convert(ArrayList<Pixel> outline){
        ArrayList<Pixel> screenCoord = new ArrayList<>();

        for(int i = 0; i < outline.size(); i++){
            Pixel point = outline.get(i);
            float z = ((float)Math.abs(center.z))/((float)point.z);
            int x = Math.round(point.x * z);//Math.round((((float)Math.abs(center.x))/((float)point.x)*z)*100);
            int y = Math.round(point.y * z);
            Pixel coord = new Pixel(x, y, 0);
            coord.value = Color.green.getRGB();

            //System.out.println((Math.abs(center.z)/point.z));
            if(isPixelInBounds(coord)){
                //System.out.println(x + " " + y + " -=- " + point.x + "  " + ((float)Math.abs(center.z))/((float)point.z));

                screenCoord.add(coord);
            }


        }

        return screenCoord;
    }

    private ArrayList<Pixel> outlinePathCoordinates(SceneObject obj){
        ArrayList<Pixel> coordiantes = new ArrayList<>();

        Pixel[] points = obj.points;

        for(int i = 0; i < points.length; i++){


            Pixel start = points[i];
            Pixel end;
            if(i >= points.length-1){
                start = points[0];
                end = points[i];
            } else{
                end = points[i+1];
            }


            if(end.x < start.x){
                start = points[i+1];
                end = points[i];
            }

            if(start.z > end.z){
                Pixel tempStart = start;
                start = end;
                end = tempStart;
            }

            double length = Math.sqrt(((end.x*end.x)-(start.x*start.x))+((end.y*end.y)-(start.y*start.y))+((end.z*end.z)-(start.z*start.z)));
            //System.out.println(Math.sqrt((end.x*end.x)-(start.x*start.x)) + " " + Math.sqrt((end.y*end.y)-(start.y*start.y)) + " " +Math.abs((end.z*end.z)-(start.z*start.z)));
            //System.out.println("From: " + start.print() + " \nTo: " + end.print() + "\nLength: " + length + "\ncoords:\n\n ");
            for(int a = 0; a < Math.abs(length); a++){
                double t = a/length;
                int x = (int)Math.round((1-t)*start.x+t*end.x);
                int y = (int)Math.round((1-t)*start.y+t*end.y);
                int z = (int)Math.round((1-t)*start.z+t*end.z);
                coordiantes.add(new Pixel(x, y, z));
            }

        }

        return coordiantes;//new ArrayList<Pixel>(Arrays.asList(obj.points));
    }

    private boolean isPixelInBounds(Pixel p){
        if(p.x >= 0 && p.x < width-1 && p.y >= 0 && p.y <= height-1){
            return true;
        }
        return false;
    }

    private boolean isPixelValid(Pixel pixel, ArrayList<Pixel> array){
        if(arrayIncludes(array, pixel) == false && pixel.x >= 0 && pixel.y >= 0){
            return true;
        }
        return false;
    }

    public static boolean arrayIncludes(ArrayList<Pixel> array, Pixel item){
        for(Pixel pixel : array){
            if(pixel.x == item.x && pixel.y == item.y){
                return true;
            }
        }
        return false;
    }


    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}