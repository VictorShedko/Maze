package com.maze.game.serverconect;

import com.maze.game.MazeGame;

import java.io.*;
import java.net.Socket;

public class ClientSocket extends Thread {
    private static Socket clientSocket;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
    MazeGame game;
    private static BufferedReader in;
    private static BufferedWriter out;
    int end=0;
    @Override
    public void run() {
        try {
            while (end==0)
            if(objectInputStream.available()!=0){
              Message inMessage=  (Message)objectInputStream.readObject();
                switch (inMessage.getCode()){
                    case 0:{
                        game.setGameActive(true);
                        game.setPlaySide(inMessage.extend1);
                    };break;
                    case 1:{
                        game.getMainController().changeTurn();
                    };break;
                    case 2:{
                        game.getMainController().moveRequest(inMessage.extend1,inMessage.extend2,inMessage.extend3);
                    };break;

                    case 4:{
                        game.setGameActive(false);
                    };break;
                    default:break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void end(){

    }
    public void sendMessage(Message ms)
        {
            try {
                this.objectOutputStream.writeObject(ms);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    public ClientSocket(MazeGame game) {
        this.game=game;
        try {
            clientSocket = new Socket("localhost", 8080);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            start();





        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
