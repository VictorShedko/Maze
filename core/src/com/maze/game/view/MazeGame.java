package com.maze.game.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.maze.game.gamemodel.controlers.MainController;
import com.maze.game.serverconect.Message;
import com.maze.game.serverconect.SocketControl;

public class MazeGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img,img1;
	int playSide=1;
	OrthographicCamera cam;
	MainController mainController;
	SocketControl ourSideSocket;
	boolean isGameActive=false;
	@Override
	public void create () {
		ourSideSocket=new SocketControl(this);



		while (!isGameActive){


		}
		mainController =new MainController(playSide,ourSideSocket.getWriter());

		batch = new SpriteBatch();


		cam = new OrthographicCamera(400, 400);
		cam.position.set(mainController.getCamX(), mainController.getCamY(), 0);
		cam.update();

	}

	public void setPlaySide(int playSide) {
		this.playSide = playSide;
	}

	@Override
	public void render () {
		handleInput();
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//batch.setProjectionMatrix(cam.combined);

		batch.begin();
		if(mainController.isGameEnd()){

		}else {
			mainController.playFieldDraw(batch);
		}
		//batch.draw(img, 100, 100);
	//	batch.draw(img1,0,0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	public void notifyStart(int side){
		this.playSide=side;
		this.isGameActive=true;
	}


	public void setGameActive(boolean gameActive) {
		isGameActive = gameActive;
	}

	public MainController getMainController() {
		return mainController;
	}

	private void handleInput() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {

				ourSideSocket.getWriter().sendMessage(new Message(2,-1,0,playSide));

		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {

				ourSideSocket.getWriter().sendMessage(new Message(2,1,0,playSide));

		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {


				ourSideSocket.getWriter().sendMessage(new Message(2,0,-1,playSide));
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			if(mainController.moveRequest(0,1,playSide)) {
				cam.translate(0, 32, 0);
				ourSideSocket.getWriter().sendMessage(new Message(2,0,1,playSide));
			}
		}

		}
	}

