package com.maze.game.view;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.maze.game.gamemodel.controlers.MainController;
import com.maze.game.serverconect.Message;
import com.maze.game.serverconect.SocketControl;

public class MazeGame extends Game {
	MainMenuScreen mainMenuScreen=new MainMenuScreen(this);
	GameScreen gameScreen;
	EndGameScreen endGameScreen;
	Stage stage;
	SpriteBatch batch;
	TextButton.TextButtonStyle textButtonStyle;
	BitmapFont font;
	Skin skin;
	TextureAtlas buttonAtlas;
	Texture img,img1;


	SocketControl ourSideSocket;

	@Override
	public void create () {


		//ourSideSocket=new SocketControl(this);
		batch = new SpriteBatch();
		stage=new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		font = new BitmapFont();
		skin = new Skin();
		buttonAtlas = new TextureAtlas(Gdx.files.internal("buttons/buttons.pack"));
		skin.addRegions(buttonAtlas);
		textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = font;
		textButtonStyle.up = skin.getDrawable("up-button");
		textButtonStyle.down = skin.getDrawable("down-button");
		textButtonStyle.checked = skin.getDrawable("checked-button");


		this.setScreen(mainMenuScreen);






	}



	@Override
	public void render () {

	}
	
	@Override
	public void dispose () {

		batch.dispose();
		img.dispose();
	}




	public void setGameActive(boolean gameActive) {
		isGameActive = gameActive;
	}


	public GameScreen createGameScreen(){


	}


	}

