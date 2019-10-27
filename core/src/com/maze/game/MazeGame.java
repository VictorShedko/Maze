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

		cam = new OrthographicCamera(200, 200 );
		cam.position.set(100, 100, 0);
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

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
			cam.translate(-3, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			cam.translate(3, 0, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			cam.translate(0, -3, 0);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
			cam.translate(0, 3, 0);
		}

		}
	}

