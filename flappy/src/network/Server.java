package network;

import game.RemoteGame;
import network.test.CommandHandler;
import network.test.commands.Command;
import states.MultiplayerLoading;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements CommandHandler {
    private String serverName;
    // Sockets
    private Socket clientSocket;
    private ServerSocket serverSocket;



    private DataOutputStream outputData;
    private DataInputStream inputData;

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private RemoteGame game;
    private ArrayList<ConnectionListener> connectionListeners;

    private boolean connected = false;
    public Server(){
        connectionListeners=new ArrayList<>();
    }
    public String getUsername() {
        return serverName;
    }

    public void setConnection(int port) {

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server connected on port " + port);
            clientSocket = serverSocket.accept();
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            listenCommand();
            setConnected(true);
            System.out.println("Successfully connected");
        } catch (IOException ex) {
            System.err.println("ERROR: connection error");
            System.exit(0);
        }
    }

    private void listenCommand() {
        try {
            Command command = (Command) inputStream.readObject();
            command.execute(game);
        } catch (IOException e) {
            setConnected(false);
        } catch (ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
        }
    }
    public void startListening(RemoteGame game){
        if (connected){
            this.game=game;
            new Thread(() -> {
                while (connected) {
                    listenCommand();
                }
                closeConnection();
            }).start();
        }
    }
    public void sendCommand(Command command) {
        try {
            outputStream.writeObject(command);
        } catch (IOException e) {
           setConnected(false);
        }
    }

    public void setServerProperties(String name) {
        serverName = name;
    }

    public void closeConnection() {
        try {
            outputStream.close();
            inputStream.close();
            serverSocket.close();
            clientSocket.close();
        } catch (IOException ex) {
            System.err.println("ERROR: error closing connection");
        }
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
