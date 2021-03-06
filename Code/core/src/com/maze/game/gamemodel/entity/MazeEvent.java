package com.maze.game.gamemodel.entity;

import java.util.function.Predicate;

public class MazeEvent {
    int eventStatus;
    Point position;
    Predicate<Point> condition;
    public int getEventStatus() {
        return eventStatus;
    }

    public boolean check() {
        if(condition.test(position)){
            eventStatus=2;
            return true;
        }else {
            eventStatus=1;
            return false;
        }
    }

    public MazeEvent(int eventStatus,Point position,Predicate<Point> condition) {
        this.eventStatus = eventStatus;
        this.position=position;
        this.condition = condition;
    }

}
