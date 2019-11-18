package com.maze.game.serverconect;
import com.maze.game.view.MazeGame;

import java.io.*;
import java.net.Socket;


public class SocketControl {
    private Socket clientSocket;
    private BufferedReader in;
    private ClientSocketListener listener;
    private ClientSocketWriter writer;
    private boolean choiceMaid = false;
    public boolean isConected() {
        return clientSocket.isBound();

    }

    public void sendChangeTurnRequestMessage(int turn) {
        writer.sendMessage(new Message(1, turn, 0, 0, 3));
    }

    public void sendMoveRequestMessage(int xShift, int yShift, int side) {
        writer.sendMessage(new Message(2, xShift, yShift, side, 3));

    }
    public void sendEndGameMessage(int winner){
        writer.sendMessage(new Message(4, winner, 0, 0,0));
    }
    public void refresh() {
        choiceMaid = false;
    }

    public void sendJoinGameMessage() {
        if (!choiceMaid ) {
            writer.sendMessage(new Message(7, 0, 0, 0, 3));
            choiceMaid = true;
        }


    }

    public void sendStartGameMessage() {
        if (!choiceMaid ) {
            writer.sendMessage(new Message(8, 0, 0, 0, 3));
            choiceMaid = true;
        }

    }

    public void sendReplayRequestMessage() {
        writer.sendMessage(new Message(10, 0, 0, 0, 3));
    }

    public void sendReplayRejectMessage() {
        writer.sendMessage(new Message(9, 0, 0, 0, 3));
    }

    public SocketControl(MazeGame game) {
        int port = 8080;


        try {
            clientSocket = new Socket("localhost", port);


            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            this.listener = new ClientSocketListener(game, this, in);
            this.writer = new ClientSocketWriter(game, clientSocket, out);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
