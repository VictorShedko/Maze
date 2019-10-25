package com.maze.game;

import com.maze.game.entity.Point;
import com.maze.game.entity.StaticObject;

import java.util.ArrayList;
import java.util.List;

public class RenderControl {
    List<Point> oldVision;
    List<Point> newVision;
    int[][]  crystallineMatrix;
    PlayField playField;
    public void reRender(){
            //call raytrecer and render logic
            List<Point> temp=oldVision;
            temp.removeAll(newVision);
            temp.forEach(t->playField.getObjectByKey(t).makeInvisible());
            temp=newVision;
            temp.removeAll(oldVision);
            temp.forEach(t->playField.getObjectByKey(t).makeVisible());
    }

    public RenderControl(PlayField playField) {
        this.playField=playField;
       List<StaticObject> list=new ArrayList<>(this.playField.getGameObjects().values());
       this.oldVision=new ArrayList<>();
       this.newVision=new ArrayList<>();
       list
               .stream()
               .forEach(t->
                        crystallineMatrix[t.getPosition().getX()][t.getPosition().getY()]=t.isCrystallineAsInt());
    }

}
