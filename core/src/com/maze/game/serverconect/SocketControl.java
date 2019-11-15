package com.maze.game.serverconect;

import com.maze.game.view.GameScreen;
import com.maze.game.view.MazeGame;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SocketControl {
    int id;
    private  Socket clientSocket;
    private  BufferedReader in;
    private  BufferedWriter out;
    private ClientSocketListener listener;
    private ClientSocketWriter writer;
    private boolean choiceMaid = false;

    public ClientSocketListener getListener() {
        return listener;
    }

    public ClientSocketWriter getWriter() {
        return writer;
    }
    public void changeTurnRequest(int turn){
        writer.sendMessage(new Message(1, turn, 0, 0,3));
    }
    public void moveRequest(int xShift,int yShift,int side){
        writer.sendMessage(new Message(2,xShift,yShift,side,3));

    }

    public void joinGame(){
        if(choiceMaid==false) {
            writer.sendMessage(new Message(7, 0, 0, 0, 3));
            choiceMaid=true;
        }


    }

    public void startGame(){
        if(choiceMaid==false) {
        writer.sendMessage(new Message(8,0,0,0,3));
            choiceMaid=true;
        }

    }

    public void replayRequest(){
        writer.sendMessage(new Message(9,0,0,0,3));
    }

    public void replayReject(){
        writer.sendMessage(new Message(10,0,0,0,3));
    }
    public SocketControl(MazeGame game) {
        int port=8080;


        try {
            clientSocket = new Socket("localhost",port);



        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        this.listener=new ClientSocketListener(game,this,in);
        this.writer=new ClientSocketWriter(game,clientSocket,out);
        int a=0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
