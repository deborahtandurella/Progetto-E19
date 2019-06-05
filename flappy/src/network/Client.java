package network;


import java.io.*;
import java.net.Socket;

public class Client {


    private String clientName;

    private Socket clientSocket ;

    private InputStream inputStream;
    private OutputStream outputStream;
    private DataInputStream inData;
    private DataOutputStream outData;

    private boolean option = true;


    public String getUsername() {
        return clientName;
    }

    public void SetConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            Thread th1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (option) {
                        ListenData();
                    }
                }
            });
            th1.start();
            System.out.println("Successfully connected to " + ip + ":" + port);
        } catch (IOException ex) {
            System.err.println("ERROR: connection error");
            System.exit(0);
        }
    }

    public void SendMessage(String msg) {
        try {
            outputStream = clientSocket.getOutputStream();
            outData = new DataOutputStream(outputStream);
            outData.writeUTF(msg);
            outData.flush();
        } catch (IOException ex) {
            System.err.println("ERROR: error sending data");
        }
    }

    public void ListenData(){
        //TODO
    }

    public void CloseConnection() {
        try {
            outData.close();
            inData.close();
            clientSocket.close();
        } catch (IOException ex) {
            System.err.println("ERROR: error closing connection");
        }
    }

    public void SetClientProperties(String name) {
        clientName = name;
    }

}
