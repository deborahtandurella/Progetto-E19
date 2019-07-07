package network;


import game.RemoteGame;
import network.test.CommandHandler;
import network.test.commands.Command;
import states.MultiplayerLoading;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client implements CommandHandler {


    private String clientName;

    private Socket clientSocket ;
    private RemoteGame game;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private DataInputStream inData;
    private DataOutputStream outData;
    private ArrayList<ConnectionListener> connectionListeners;
    private boolean connected = false;

    public Client(){
        connectionListeners=new ArrayList<>();
    }
    public String getUsername() {
        return clientName;
    }

    public void setConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
            setConnected(true);
            System.out.println("Successfully connected to " + ip + ":" + port);
        } catch (IOException ex) {
            System.err.println("ERROR: connection error");
            System.exit(0);
        }
    }

    public void sendCommand(Command command) {
        try {
            outputStream.writeObject(command);
        } catch (IOException e) {
            setConnected(false);
        }
    }
    public void startListening(RemoteGame game){
        this.game=game;
        Thread th1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (connected) {
                    listenCommand();
                }
                closeConnection();
            }
        });
        th1.start();
    }
    private void listenCommand(){
        try {
            Command command = (Command) inputStream.readObject();
            command.execute(game);
        } catch (IOException e) {
            setConnected(false);
        } catch (ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            inputStream.close();
            outputStream.close();
            clientSocket.close();
        } catch (IOException ex) {
            System.err.println("ERROR: error closing connection");
        }
    }

    public void SetClientProperties(String name) {
        clientName = name;
    }
    public void addConnectionListener(ConnectionListener listener){
        connectionListeners.add(listener);
    }
    protected void notifyListeners(boolean connected){
        for(ConnectionListener listener: connectionListeners){
            listener.connectionWorking(connected);
        }
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
        notifyListeners(connected);
    }
}
