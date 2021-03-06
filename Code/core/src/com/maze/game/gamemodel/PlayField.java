package com.maze.game.gamemodel;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.maze.game.gamemodel.entity.playrs.Human;
import com.maze.game.gamemodel.entity.playrs.Monster;
import com.maze.game.gamemodel.entity.Point;
import com.maze.game.gamemodel.entity.StaticObject;


import java.util.HashMap;
import java.util.Map;

public class PlayField {
    private final int size;
    private Map<Point, StaticObject> gameObjects;
    private Human human;
    private Monster monster;
    private TextureStorage textureStorage;
    public void addHuman(Human human) {
        this.human = human;
    }
    public void draw(Batch batch){

        gameObjects.values().forEach(t->t.draw(batch,textureStorage));
      human.draw(batch,textureStorage);
      monster.draw(batch,textureStorage);
    }

    public Human getHuman() {
        return human;
    }

    public Monster getMonster() {
        return monster;
    }

    public void addMonster(Monster monster) {
        this.monster= monster;
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

    public PlayField(int size, TextureStorage storage) {
        this.size = size;
        this.gameObjects=new HashMap<>();
        this.textureStorage=storage;
    }
    public void refreshSteps(int side){
        if(side==0){
           human.refreshSteps();
        }else {
            monster.refreshSteps();
        }


    }

}
