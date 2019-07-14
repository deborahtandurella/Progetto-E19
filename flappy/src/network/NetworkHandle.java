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

public class NetworkHandle implements CommandHandler {
    private String othersName;
    // Sockets
    private Socket clientSocket;

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private RemoteGame remoteGame;
    private OnlineLocalGame localGame;
    private CopyOnWriteArrayList<ConnectionListener> connectionListeners;

    private boolean connected = false;
    public NetworkHandle(){
        connectionListeners=new CopyOnWriteArrayList<>();
    }

    public void setConnection(int port, String name) {

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("NetworkHandle connected on port " + port);
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
    public void setConnection(String ip, int port, String name) {
        try {
            clientSocket = new Socket(ip, port);
            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            inputStream = new ObjectInputStream(clientSocket.getInputStream());
            outputStream.writeObject(name);
            /*DatagramSocket udpSocket = new DatagramSocket(port);
            Command comando= new JumpCommand(0.4, 0.2);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(comando);
            oos.flush();
            byte[] sndData= bos.toByteArray();
            DatagramPacket packet = new DatagramPacket(sndData, sndData.length, clientSocket.getInetAddress(), port );
            udpSocket.send(packet); */
            othersName=(String) inputStream.readObject();
            setConnected(true);
            System.out.println("Successfully connected to " + ip + ":" + port);
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("ERROR: connection error");
            setConnected(false);
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
