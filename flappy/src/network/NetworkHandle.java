package network;

import game.multiplayer.OnlineLocalGame;
import game.multiplayer.OnlineRemoteGame;
import game.player.PlayerInfo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class NetworkHandle  {
    private Socket socket;
    private ServerSocket serverSocket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private NetworkReceiver receiver;
    private CopyOnWriteArrayList<ConnectionListener> connectionListeners;
    private boolean connected = false;
    private boolean closingRequested = false;

    private OnlineRemoteGame remoteGame;
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
    private void listen() {
        try {
            receiver.receive(inputStream.readObject());
        } catch (IOException e) {
            setConnected(false);
            closeConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void startListening(){
            new Thread(() -> {
                while (connected) {
                    if (receiver!=null)
                        listen();
                }
                closeConnection();
            }).start();

    }
    public void sendObject(Object object) {
        try {
            outputStream.writeObject(object);
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
        notifyListeners(connected);
    }
    public PlayerInfo getOthersInfo(){
        return new PlayerInfo(othersName);
    }

    public void setReceiver(NetworkReceiver receiver) {
        this.receiver = receiver;
    }
}
