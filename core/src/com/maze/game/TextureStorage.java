package com.maze.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class TextureStorage {
    Texture[][] textureBank;
    Texture fogOfWar;
    Texture wallDark;
    Texture wallLight;
    Texture floorDark;
    Texture floorLight;
    Texture chestLight;
    Texture chestDark;
    Texture exitLight;
    Texture exitDark;
    public TextureStorage(int gameType) {


        if (gameType==0){
          textureBank[1][0]=fogOfWar;
          textureBank[1][1]=wallDark;
          textureBank[1][2]=wallLight;
        }

    }

    Texture img = new Texture("BasicFloor.png");

    public Texture getTextureBank(int type,int status) {
        return textureBank[type][status];
    }
}
