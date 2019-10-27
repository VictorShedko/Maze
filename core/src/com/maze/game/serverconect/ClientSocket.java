package com.maze.game.serverconect;

import java.io.*;
import java.net.Socket;

public class ClientSocket extends Thread {
    private static Socket clientSocket;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;
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

    public ClientSocket() {
        try {
            clientSocket = new Socket("localhost", 4004);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());






        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
