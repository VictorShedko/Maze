package com.maze.game.entity;

import com.maze.game.MazeEvent;

public class UsableObject extends StaticObject {
    private MazeEvent event;
   private int rotationAngle;

    public UsableObject(int objectId, Point position, int status, boolean isTraversable,boolean isCristallize, MazeEvent event,int rotationAngle) {
        super(objectId, position, status, isTraversable,isCristallize);
        this.rotationAngle=rotationAngle;
        this.event = event;
    }
}
