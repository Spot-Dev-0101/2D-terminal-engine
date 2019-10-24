package com.brad.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game{

    public static Display display;
    public static Scene scene;
    public static int height = 500;
    public static int width = 500;

    public Game(){
        display = new Display(width, height);
        scene = new Scene();

        SceneObject cube = new SceneObject(new Pixel[]{
                new Pixel(0, 0, 150), new Pixel(0, 0, 100), new Pixel(150, 0, 100), new Pixel(150, 0, 150),
                new Pixel(150, 150, 150), new Pixel(150, 150, 100),
                new Pixel(0, 150, 150),new Pixel(0, 150, 150)
        }, "Some obj");

        SceneObject ball = new SceneObject(new Pixel[]{
                new Pixel(5, 5, 10), new Pixel(10, 5, 5), new Pixel(5, 5, 0),
                new Pixel(0, 5, 5), new Pixel(5, 5, 10), new Pixel(5, 0, 5),
                new Pixel(5, 5, 0), new Pixel(5, 10, 5), new Pixel(5, 5, 10)
        }, "Ball");

        //SceneObject line = new SceneObject(new Pixel[]{new Pixel(0, 0, 0), new Pixel(100, 100, 100)}, "line");
        //cube.scale(new Pixel(20, 20, 20));
        //cube.move(new Pixel(500/2-75, 500/2-75, 0));
        //cube.center.add(Display.center);
        //smallCube.move(new Pixel(cube.center.x+200, cube.center.y-150, cube.center.z));
        scene.addObjects(new SceneObject[]{cube});
        display.center = new Pixel(0, 0, -100);
        display.Update(scene);

        Display.frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(e.getX() + " " + e.getY());
                display.center = new Pixel(e.getX(), e.getY(), -100);
                //cube.move(new Pixel(-10, -10, 0));
                Display.frame.repaint();
                display.Update(scene);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            }
            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
}
