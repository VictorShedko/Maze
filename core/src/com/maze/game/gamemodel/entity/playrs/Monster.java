package com.maze.game.gamemodel.entity.playrs;

import com.maze.game.gamemodel.PlayField;
import com.maze.game.gamemodel.entity.Point;

public class Monster extends Human {
    public boolean isCaught() {
        if (this.parentField.getHuman().getPosition().equals(this.position)) {
            return true;
        } else {
            return false;
        }
    }

    public Monster(int objectId, Point position, int status, int stepsPerTurn, int fieldOfVisionSize, PlayField playField) {
        super(objectId, position, status, stepsPerTurn, fieldOfVisionSize, playField);
    }
}
