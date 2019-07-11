package network;


import game.RemoteGame;
import logic.player.PlayerInfo;
import network.test.CommandHandler;
import network.test.commands.Command;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client implements CommandHandler {


    private String othersName;

    private Socket clientSocket ;
    private RemoteGame game;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private ArrayList<ConnectionListener> connectionListeners;
    private boolean connected = false;

    public Client(){
        connectionListeners=new ArrayList<>();
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

    public void sendCommand(Command command) {
        try {
            outputStream.writeObject(command);
        } catch (IOException e) {
            e.printStackTrace();
            setConnected(false);
        }
    }
    public void startListening(RemoteGame game){
        this.game=game;
        new Thread(() -> {
            while (connected) {
                listenCommand();
            }
            closeConnection();
        }).start();
    }
    private void listenCommand(){
        try {
            Command command = (Command) inputStream.readObject();
            command.execute(game, null);
        } catch (IOException e) {
            e.printStackTrace();
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

    public void addConnectionListener(ConnectionListener listener){
        connectionListeners.add(listener);
    }
    private void notifyListeners(boolean connected){
        for(ConnectionListener listener: connectionListeners){
            listener.connectionWorking(connected);
        }
    }

    private void setConnected(boolean connected) {
        this.connected = connected;
        notifyListeners(connected);
    }
    public PlayerInfo getOthersInfo(){
        return new PlayerInfo(othersName);
    }

}
