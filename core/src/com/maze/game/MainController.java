package com.maze.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.maze.game.entity.Human;
import com.maze.game.fabric.Fabric;
import com.maze.game.fabric.MapCreator;
import com.maze.game.renderLogic.RenderControl;

import java.util.Timer;
import java.util.TimerTask;


public class MainController {
    boolean isChestFind;
    boolean isExitFind;
    int side;//0 human 1 monster
    PlayField playField;
    EventSystem eventSystem;
    Fabric fabric;
    Timer timeToAutoTurnChange=new Timer();
    TimerTaskChangeTurn t=new TimerTaskChangeTurn(this);
    RenderControl renderControl;
    int turn;//0 human 1 monster
    public void setChestFind(boolean chestFind) {
        isChestFind = chestFind;
    }

    public void setExitFind(boolean exitFind) {
        isExitFind = exitFind;
    }
    public int getCamX(){
        if(side==0)return playField.getHuman().getPosition().getX()*32;
        return playField.getMonster().getPosition().getX()*32;
    }
    public int getCamY(){
        if(side==0)return playField.getHuman().getPosition().getY()*32;
        return playField.getMonster().getPosition().getY()*32;
    }

    public boolean moveRequest(int xShift,int yShift,int side){
        Human player;
        boolean ret=false;
       // if(turn==side){
           if(side==0) { player=playField.getHuman();}
          else {
               player = playField.getMonster();
          }
            ret=player.moveTo(xShift, yShift);
          //  if(player.getStepsLeft()==0)this.changeTurn();
    //    }

        if(ret){
            eventSystem.update();
           if(isChestFind&&isExitFind){
               //human won;
           }
            if(playField.getMonster().isCaught()){
                //monster won;
            }
            return true;
        }else {
            return false;
        }

    }

    public void playFieldDraw(Batch batch){
        renderControl.reRender(side,batch);
        playField.draw(batch);
    }

    public void changeTurn(){
        turn=turn==1 ? 0:1;
        playField.refreshSteps(turn);
        t.cancel();
        t=new TimerTaskChangeTurn(this);
        timeToAutoTurnChange.schedule(t,1000);
    }

    public MainController(int side) {
        this.side=side;
        MapCreator.createMap("map.bat");
        TextureStorage textureStorage=new TextureStorage(side);
        playField=new PlayField(21,textureStorage);
        eventSystem=new EventSystem(this);
        fabric=new Fabric(eventSystem,playField);
        fabric.generateMap();
        renderControl=new RenderControl(playField);
        playField.see();
        playField.refreshSteps(side);

        timeToAutoTurnChange.schedule(t,5000);
    }
}
