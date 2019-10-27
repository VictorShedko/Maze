package com.maze.game.serverconect;

import com.maze.game.MazeGame;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClientSocket extends Thread {
    private static Socket clientSocket;

    MazeGame game;
    private static BufferedReader in;
    private static BufferedWriter out;
    int end=0;
    String str;
    @Override
    public void run() {
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

    public void end(){

    }
    public void sendMessage(Message ms)
        {
            try {
                out.write(ms.getCode()+" "+ms.getExtend1()+" "+ms.getExtend2()+" "+ms.getExtend3());
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    public ClientSocket(MazeGame game) {
        this.game=game;
        try {
            clientSocket = new Socket("localhost", 8080);

            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            int a=0;
           // start();





        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
