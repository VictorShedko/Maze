package com.maze.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.maze.game.gamemodel.controlers.MainController;

public class GameScreen implements Screen {
    final MazeGame game;
    MainController mainController;
    OrthographicCamera cam;
    int playSide=1;
    boolean isGameActive=false;
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        handleInput();
        cam.position.set(mainController.getCamX(), mainController.getCamY(), 0);
        cam.update();
        game.batch.setProjectionMatrix(cam.combined);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        game.batch.begin();
        if(mainController.isGameEnd()){

        }else {
            mainController.playFieldDraw(game.batch);
        }
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

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {

            game.ourSideSocket.moveRequest(-1,0,playSide);

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
            game.ourSideSocket.moveRequest(1,0,playSide);


        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {

            game.ourSideSocket.moveRequest(0,-1,playSide);

        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            game.ourSideSocket.moveRequest(0,1,playSide);


        }

    }

    public MainController getMainController() {
        return mainController;
    }
    public void notifyStart(int side){
        this.playSide=side;
        this.isGameActive=true;
        System.out.println("notify side:"+side);
    }
    public void setGameActive(boolean gameActive) {
        isGameActive = gameActive;
    }
    public MazeGame getRootGame(){
        return game;
    }

    public GameScreen(MazeGame game) {
        this.game = game;
        System.out.println("game active");
        mainController =new MainController(playSide,game.ourSideSocket);
        cam = new OrthographicCamera(400, 400);
        cam.position.set(mainController.getCamX(), mainController.getCamY(), 0);
        cam.update();
    }
}
