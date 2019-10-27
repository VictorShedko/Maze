package com.maze.game.renderLogic;

import com.badlogic.gdx.graphics.glutils.VertexArray;
import com.maze.game.PlayField;
import com.maze.game.entity.Human;
import com.maze.game.entity.Point;
import com.maze.game.entity.StaticObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class RenderControl {
    List<Point> oldVision;
    List<Point> newVision;
    Integer[][]  crystallineMatrix=new Integer[21][21] ;
    PlayField playField;
    public void reRender(int side){
           Human player;
            if (side==0){
                player=playField.getHuman();
            }else {
                player=playField.getMonster();
            }


            this.fillNewVision(player.getPosition().getX(),player.getPosition().getY(),player.getFieldOfVisionSize());
            List<Point> temp=oldVision;
            temp.removeAll(newVision);
            temp.forEach(t->playField.getObjectByKey(t).makeInvisible());
            temp=newVision;
            temp.removeAll(oldVision);
            temp.forEach(t->playField.getObjectByKey(t).makeVisible());
            if(newVision.indexOf(playField.getHuman().getPosition())!=-1){
                playField.getHuman().makeVisible();
            }
            if(newVision.indexOf(playField.getMonster().getPosition())!=-1){
            playField.getMonster().makeVisible();
          }
    }
    private int sign (double x) {
        return (x > 0) ? 1 : (x < 0) ? -1 : 0;
        //возвращает 0, если аргумент (x) равен нулю; -1, если x < 0 и 1, если x > 0.
    }

    protected void BresenhamLine (double xStart, double yStart, double xEnd, double yEnd)
    {
        double  dx, dy;
        Double xMin=Math.min(xStart,xEnd);
        Double xMax=Math.max(xStart,xEnd);
        Double yMin=Math.min(yStart,yEnd);
        Double yMax=Math.max(yStart,yEnd);
        dx = xEnd - xStart;//проекция на ось икс
        dy = yEnd - yStart;//проекция на ось игрек
        List<Intersections>  rayTrack=new ArrayList<Intersections>();
        for (int i=(int)Math.ceil(xMin);i<(int)Math.floor(xMax);i++){
            Intersections in= new Intersections(i,yStart+(i-xStart*dy)/dx);
            rayTrack.add(in);
        }
        for (int i=(int)Math.ceil(yMin);i<(int)Math.floor(yMax);i++){
            Intersections in= new Intersections(xStart+i*dx/dy,i);
            rayTrack.add(in);
        }
        rayTrack.sort((o1,o2)->o1.compareTo(o2));
        for(Intersections i:rayTrack){
            Double x=i.getX();
            Double y=i.getY();
           newVision.add(new Point(x.intValue(),y.intValue()));
           if(crystallineMatrix[x.intValue()][y.intValue()]==0)return;

        }

    }

    protected void fillNewVision(int x,int y,int radius){
        for(double angle=0;angle<7;angle+=(1.5/radius))
        {
            this.BresenhamLine(x+0.5,y+0.5,x+0.5+radius*Math.sin(angle),y+0.5+radius*Math.cos(angle));
        }
     //   this.BresenhamLine(x+0.5,y+0.5,)
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
        File chack=new File("check.txt");

        try {
            FileWriter fl = new FileWriter(chack);

            for (int i = 0; i < 21; i++) {

                for (int j = 0; j < 21; j++) {
                    fl.write(crystallineMatrix[i][j].intValue()+'0');

                }
                fl.write("\n");
            }
            fl.write("suka");
            fl.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }


    }



}
