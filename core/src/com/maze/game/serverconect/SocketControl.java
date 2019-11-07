package com.maze.game.serverconect;

import com.maze.game.view.MazeGame;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SocketControl {
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

    public SocketControl(MazeGame game) {
        int port=8080;


        try {
            clientSocket = new Socket("localhost",port);


///bolno
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        this.listener=new ClientSocketListener(game,clientSocket,in);
        this.writer=new ClientSocketWriter(game,clientSocket,out);
        this.writer.sendMessage(new Message(0,0,0,0));
        int a=0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
