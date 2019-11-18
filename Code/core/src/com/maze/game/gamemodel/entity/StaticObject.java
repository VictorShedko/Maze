package com.maze.game.gamemodel.entity;

public class StaticObject extends GameObject {
    boolean isTraversable;
    boolean isCrystalline;

    public boolean isTraversable() {
        return isTraversable;
    }

    @Override
    public void makeInvisible() {
        this.status = 1;
    }

    @Override
    public void makeVisible() {
        this.status = 2;
    }

    public boolean isCrystalline() {
        return isCrystalline;
    }

    public int isCrystallineAsInt() {
        if (isCrystalline) return 1;
        return 0;
    }

    public StaticObject(int objectId, Point position, int status, boolean isTraversable, boolean isCrystalline) {
        super(objectId, position, status);
        this.isTraversable = isTraversable;
        this.isCrystalline = isCrystalline;
    }
}
