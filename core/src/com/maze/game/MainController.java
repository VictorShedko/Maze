package com.maze.game;

import com.maze.game.fabric.Fabric;

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

    public boolean moveRequest(int xShift,int yShist){

        if(turn==side){
           if(side==0) return playField.getHuman().moveTo(xShift,yShist);
           return playField.getMonster().moveTo(xShift,yShist);
        }
        return false;
    }
    public void playFieldDraw(){
        renderControl.reRender();
        playField.draw();
    }

    public void changeTurn(){
        turn=turn==1 ? 0:1;

    }

    public MainController(int side) {
        this.side=side;
    }
}
