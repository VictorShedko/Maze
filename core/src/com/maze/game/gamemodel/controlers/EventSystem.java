package com.maze.game.gamemodel.controlers;

import com.maze.game.gamemodel.entity.MazeEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class EventSystem {
    MainController mainController;
    Map<MazeEvent, Consumer<MainController>> events;

    public void registrate(MazeEvent event, Consumer<MainController> effect) {
        events.put(event, effect);
    }

    public void update() {
        events.forEach((t, h) -> {
            if (t.check()) h.accept(mainController);
        });

    }

    public EventSystem(MainController mainController) {
        this.mainController = mainController;
        this.events = new HashMap<>();
    }
}
