package com.maze.game.serverconect;

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


    }

    public void startGame(){


    }

    public SocketControl(MazeGame game) {
        int port=8080;


        try {
            clientSocket = new Socket("localhost",port);



        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        this.listener=new ClientSocketListener(game,this,in);
        this.writer=new ClientSocketWriter(game,clientSocket,out);
        //this.writer.sendMessage(new Message(0,0,0,0,2));
        int a=0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
