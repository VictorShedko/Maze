package com.maze.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
    Texture mainMenuScreenImage=new Texture("menuimage\\mainscreen.jpg");

    public MainMenuScreen(final MazeGame game) {

        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);


        exit = new TextButton("exit", game.textButtonStyle);
        exit.setSize(200, 100);
        exit.setPosition(100, 0);
        game.stage.addActor(exit);
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                    Gdx.app.exit();
            }
        });


        startGame = new TextButton("start game", game.textButtonStyle);
        startGame.setSize(200, 100);
        startGame.setPosition(100, 200);
        game.stage.addActor(startGame);
        startGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.ourSideSocket.startGame();

            }
        });


        joinGame = new TextButton("join game", game.textButtonStyle);
        joinGame.setSize(200, 100);
        joinGame.setPosition(100, 400);
        game.stage.addActor(joinGame);
        joinGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.ourSideSocket.joinGame();

            }
        });
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
        game.batch.draw(mainMenuScreenImage,0,0);
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


}
