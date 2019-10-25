package com.maze.game.entity;

import com.maze.game.PlayField;
import com.maze.game.Point;

public class Human extends GameObject{
    int stepsPerTurn;
    int stepsLeft;
    int fieldOfVisionSize;
    PlayField parentField;
    @Override
    public void makeVisible() {
    this.status=2;
    }

    @Override
    public void makeInvisible() {
    this.status=0;
    }
    public boolean moveTo(int xShift,int yShift){
        Point destinationPoint= new Point(this.position.getX()+xShift,this.position.getY()+yShift);
        if((parentField.getObjectByKey(destinationPoint).isTraversable)&&(stepsLeft>0)){
            position.setX(position.getX()+xShift);
            position.setY(position.getY()+yShift);
            return true;
        }else {

            return false;
        }
    }

    public int getStepsLeft() {
        return stepsLeft;
    }
    public void refreshSteps(){
        this.stepsLeft=this.stepsPerTurn;
    }

    public Human(int objectId, Point position, int status, int stepsPerTurn) {
        super(objectId, position, status);
        this.stepsPerTurn = stepsPerTurn;
    }
}
