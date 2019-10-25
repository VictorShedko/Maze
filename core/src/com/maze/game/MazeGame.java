package com.maze.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MazeGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	OrthographicCamera cam;
	MainController mainController;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("BasicFloor.png");
		cam = new OrthographicCamera(200, 200 );
		cam.position.set(100, 100, 0);
		cam.update();

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
