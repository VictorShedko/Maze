package com.maze.game.serverconect;

import com.maze.game.view.MazeGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClientSocketListener extends Thread{
    MazeGame game;
    private  BufferedReader in;
    int end=0;
    @Override
    public void run() {
        String str;
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
    public ClientSocketListener(MazeGame game, Socket socket, BufferedReader in) {
        this.game=game;

        this.in =in ;
        int a=0;
        start();
    }
}
