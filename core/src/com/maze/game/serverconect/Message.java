package com.maze.game.serverconect;

import java.io.Serializable;

public class Message implements Serializable {
    private int code;//0 start game 1 change turn 2 move 5 reg
    private int extend1, extend2,extend3;
    private int address;

    public int getCode() {
        return code;
    }

    public int getExtend1() {
        return extend1;
    }

    public int getExtend2() {
        return extend2;
    }

    public int getExtend3() {
        return extend3;
    }

    public int getAddress() {
        return address;
    }


    public Message(int code, int extend1, int extend2, int extend3, int address) {
        this.code = code;
        this.extend1 = extend1;
        this.extend2 = extend2;
        this.extend3 = extend3;
        this.address=address;
    }
}
