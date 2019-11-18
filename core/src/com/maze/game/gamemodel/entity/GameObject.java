package com.maze.game.gamemodel.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.maze.game.gamemodel.TextureStorage;

public abstract class GameObject{
    private final static float CELL_SIZE = 32f;
    private ShaderProgram darker;

    protected int objectId;
    protected int status;//0- never seen before,1- has been seen but now out of range 2 in vision
    protected Point position;

    public int getObjectId() {
        return objectId;
    }

    public Point getPosition() {
        return position;
    }

    public abstract void makeVisible();

    public abstract void makeInvisible();

    public void draw(Batch batch, TextureStorage store) {
        Texture texture = store.getTextureBank(objectId, status);
        Rectangle boundingBox = new Rectangle(
                position.getX() * CELL_SIZE,
                position.getY() * CELL_SIZE,
                CELL_SIZE,
                CELL_SIZE
        );


        if (status == 1) {

            batch.setShader(darker);
        } else {
            batch.setShader(null);
        }


        batch.draw(texture, boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);


    }

    public GameObject(int objectId, Point position, int status) {
        this.objectId = objectId;
        this.position = position;
        this.status = status;

        darker = new ShaderProgram(Gdx.files.internal("shaders/default.vert"),
                Gdx.files.internal("shaders/darker.frag"));
        darker.pedantic = false;
    }
}
