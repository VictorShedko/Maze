package com.maze.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class EndGameScreen implements Screen {
    MazeGame game;
    TextButton replayButton;
    TextButton mainMenuButton;
    OrthographicCamera camera;
    Texture endGameScreen=new Texture("menuimage\\GameOver.jfif");
    Stage endGameStage;
    public void replayConfirm(){

    }
    
    public void replayRejected(){
        game.setScreen(game.mainMenuScreen);
    }


    private void replayRequest(){


    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(endGameStage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_WRITEMASK);

        camera.update();


        game.batch.begin();
        endGameStage.getBatch().begin();
        endGameStage.getBatch().draw(endGameScreen,0,0,640,480);
        endGameStage.getBatch().end();
        endGameStage.draw();

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
        endGameStage =new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(endGameStage);
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);


        replayButton = new TextButton("once again", game.textButtonStyle);
        replayButton.setSize(200, 100);
        replayButton.setPosition(400, 200);
        endGameStage.addActor(replayButton);
        replayButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

                game.ourSideSocket.sendReplayRequestMessage();
            }
        });


        mainMenuButton = new TextButton("main menu", game.textButtonStyle);
        mainMenuButton.setSize(200, 100);
        mainMenuButton.setPosition(200, 200);
        endGameStage.addActor(mainMenuButton);
        mainMenuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.ourSideSocket.refresh();
                game.ourSideSocket.sendReplayRejectMessage();
                game.setScreen(game.mainMenuScreen);

            }
        });

    }
}
