package com.maze.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class TextureStorage {
    Texture[][] textureBank =new Texture [10][10];
    Texture fogOfWar=new Texture("FogOfWar.png");
    Texture wallDark=new Texture("WallLight.png");
    Texture wallLight=new Texture("WallLight.png");
    Texture floorDark=new Texture("BasicFloor.png");
    Texture floorLight=new Texture("BasicFloor.png");
    Texture chestLight;
    Texture chestDark;
    Texture exitLight;
    Texture exitDark;
    Texture human=new Texture("Human.png");
    Texture empty=new Texture("Empty.png");
    public TextureStorage(int gameType) {


        if (gameType==0){
            textureBank[1][0]=empty;
            textureBank[1][1]=empty;
            textureBank[1][2]=human;

            textureBank[2][0]=empty;
            textureBank[2][1]=empty;
            textureBank[2][2]=human;

            textureBank[3][0]=fogOfWar;
            textureBank[3][1]=wallDark;
            textureBank[3][2]=wallLight;

            textureBank[4][0]=fogOfWar;
            textureBank[4][1]=floorDark;
            textureBank[4][2]=floorLight;

            textureBank[5][0]=fogOfWar;
            textureBank[5][1]=floorDark;
            textureBank[5][2]=floorDark;

            textureBank[6][0]=fogOfWar;
            textureBank[6][1]=floorDark;
            textureBank[6][2]=floorDark;

        }

    }

    Texture img = new Texture("BasicFloor.png");

    public Texture getTextureBank(int type,int status) {
        return textureBank[type][status];
    }
}
