package com.maze.game.gamemodel.entity.playrs;

import com.maze.game.gamemodel.PlayField;
import com.maze.game.gamemodel.entity.GameObject;
import com.maze.game.gamemodel.entity.Point;

public class Human extends GameObject {
    protected int stepsPerTurn;
    protected int stepsLeft;
    protected int fieldOfVisionSize;
    protected PlayField parentField;

    @Override
    public void makeVisible() {
        this.status = 2;
    }

    @Override
    public void makeInvisible() {
        this.status = 0;
    }

    public boolean moveTo(int xShift, int yShift) {
        Point destinationPoint = new Point(this.position.getX() + xShift, this.position.getY() + yShift);
        if (parentField.getObjectByKey(destinationPoint).isTraversable()) {
            position.setX(position.getX() + xShift);
            position.setY(position.getY() + yShift);
            this.stepsLeft--;
            return true;
        } else {

            return false;
        }
    }

    public int getStepsLeft() {
        return stepsLeft;
    }

    public void refreshSteps() {
        this.stepsLeft = this.stepsPerTurn;
    }

    public int getFieldOfVisionSize() {
        return fieldOfVisionSize;
    }

    public Human(int objectId, Point position, int status, int stepsPerTurn, int fieldOfVisionSize, PlayField playField) {
        super(objectId, position, status);
        this.stepsPerTurn = stepsPerTurn;
        this.fieldOfVisionSize = fieldOfVisionSize;
        this.status = status;
        this.parentField = playField;
    }
}
