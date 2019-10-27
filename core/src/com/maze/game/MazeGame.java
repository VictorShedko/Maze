package com.maze.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MazeGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img,img1;
	OrthographicCamera cam;
	MainController mainController;
	@Override
	public void create () {
		mainController =new MainController(0);

		batch = new SpriteBatch();
		img = new Texture("BasicFloor.png");
		img1 = new Texture("badlogic.jpg");

		cam = new OrthographicCamera(400, 400 );
		cam.position.set(100, 255, 0);
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
	private void handleInput() {

		if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {
			if(mainController.moveRequest(-1,0,0)) {
				cam.translate(-32, 0, 0);
			}
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
			if(mainController.moveRequest(1,0,0)) {
				cam.translate(32, 0, 0);
			}
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {

			if(mainController.moveRequest(0,-1,0)){
				cam.translate(0, -32, 0);
			}
		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			if(mainController.moveRequest(0,1,0)) {
				cam.translate(0, 32, 0);
			}
		}

		}
	}

