package com.maze.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.maze.game.gamemodel.controlers.MainController;
import com.maze.game.serverconect.SocketControl;

public class GameScreen implements Screen {
    final MazeGame game;
    MainController mainController;
    OrthographicCamera cam;
    SocketControl socketControl;
    int playSide=1;
    boolean isGameActive=false;
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        handleInput();
        if(isGameActive&&mainController==null){
            mainController=new MainController(playSide,socketControl);
            isGameActive=true;
        }
        if(isGameActive){
            cam.position.set(mainController.getCamX(), mainController.getCamY(), 0);
            if(mainController.isGameEnd()){
                game.setScreen(new EndGameScreen(game));
            }
        }
        mainController.setGameEnd(true);
        cam.update();
        game.batch.setProjectionMatrix(cam.combined);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        game.batch.begin();
        if(!mainController.isGameEnd()){
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
        try {


            if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {

                game.ourSideSocket.moveRequest(-1, 0, playSide);

            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
                game.ourSideSocket.moveRequest(1, 0, playSide);


            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {

                game.ourSideSocket.moveRequest(0, -1, playSide);

            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                game.ourSideSocket.moveRequest(0, 1, playSide);


            }
        }catch (Exception ex){

        }

    }

    public MainController getMainController() {
        return mainController;
    }

    public void setGameActive(boolean gameActive) {
        isGameActive = gameActive;
    }

    public void setup(){

        mainController=new MainController(playSide,socketControl);
    }


    public GameScreen(MazeGame game, int side, SocketControl socketControl) {
        this.game = game;
        this.socketControl=socketControl;
        System.out.println("game active");
        this.playSide=side;
        cam = new OrthographicCamera(400, 400);
        cam.position.set(0, 0, 0);
        cam.update();
    }
}
