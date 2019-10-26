package com.maze.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.maze.game.TextureStorage;

public abstract class GameObject extends Actor {
    protected int objectId;
    protected int status;//0- never seen before,1- has been seen but now out of range 2 in vision
    protected Point position;

    public int getObjectId() {
        return objectId;
    }

    public Point getPosition() {
        return position;
    }
    public abstract void makeVisible() ;
    public abstract void makeInvisible();
    public void draw(Batch batch, TextureStorage store){
        batch.draw(store.getTextureBank(objectId,status),position.getX()*32f,position.getY()*32f);
        if(status==1)this.setVisible(false);
    }
    public GameObject(int objectId, Point position, int status) {
        this.objectId = objectId;
        this.position =position;
        this.status=status;
    }
}
