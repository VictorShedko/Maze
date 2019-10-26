package com.maze.game.entity;

import com.maze.game.PlayField;

public class Monster extends Human {
    public boolean isCaught(){
        if(this.parentField.getHuman().getPosition().equals(this.position)){
            return true;
        }else {
            return false;
        }
    }

    public Monster(int objectId, Point position, int status, int stepsPerTurn, int fieldOfVisionSize, PlayField playField) {
        super(objectId, position, status, stepsPerTurn, fieldOfVisionSize,playField);
    }
}
