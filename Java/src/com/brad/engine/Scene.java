package com.brad.engine;

import java.util.ArrayList;

public class Scene {

    public ArrayList<SceneObject> objects = new ArrayList<SceneObject>();

    public Scene(){

    }

    public void addObjects(SceneObject[] objs){
        for(SceneObject obj : objs){
            objects.add(obj);
        }
    }

}
