package com.maze.game;

import com.maze.game.entity.GameObject;
import com.maze.game.entity.Human;
import com.maze.game.entity.StaticObject;

import java.util.Map;

public class PlayField {

    private Map<Point, StaticObject> gameObjects;
    private Human human;

    public void addHuman(Human human) {
        this.human = human;
    }

    public Human getHuman() {
        return human;
    }

    public boolean addObjectToField(StaticObject obj){
        gameObjects.put(obj.getPosition(),obj);
        return true;
    }
    public StaticObject getObjectByKey(Point point){

        return gameObjects.get(point);
    }

    public Map<Point, StaticObject> getGameObjects() {
        return gameObjects;
    }

    public PlayField(Map<Point, StaticObject> gameObjects) {
        this.gameObjects = gameObjects;
    }
}
