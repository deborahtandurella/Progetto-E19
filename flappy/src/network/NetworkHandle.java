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
    private Socket socket;
    private ServerSocket serverSocket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    private CopyOnWriteArrayList<ConnectionListener> connectionListeners;
    private boolean connected = false;
    private boolean closingRequested = false;

    private RemoteGame remoteGame;
    private OnlineLocalGame localGame;
    private String othersName;

    public NetworkHandle(){
        connectionListeners=new CopyOnWriteArrayList<>();
    }

    public void setConnection(int port, String name) {

        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Connection opened on port " + port);
            socket = serverSocket.accept();
            serverSocket.close();
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(name);
            othersName=(String)inputStream.readObject();
            setConnected(true);
            System.out.println("Successfully connected");
        } catch (IOException | ClassNotFoundException ex) {
            if (!closingRequested){
                setConnected(false);
                closeConnection();
            }
        }
    }
    public void setConnection(String ip, int port, String name) {
        try {
            socket = new Socket(ip, port);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream.writeObject(name);
            othersName=(String) inputStream.readObject();
            setConnected(true);
            System.out.println("Successfully connected to " + ip + ":" + port);
            /*DatagramSocket udpSocket = new DatagramSocket(port);
            Command comando= new JumpCommand(0.4, 0.2);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(comando);
            oos.flush();
            byte[] sndData= bos.toByteArray();
            DatagramPacket packet = new DatagramPacket(sndData, sndData.length, socket.getInetAddress(), port );
            udpSocket.send(packet); */

        } catch (IOException | ClassNotFoundException ex) {
            if (!closingRequested){
                setConnected(false);
                closeConnection();
            }
        }
    }

    private void listenCommand() {
        try {
            Command command = (Command) inputStream.readObject();
            command.execute(remoteGame, localGame);
        } catch (IOException e) {
            setConnected(false);
            closeConnection();
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
           closeConnection();
        }
    }

    public void closeConnection() {
        if (!connected){
            closingRequested=true;
            if (serverSocket!=null && !serverSocket.isClosed())
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (connected)
            try {
                outputStream.close();
                inputStream.close();
                socket.close();
            } catch (IOException ex ) {
                System.err.println("ERROR: error closing connection");
            }
    }
    public void addConnectionListener(ConnectionListener listener){
        connectionListeners.add(listener);
    }
    private void notifyListeners(boolean connected){
        for(ConnectionListener listener: connectionListeners){
            listener.connectionStatus(connected);
        }
    }
    public void removeListener(ConnectionListener listener){
        connectionListeners.remove(listener);
    }
    private void setConnected(boolean connected) {
        this.connected = connected;
        System.err.println(connected);
        notifyListeners(connected);
    }
    public PlayerInfo getOthersInfo(){
        return new PlayerInfo(othersName);
    }
}
