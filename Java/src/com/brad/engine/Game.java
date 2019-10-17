package com.brad.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game{

    public static Display display;
    public static Scene scene;

    public Game(){
        display = new Display(500, 500);
        scene = new Scene();

        SceneObject cube = new SceneObject(new Pixel[]{
                new Pixel(0, 0, 150), new Pixel(0, 0, 100), new Pixel(150, 0, 100), new Pixel(150, 0, 150),
                new Pixel(150, 150, 150), new Pixel(150, 150, 100),
                new Pixel(0, 150, 150),new Pixel(0, 150, 150)
        }, "Some obj");

        SceneObject smallCube = new SceneObject(new Pixel[]{
                new Pixel(0, 0, 60), new Pixel(0, 0, 40), new Pixel(60, 0, 40), new Pixel(60, 0, 60),
                new Pixel(60, 60, 60), new Pixel(60, 60, 40),
                new Pixel(0, 60, 60),new Pixel(0, 60, 60)
        }, "Some obj");
        //SceneObject line = new SceneObject(new Pixel[]{new Pixel(0, 0, 0), new Pixel(100, 100, 100)}, "line");

        cube.move(new Pixel(500/2-75, 500/2-75, 0));
        //smallCube.move(new Pixel(cube.center.x+200, cube.center.y-150, cube.center.z));
        scene.addObjects(new SceneObject[]{cube});
        //display.center = cube.center;
        display.Update(scene);
    }
}
