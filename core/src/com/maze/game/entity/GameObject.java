package com.maze.game.entity;

import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class GameObject extends Actor {
    protected int objectId;
    protected int status;//0- never seen before,1- has been seen but now out of
    protected Point position;

    public Point getPosition() {
        return position;
    }
    public abstract void makeVisible();
    public abstract void makeInvisible();
    public GameObject(int objectId, Point position, int status) {
        this.objectId = objectId;
        this.position =position;
        this.status=status;
    }
}
