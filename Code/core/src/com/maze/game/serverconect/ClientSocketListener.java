package com.maze.game.serverconect;

import com.maze.game.view.GameScreen;
import com.maze.game.view.MazeGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ClientSocketListener extends Thread{
    MazeGame game;
    int id=-1;
    private  BufferedReader in;
    int end=0;
    SocketControl parent;
    @Override
    public void run() {
        String str;
        try {
            while (end == 0) {

                str = in.readLine();
                List<Integer> args= Arrays.stream(str.split(" ")).map(t->Integer.parseInt(t)).collect(Collectors.toList());
                Message inMessage = new Message(args.get(0),args.get(1),args.get(2),args.get(3),args.get(4));
                System.out.println("Message is:"+inMessage.getCode()+" "+inMessage.getExtend1()+" "+inMessage.getExtend2()+" "+inMessage.getExtend3()+" "+inMessage.getAddress());
                switch (inMessage.getCode()) {
                    case 5:{
                     if(id==-1) {
                         id= inMessage.getAddress();
                         System.out.println("My id is "+id);
                     }
                    };break;
                    case 0: {

                        if(inMessage.getAddress()==id) {
                            game.setGameScreen(inMessage.getExtend1());
                            System.out.println("start request"+inMessage.getAddress());

                        }
                    }
                    ;
                    break;
                    case 1: {
                        if(inMessage.getAddress()==id) {
                            System.out.println("change");
                            game.getGameScreen().getMainController().changeTurn(inMessage.getExtend1());
                        }
                    }
                    ;
                    break;
                    case 2: {
                        if(inMessage.getAddress()==id) {
                            game.getGameScreen().getMainController().moveRequest(inMessage.getExtend1(), inMessage.getExtend2(), inMessage.getExtend3());
                        }
                    }
                    ;
                    break;

                    case 4: {
                        game.getGameScreen().setGameActive(false);
                    }

                    break;
                    case 9: {
                        if(inMessage.getAddress()==id) {
                            game.getEndGameScreen().replayRejected();
                        }
                    }
                    ;
                    break;
                    case 10: {
                        if(inMessage.getAddress()==id) {
                            game.setGameScreen(inMessage.getExtend1());
                        }
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
    public ClientSocketListener(MazeGame game, SocketControl parent, BufferedReader in) {
        this.game=game;
        this.parent=parent;
        this.in =in ;
        int a=0;
        start();
    }
}
