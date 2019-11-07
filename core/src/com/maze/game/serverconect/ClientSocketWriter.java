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
    @Override
    public void run() {
        sendMessage(new Message(0,0,0,0));
        try {
            while (end == 0) {

                str = in.readLine();
               List<Integer> args= Arrays.stream(str.split(" ")).map(t->Integer.parseInt(t)).collect(Collectors.toList());
                Message inMessage = new Message(args.get(0),args.get(1),args.get(2),args.get(3));
                switch (inMessage.getCode()) {
                    case 0: {
                        game.setGameActive(true);
                        game.setPlaySide(inMessage.extend1);
                    }
                    ;
                    break;
                    case 1: {
                        game.getMainController().changeTurn();
                    }
                    ;
                    break;
                    case 2: {
                        game.getMainController().moveRequest(inMessage.extend1, inMessage.extend2, inMessage.extend3);
                    }
                    ;
                    break;

                    case 4: {
                        game.setGameActive(false);
                    }
                    ;
                    break;
                    default:
                        break;
                }
            }
            } catch(IOException e){
                e.printStackTrace();

            }

    }
    public void setEnd(int end) {
        this.end = end;
    }


    public void sendMessage(Message ms)
        {
            try {
                out.write(ms.getCode()+" "+ms.getExtend1()+" "+ms.getExtend2()+" "+ms.getExtend3()+"\n");
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
