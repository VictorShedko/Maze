package com.maze.game.gamemodel.entity;

import java.util.ArrayList;
import java.util.List;

public class UsableObject extends StaticObject {
    private List<MazeEvent> events=new ArrayList<>();
    private int rotationAngle;

    public void addEvent(MazeEvent event){
            events.add(event);

    }
    public UsableObject(int objectId, Point position, int status, boolean isTraversable, boolean isCristallize, MazeEvent event, int rotationAngle) {
        super(objectId, position, status, isTraversable, isCristallize);
        this.rotationAngle = rotationAngle;
    }

}
