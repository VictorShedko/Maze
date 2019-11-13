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
	int playSide=1;
	OrthographicCamera cam;
	MainController mainController;
	SocketControl ourSideSocket;
	boolean isGameActive=false;
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
		while (!isGameActive){
		System.out.println("wait");

		}
		System.out.println("game active");
		mainController =new MainController(playSide,ourSideSocket);



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
		cam.position.set(mainController.getCamX(), mainController.getCamY(), 0);
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
		System.out.println("notify side:"+side);
	}


	public void setGameActive(boolean gameActive) {
		isGameActive = gameActive;
	}

	public MainController getMainController() {
		return mainController;
	}

	private void handleInput() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.LEFT)) {

				ourSideSocket.moveRequest(-1,0,playSide);

		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
			ourSideSocket.moveRequest(1,0,playSide);


		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {

			ourSideSocket.moveRequest(0,-1,playSide);

		}
		if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			ourSideSocket.moveRequest(0,1,playSide);


		}

		}
	}

