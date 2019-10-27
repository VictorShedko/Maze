package com.maze.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.maze.game.serverconect.ClientSocket;

public class MazeGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img,img1;
	int playSide=1;
	OrthographicCamera cam;
	MainController mainController;
	ClientSocket ourSideSocket;
	boolean isGameActive=false;
	@Override
	public void create () {
		ourSideSocket=new ClientSocket();
		ourSideSocket.run();
		try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		mainController =new MainController(playSide);

		batch = new SpriteBatch();
		img = new Texture("BasicFloor.png");
		img1 = new Texture("badlogic.jpg");

		cam = new OrthographicCamera(400, 400);
		cam.position.set(mainController.getCamX(), mainController.getCamY(), 0);
		cam.update();

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
		mainController.playFieldDraw(batch);
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


	private void handleInput() {

		if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
			if(mainController.moveRequest(-1,0,playSide)) {
				cam.translate(-32, 0, 0);
			}
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
			if(mainController.moveRequest(1,0,playSide)) {
				cam.translate(32, 0, 0);
			}
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {

			if(mainController.moveRequest(0,-1,playSide)){
				cam.translate(0, -32, 0);
			}
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			if(mainController.moveRequest(0,1,playSide)) {
				cam.translate(0, 32, 0);
			}
		}

		}
	}

