package network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private String serverName;
    // Sockets
    private Socket myService;
    private ServerSocket serviceSocket;

    private OutputStream outputStream;
    private InputStream inputStream;

    private DataOutputStream outputData;
    private DataInputStream inputData;

    private boolean option = true;

    public String getUsername() {
        return serverName;
    }

    public void SetConnection(int port) {
        System.out.println("Server listening on port " + port);

        try {
            serviceSocket = new ServerSocket(port);
            System.out.println("Server listening on port " + port);
            System.out.println("sto facendo qualcosa");

            myService = serviceSocket.accept();
            System.out.println("sto facendo qualcosa");

            Thread th1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (option) {
                        ListenData();
                        System.out.println("sto facendo qualcosa");
                    }
                }
            });
            th1.start();
            System.out.println("Successfully connected");
        } catch (IOException ex) {
            System.err.println("ERROR: connection error");
            System.exit(0);
        }
    }

    public void ListenData() {
        try {
            inputStream = myService.getInputStream();
            inputData = new DataInputStream(inputStream);
            if(inputData.readUTF().equals("connected from client")){
                SendMessage("connected from server");
            }
        } catch (IOException ex) {
            System.err.println("ERROR: error listening data");
        }
    }

    public void SendMessage(String msg) {
        try {
            outputStream = myService.getOutputStream();
            outputData = new DataOutputStream(outputStream);
            outputData.writeUTF(msg);
            outputData.flush();
        } catch (IOException ex) {
            System.err.println("ERROR: error sending data");
        }
    }

    public void SetServerProperties(String name) {
        serverName = name;
    }

    public void CloseConnection() {
        try {
            outputData.close();
            inputData.close();
            serviceSocket.close();
            myService.close();
        } catch (IOException ex) {
            System.err.println("ERROR: error closing connection");
        }
    }

}
