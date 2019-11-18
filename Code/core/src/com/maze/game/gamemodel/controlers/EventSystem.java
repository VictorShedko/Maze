package com.maze.game.gamemodel.controlers;

import com.maze.game.gamemodel.entity.MazeEvent;
import com.maze.game.gamemodel.entity.events.MazeEventEffeact;

import java.util.HashMap;
import java.util.Map;

public class EventSystem {
    MainController mainController;
    Map<MazeEvent, MazeEventEffeact> events;

    public void registrate(MazeEvent event, MazeEventEffeact effect) {

        events.put(event, effect);
    }

    public void update() {
        events.forEach((t, h) -> {
            if (t.check()) h.update();
        });

    }

    public EventSystem(MainController mainController) {
        this.mainController = mainController;
        this.events = new HashMap<>();
    }
}
