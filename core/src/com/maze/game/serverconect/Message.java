package com.maze.game.serverconect;

public class Message {
    int code;//0 start game 1 change turn 2 move
    int extend1, extend2;

    public int getCode() {
        return code;
    }

    public int getExtend1() {
        return extend1;
    }

    public int getExtend2() {
        return extend2;
    }


}
