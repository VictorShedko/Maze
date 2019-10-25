package com.maze.game.fabric;

import com.maze.game.entity.Point;

import java.io.Serializable;

public class Instruction implements Serializable {
int type;
Point p;
boolean travable;
boolean cristalline;
int eventType;
Point eventPos;
int angle;

    public int getAngle() {
        return angle;
    }

    public boolean isCristalline() {
        return cristalline;
    }

    public Point getEventPos() {
        return eventPos;
    }

    public int getEventType() {
        return eventType;
    }

    public Point getP() {
        return p;
    }

    public boolean isTravable() {
        return travable;
    }

    public int getType() {
        return type;
    }

    public Instruction(int type, Point p, boolean travable, boolean cristalline, int eventType, Point eventPos, int angle) {
       this.angle=angle;
       this.cristalline=cristalline;
       this.eventPos=eventPos;
       this.type=type;
       this.travable=travable;
       this.eventType=eventType;
       this.p=p;
    }
}
