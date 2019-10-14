package com.brad.engine;

import java.util.ArrayList;

public class Display {

    private int width;
    private int height;
    public static String pixelChar;
    private Pixel[][] pixels;

    public Display(int w, int h){
        this.width = w;
        this.height = h;
        pixelChar = "██";//"▒";
    }

    public void Update(Scene scene){
        createInitPixels();
        ArrayList<SceneObject> objects = scene.objects;
        for(SceneObject obj : objects){
            if(obj.outline.size() == 0 || obj.updatePixels == true){
                outLinePixels(obj);
            }
            ArrayList<Pixel> outline = obj.outline;//outLinePixels(obj);
            for(Pixel pixel : outline){
                if(pixel.x < width && pixel.y < height){

                    pixels[pixel.y][pixel.x].value = "\u001B[32m"+"██"+"\u001B[0m";//▓
                }
            }
            if(obj.inner.size() == 0 || obj.updatePixels == true){
                innerPixels(obj.outline, obj);
                obj.updatePixels = false;
            }
            ArrayList<Pixel> inner = obj.inner;//innerPixels(outline, obj);
            for(Pixel pixel : inner){
                if(pixel.x < width && pixel.y < height){
                    //System.out.println(pixel.x + " " + pixel.y + "    " + width + " " + height + "        " + pixels.length + " " + pixels[0].length);
                    pixels[pixel.y][pixel.x].value = "\u001B[31m"+"██"+"\u001B[0m";
                }
            }
        }

        drawPixels(false);
    }

    private void drawPixels(boolean colored){
        clearScreen();
        for(Pixel[] row : pixels){
            String tempRow = "";
            for(Pixel pixel : row){
                if(colored == true){
                    tempRow += pixel.value; //"\x1b[37a"+this.char+"\x1b[0m"
                } else{
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
                pixels[x][y] = new Pixel(x, y);
            }
        }
    }

    private void outLinePixels(SceneObject obj){

        ArrayList<Pixel> outLinePixels = new ArrayList<Pixel>();
        Pixel[] points = obj.points;
        for(int i = 0; i < points.length; i++){
            int add = 0;
            Pixel start = points[i];
            Pixel end;
            if(i == points.length-1){
                //System.out.println("Last");
                start = points[0];
                end = points[i];

            } else{
                end = points[i+1];
            }

            if(end.x < start.x){
                start = points[i+1];
                end = points[i];
            }

            if(i == points.length-2){
                add = 1;
            }

            double length = Math.sqrt(((end.x*end.x)-(start.x*start.x))+((end.y*end.y)-(start.y*start.y)));
            int len = 0;
            for(int z = 0; z < Math.abs(length)+add; z++){
                double t = z/length;
                int x = (int)Math.round((1-t)*start.x+t*end.x);
                int y = (int)Math.round((1-t)*start.y+t*end.y);
                Pixel pixel = new Pixel(x, y);

                if(isPixelValid(pixel, outLinePixels)) {
                    outLinePixels.add(pixel);
                    //System.out.print(" added");
                    len++;
                }
                //System.out.println(i + " | " + z + " x: " + x + " y: " + y);
            }
            //System.out.println(i + "/" + (points.length-1) +" Start: " + start.x + " " + start.y + " End: " + end.x + " " + end.y + " outline len: " + len + " line len: " + length);
        }
        obj.outline = outLinePixels;
    }

    private void innerPixels(ArrayList<Pixel> outline, SceneObject obj){
        ArrayList<Pixel> innerPixels = new ArrayList<Pixel>();
        System.out.println(obj.maxBounds.x * obj.maxBounds.y);
        Pixel start = obj.center;
        innerPixels.add(start);

        for(int i = 0; i < innerPixels.size(); i++){
            Pixel point = innerPixels.get(i);

            if(arrayIncludes(outline, point) == false){
                //innerPixels.add(point);
                Pixel n = new Pixel(point.x, point.y-1);
                Pixel s = new Pixel(point.x, point.y+1);
                Pixel e = new Pixel(point.x+1, point.y);
                Pixel w = new Pixel(point.x-1, point.y);

                if(isPixelValid(n, innerPixels) && isPixelValid(n, outline)){
                    innerPixels.add(n);
                    //System.out.println("N: " + n);
                }
                if(isPixelValid(s, innerPixels) && isPixelValid(s, outline)){
                    innerPixels.add(s);
                    //System.out.println("S: " + s);
                }
                if(isPixelValid(e, innerPixels) && isPixelValid(e, outline)){
                    innerPixels.add(e);
                    //System.out.println("E: " + e);
                }
                if(isPixelValid(w, innerPixels) && isPixelValid(w, outline)){
                    innerPixels.add(w);
                    //System.out.println("W: " + w);
                }
            }

            if(innerPixels.size() >= 500){
                System.out.println("Reached limit");
                break;
            }
        }
        obj.inner = innerPixels;
        //return innerPixels;
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