package com.maze.game.gamemodel.entity.events;

import com.maze.game.gamemodel.entity.playrs.Human;

import java.util.function.Consumer;

public class PlayerStateEventEffeact implements MazeEventEffeact {
    private Human player;
    private Consumer<Human> effect;
    @Override
    public void update() {
        effect.accept(player);
    }

    public PlayerStateEventEffeact(Human player, Consumer<Human> effect) {
        this.player = player;
        this.effect = effect;
    }
}
