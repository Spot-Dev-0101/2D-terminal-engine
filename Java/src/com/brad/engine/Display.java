package com.brad.engine;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Display {

    private int width;
    private int height;
    public static String pixelChar;
    private Pixel[][] pixels;
    public Pixel center;


    public Display(int w, int h){
        this.width = w;
        this.height = h;
        this.center = new Pixel(h/2, w/2, -100);
        pixelChar = "██";//"▒";
    }

    public void Update(Scene scene){
        createInitPixels();
        for(SceneObject object : scene.objects){
            ArrayList<Pixel> converted = convert(outlineCoordinates(object));

            for(Pixel pixel : converted){
                pixels[pixel.x][pixel.y].value = "\u001B[30m"+pixelChar+"\u001B[0m";
            }

        }
        drawPixels();
    }

    private void drawPixels(){
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
            }
        }
    }

    private ArrayList<Pixel> convert(ArrayList<Pixel> outline){
        ArrayList<Pixel> screenCoord = new ArrayList<>();

        for(int i = 0; i < outline.size(); i++){


            Pixel target = outline.get(i);

            double length = Math.sqrt(Math.abs((center.x*center.x)-(target.x*target.x))+Math.abs((center.y*center.y)-(target.y*target.y))+Math.abs((center.z*center.z)-(target.z*target.z)));

            //System.out.println();
            //System.out.println(i + " " +length);

            for(int a = 0; a < Math.abs(length); a++){
                double t = a/length;
                int x = (int)Math.round((1-t)*target.x+t*center.x);
                int y = (int)Math.round((1-t)*target.y+t*center.y);
                int z = (int)Math.round((1-t)*target.z+t*center.z);
                //coordiantes.add(new Pixel(x, y, z));
                if(isPixelValid(new Pixel(x, y, z), screenCoord) && isPixelValid(new Pixel(x, y, z), outline) && Math.round(z) == Math.round(center.z-z)){
                    screenCoord.add(new Pixel(x, y, z));
                    System.out.println("X: " + x + " Y: " + y + " Z: " + z);
                }

            }

        }

        return screenCoord;
    }

    private ArrayList<Pixel> outlineCoordinates(SceneObject obj){
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

            double length = Math.sqrt(((end.x*end.x)-(start.x*start.x))+((end.y*end.y)-(start.y*start.y))+((end.z*end.z)-(start.z*start.z)));
            //System.out.println(Math.sqrt((end.x*end.x)-(start.x*start.x)) + " " + Math.sqrt((end.y*end.y)-(start.y*start.y)) + " " +Math.abs((end.z*end.z)-(start.z*start.z)));
            //ystem.out.println("From: " + start.print() + " \nTo: " + end.print() + "\nLength: " + length + "\ncoords:\n\n ");

            for(int a = 0; a < Math.abs(length); a++){
                double t = a/length;
                int x = (int)Math.round((1-t)*start.x+t*end.x);
                int y = (int)Math.round((1-t)*start.y+t*end.y);
                int z = (int)Math.round((1-t)*start.z+t*end.z);
                coordiantes.add(new Pixel(x, y, z));
                //System.out.println("X: " + x + " Y: " + y + " Z: " + z);
            }




        }

        return coordiantes;
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