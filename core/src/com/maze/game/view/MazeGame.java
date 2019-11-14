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
	MainMenuScreen mainMenuScreen;
	GameScreen gameScreen;
	EndGameScreen endGameScreen;
	Stage stage;
	SpriteBatch batch;
	TextButton.TextButtonStyle textButtonStyle;
	BitmapFont font;
	Skin skin;
	TextureAtlas buttonAtlas;



	SocketControl ourSideSocket;

	@Override
	public void create () {


		ourSideSocket=new SocketControl(this);
		batch = new SpriteBatch();
		stage=new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		font = new BitmapFont();
		skin = new Skin();

		textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = font;


		mainMenuScreen=new MainMenuScreen(this);
		this.setScreen(mainMenuScreen);






	}



	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

		batch.dispose();

	}


	public MainMenuScreen getMainMenuScreen() {
		return mainMenuScreen;
	}

	public GameScreen getGameScreen() {
		return gameScreen;
	}

	public EndGameScreen getEndGameScreen() {
		return endGameScreen;
	}

	public void setGameScreen(){
    gameScreen=new GameScreen(this);

	}


	}

