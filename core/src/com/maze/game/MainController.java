package com.maze.game;

import com.badlogic.gdx.graphics.g2d.Batch;
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
    Timer timeToAutoTurnChange;
    TimerTask Chenger;
    RenderControl renderControl;
    int turn;//0 human 1 monster
    public void setChestFind(boolean chestFind) {
        isChestFind = chestFind;
    }

    public void setExitFind(boolean exitFind) {
        isExitFind = exitFind;
    }

    public boolean moveRequest(int xShift,int yShift,int side){
        boolean ret=false;
        if(turn==side){
           if(side==0)  ret=playField.getHuman().moveTo(xShift,yShift);
            ret =playField.getMonster().moveTo(xShift,yShift);
        }
        if(ret){
            eventSystem.update();
           if(isChestFind&&isExitFind){
               //human won;
           }
            if(playField.getMonster().isCaught()){
                //monster won;
            }
        }else {
            return false;
        }
        return false;
    }

    public void playFieldDraw(Batch batch){
        renderControl.reRender(side);
        playField.draw(batch);
    }

    public void changeTurn(){
        turn=turn==1 ? 0:1;

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
    }
}
