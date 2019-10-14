package com.brad.engine;

public class Pixel {

    public int x;
    public int y;
    public String value;

    public Pixel(int x, int y){
        this.x = x;
        this.y = y;
        this.value = Display.pixelChar;
    }

    public void add(Pixel pixel){
        this.x += pixel.x;
        this.y += pixel.y;
    }

    public static Pixel midPoint(Pixel[] pixels){
        int x = 0;
        int y = 0;

        for(Pixel pixel : pixels){
            x += pixel.x;
            y += pixel.y;
        }
        return new Pixel(x/pixels.length, y/pixels.length);
    }

}
