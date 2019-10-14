package com.brad.engine;

public class Pixel {

    /*

    X = left right
    Y = up down
    Z = back forwards

     */


    public int x;
    public int y;
    public int z;
    public String value;

    public Pixel(int x, int y, int z){
        this.x = x;
        this.y = y;
        this.z = z;
        this.value = Display.pixelChar;
    }

    public void add(Pixel pixel){
        this.x += pixel.x;
        this.y += pixel.y;
        this.z += pixel.z;
    }

    public static Pixel midPoint(Pixel[] pixels){
        int x = 0;
        int y = 0;
        int z = 0;

        for(Pixel pixel : pixels){
            x += pixel.x;
            y += pixel.y;
            z += pixel.z;
        }
        return new Pixel(x/pixels.length, y/pixels.length, z/pixels.length);
    }

    public String print(){
        return x + ", " + y +", " + z;
    }

    public static double length(Pixel from, Pixel to){
        return Math.sqrt(((to.x*to.x)-(from.x*from.x))+((to.y*to.y)-(from.y*from.y))+((to.z*to.z)-(from.z*from.z)));
    }

}
