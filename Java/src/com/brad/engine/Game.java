package com.brad.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game{

    public static Display display;
    public static Scene scene;

    public Game(){
        display = new Display(80, 20);
        scene = new Scene();

        SceneObject cube = new SceneObject(new Pixel[]{
                new Pixel(0, 0, 15), new Pixel(0, 0, 10), new Pixel(15, 0, 10), new Pixel(15, 0, 15),
                new Pixel(15, 15, 15), new Pixel(15, 15, 10),
                new Pixel(0, 15, 10),new Pixel(0, 15, 15)
        }, "Some obj");
        scene.addObjects(new SceneObject[]{cube});
        //display.center = cube.center;
        display.Update(scene);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = "";

        while (line.equalsIgnoreCase("quit") == false) {
            try{
                line = in.readLine();

            } catch (IOException e){

            }

            if(line.equalsIgnoreCase("d")){
                cube.move(new Pixel(0, 2, 0));
            }
            if(line.equalsIgnoreCase("a")){
                cube.move(new Pixel(0, 2, 0));
            }

            display.Update(scene);
            //do something
        }

        try{
            in.close();
        } catch (IOException e){

        }
    }

}
