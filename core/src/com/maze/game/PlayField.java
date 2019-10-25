package com.maze.game;

import com.maze.game.entity.Human;
import com.maze.game.entity.Monster;
import com.maze.game.entity.Point;
import com.maze.game.entity.StaticObject;

import java.util.Map;

public class PlayField {
    private final int size;
    private Map<Point, StaticObject> gameObjects;
    private Human human;
    private Monster monster;
    public void addHuman(Human human) {
        this.human = human;
    }

    public Human getHuman() {
        return human;
    }

    public Monster getMonster() {
        return monster;
    }

    public void addMonster(Monster monster) {
        this.monster = monster;
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

    public int getSize() {
        return size;
    }

    public PlayField(int size) {
        this.size = size;
    }
}
