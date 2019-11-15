package com.maze.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class EndGameScreen implements Screen {
    MazeGame game;
    TextButton replayButton;
    TextButton mainMenuButton;
    OrthographicCamera camera;

    public void replayConfirm(){

    }
    
    public void replayRejected(){
        game.setScreen(game.mainMenuScreen);
    }


    private void replayRequest(){


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_WRITEMASK);

        camera.update();
        game.batch.begin();
        game.stage.draw();

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public EndGameScreen(MazeGame game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);


        replayButton = new TextButton("once again", game.textButtonStyle);
        replayButton.setSize(200, 100);
        replayButton.setPosition(100, 0);
        game.stage.addActor(replayButton);
        replayButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                game.ourSideSocket.replayRequest();
            }
        });


        mainMenuButton = new TextButton("main menu", game.textButtonStyle);
        mainMenuButton.setSize(200, 100);
        mainMenuButton.setPosition(100, 200);
        game.stage.addActor(mainMenuButton);
        mainMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.ourSideSocket.replayReject();
                game.setScreen(game.mainMenuScreen);

            }
        });

    }
}
