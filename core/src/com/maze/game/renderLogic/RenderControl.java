package com.maze.game.renderLogic;

import com.badlogic.gdx.graphics.g2d.Batch;
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
    Batch batch;
    public void reRender(int side,Batch batch){
           Human player;
            if (side==0){
                player=playField.getHuman();
            }else {
                player=playField.getMonster();
            }

            newVision.clear();
            newVision.add(player.getPosition());
            this.fillNewVision(player.getPosition().getX(),player.getPosition().getY(),player.getFieldOfVisionSize());
            List<Point> temp=new ArrayList<>(oldVision);

        File check=new File("check1.txt");
        try {
            FileWriter fl = new FileWriter(check);

            for (int i = 0; i < newVision.size(); i++) {


                    fl.write(newVision.get(i).getX()+" "+newVision.get(i).getY()+"\n");


            }
            fl.write("suka");
            fl.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
            temp.removeAll(newVision);
            temp.forEach(t->playField.getObjectByKey(t).makeInvisible());
            temp=new ArrayList<>(newVision);
            temp.removeAll(oldVision);
            oldVision.clear();
            oldVision.addAll(newVision);
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
    protected boolean addToList(Double x,Double y,Double dx,Double dy){


        if(x<0||x>20)return true;
        if(y<0||y>20)return true;
        int a1,a2;
        a1=(int)Math.floor(x+dx);
        a2=(int)Math.floor(y+dy);
        Point p=new Point(a1,a2);
        if(newVision.indexOf(p)==-1){
            newVision.add(p);
        }
        if(crystallineMatrix[a1][a2]==0)return true;
        return false;
    }



    protected void BresenhamLine (double xStart, double yStart, double xEnd, double yEnd)
    {
        double  dx, dy;
        Double xMin=Math.min(xStart,xEnd);
        Double xMax=Math.max(xStart,xEnd);
        Double yMin=Math.min(yStart,yEnd);
        Double yMax=Math.max(yStart,yEnd);
        Intersections startPoint=new Intersections(xStart,yStart);
        dx = xEnd - xStart;//проекция на ось икс
        dy = yEnd - yStart;//проекция на ось игрек
        double ex=sign(dx)*0.000001,ey=sign(dy)*0.000001;
        List<Intersections>  rayTrack=new ArrayList<Intersections>();
        for (int i=(int)Math.ceil(xMin);i<=(int)Math.floor(xMax);i++){
            Intersections in= new Intersections(i,yStart+(i-xStart)*dy/dx);
            in.setRange(startPoint);
            rayTrack.add(in);
        }
        for (int i=(int)Math.ceil(yMin);i<=(int)Math.floor(yMax);i++){
            Intersections in= new Intersections(xStart+(i-yStart)*dx/dy,i);
            in.setRange(startPoint);
            rayTrack.add(in);
        }
        rayTrack.sort((o1,o2)->o2.compareTo(o1));
        File chack=new File("check3.txt");

        try {
            FileWriter fl = new FileWriter(chack,true);

            for (int i = 0; i < rayTrack.size(); i++) {

                    fl.write(rayTrack.get(i).getX()+" "+rayTrack.get(i).getY()+"   ");
            }
            fl.write("\n");
            fl.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
        for(int i=rayTrack.size()-1;i>=0;i--){
            Double x=rayTrack.get(i).getX();
            Double y=rayTrack.get(i).getY();
            if(addToList(x,y,ex,ey))return;

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
