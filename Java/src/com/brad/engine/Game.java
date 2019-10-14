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

        SceneObject obj = new SceneObject(new Pixel[]{new Pixel(2, 2), new Pixel(10, 2), new Pixel(10, 10), new Pixel(2, 10)}, "Some obj");
        SceneObject something = new SceneObject(new Pixel[]{new Pixel(50, 1), new Pixel(58, 1), new Pixel(58, 18), new Pixel(50, 18)}, "something");
        SceneObject tri = new SceneObject(new Pixel[]{new Pixel(12, 2), new Pixel(17, 8), new Pixel(30, 0), new Pixel(30, 15), new Pixel(12, 15)}, "Tri");
        scene.addObjects(new SceneObject[]{tri, obj, something});

        display.Update(scene);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line = "";

        while (line.equalsIgnoreCase("quit") == false) {
            try{
                line = in.readLine();

            } catch (IOException e){

            }
            if(line.equalsIgnoreCase("w")){
                obj.move(new Pixel(0, -2));
            }
            if(line.equalsIgnoreCase("a")){
                obj.move(new Pixel(-2, 0));
            }
            if(line.equalsIgnoreCase("s")){
                obj.move(new Pixel(0, 2));
            }
            if(line.equalsIgnoreCase("d")){
                obj.move(new Pixel(2, 0));
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
