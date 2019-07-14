package network;

import game.OnlineLocalGame;
import game.RemoteGame;
import logic.player.PlayerInfo;
import network.test.CommandHandler;
import network.test.commands.Command;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class Server implements CommandHandler {
    private String othersName;
    // Sockets
    private Socket clientSocket;
    private ServerSocket serverSocket;

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private RemoteGame remoteGame;
    private OnlineLocalGame localGame;
    private CopyOnWriteArrayList<ConnectionListener> connectionListeners;

    private boolean connected = false;
    public Server(){
        connectionListeners=new CopyOnWriteArrayList<>();
    }

    public void setConnection(int port, String name) {

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server connected on port " + port);
            clientSocket = serverSocket.accept();
            serverSocket.close();
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            outputStream.writeObject(name);
            othersName=(String)inputStream.readObject();
            setConnected(true);
            System.out.println("Successfully connected");
        } catch (IOException ex) {
            System.err.println("ERROR: connection error");
            System.exit(0);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void listenCommand() {
        try {
            Command command = (Command) inputStream.readObject();
            command.execute(remoteGame, localGame);
        } catch (IOException e) {
            setConnected(false);
        } catch (ClassNotFoundException | ClassCastException e) {
            e.printStackTrace();
        }
    }
    public void startListening(RemoteGame remoteGame, OnlineLocalGame localGame){
        if (connected){
            this.localGame= localGame;
            this.remoteGame = remoteGame;
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

    public void closeConnection() {
        try {
            outputStream.close();
            inputStream.close();
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
    public void removeListener(ConnectionListener listener){
        connectionListeners.remove(listener);
    }
    public void setConnected(boolean connected) {
        this.connected = connected;
        notifyListeners(connected);
    }
    public PlayerInfo getOthersInfo(){
        return new PlayerInfo(othersName);
    }
}
