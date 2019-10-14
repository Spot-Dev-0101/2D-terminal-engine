package com.brad.engine;

import java.util.ArrayList;

public class SceneObject {

    public Pixel[] points;
    public String name;
    public Pixel center;
    public Pixel maxBounds;
    public ArrayList<Pixel> outline = new ArrayList<Pixel>();
    public ArrayList<Pixel> inner = new ArrayList<Pixel>();
    public boolean updatePixels = true;

    public SceneObject(Pixel[] points, String name){
        this.points = points;
        this.name = name;
        this.center = Pixel.midPoint(points);
        this.maxBounds = max();

    }

    private Pixel max(){
        int maxX = 0;
        int maxY = 0;

        for(Pixel pixel : this.points){
            if(pixel.x > maxX){
                maxX = pixel.x;
            }
            if(pixel.y > maxY) {
                maxY = pixel.y;
            }
        }
        return new Pixel(maxX, maxY);
    }

    public void move(Pixel dir){
        for(Pixel pixel : this.points){
            pixel.add(dir);
            this.center = Pixel.midPoint(points);
            this.updatePixels = true;
        }
    }
}
