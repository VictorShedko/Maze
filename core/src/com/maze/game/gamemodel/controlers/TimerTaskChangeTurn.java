package com.maze.game.gamemodel.controlers;

import java.util.TimerTask;

public class TimerTaskChangeTurn extends TimerTask {
    MainController mainController;

    @Override
    public void run() {
        mainController.changeTurn();
    }

    public TimerTaskChangeTurn(MainController mainController) {
        this.mainController = mainController;
    }
}
