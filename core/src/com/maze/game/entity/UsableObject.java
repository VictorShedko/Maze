package com.maze.game.entity;

import com.maze.game.MazeEvent;
import com.maze.game.Point;

public class UsableObject extends StaticObject {
    private MazeEvent event;
   private int rotationAngle;

    public UsableObject(int objectId, Point position, int status, boolean isTraversable, MazeEvent event,int rotationAngle) {
        super(objectId, position, status, isTraversable);
        this.rotationAngle=rotationAngle;
        this.event = event;
    }
}
