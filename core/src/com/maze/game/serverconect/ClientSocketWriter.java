package com.maze.game.serverconect;

import com.maze.game.view.MazeGame;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClientSocketWriter extends Thread {
    private  Socket clientSocket;

    MazeGame game;
    private  BufferedReader in;
    private  BufferedWriter out;
    int end=0;
    String str;

    public void setEnd(int end) {
        this.end = end;
    }


    public void sendMessage(Message ms)
        {
            try {
                out.write(ms.getCode()+" "+ms.getExtend1()+" "+ms.getExtend2()+" "+ms.getExtend3()+" "+ms.getAddress()+"\n");
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    public ClientSocketWriter(MazeGame game,Socket socket,BufferedWriter out) {
        this.game=game;

            this.out =out ;
            int a=0;

    }
}
