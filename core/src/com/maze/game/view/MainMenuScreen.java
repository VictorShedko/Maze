package com.maze.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import org.w3c.dom.Text;

import java.awt.*;

public class MainMenuScreen implements Screen {
    final MazeGame game;
    TextButton exit;
    TextButton startGame;
    TextButton joinGame;
    OrthographicCamera camera;
    Skin skin = new Skin();
    Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);


    public MainMenuScreen(final MazeGame game) {

        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);




        exit=new TextButton("exit",game.textButtonStyle);
        game.stage.addActor(exit);
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
        startGame=new TextButton("exit",game.textButtonStyle);
        game.stage.addActor(startGame);
        startGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.ourSideSocket.startGame();
              game.setScreen(game.gameScreen);
            }
        });
        joinGame=new TextButton("exit",game.textButtonStyle);
        game.stage.addActor(joinGame);
        joinGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.ourSideSocket.joinGame();
                game.setScreen(game.gameScreen);
            }
        });
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_WRITEMASK);




        game.batch.begin();
        game.stage.draw();
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(game.createGameScreen());
            dispose();
        }
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


}
