package com.maze.game.gamemodel.entity.events;

import com.maze.game.gamemodel.controlers.MainController;

import java.util.function.Consumer;

public class SystemStateEventEffeact implements MazeEventEffeact {
    private MainController mainController;
    private Consumer<MainController> effect;
    @Override
    public void update() {
        effect.accept(mainController);
    }

    public SystemStateEventEffeact(MainController mainController, Consumer<MainController> effect) {
        this.mainController = mainController;
        this.effect = effect;
    }
}
