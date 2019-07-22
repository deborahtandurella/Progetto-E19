package network;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 *  ConnectionHandle permette di aprire ad una connessione TCP o di connettersi ad una connessione TCP già aperta.
 *  Perche i messaggi ricevuti vengano reindirizzati al receiver è necessario avviare prima l'ascolto con il metodo startListening()
 *  Implementa di default lo scambio di nomi dei due soggetti in connessione per facilitare l'identificazione. Il nome della controparte può essere ottenuto tramite il metodo getOthersInfo()
 *
 */
public class ConnectionHandle {
    private Socket socket;
    private ServerSocket serverSocket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private NetworkReceiver receiver;

    private String othersName;

    private CopyOnWriteArrayList<ConnectionListener> connectionListeners;
    private boolean connected = false;
    private boolean closingRequested = false;

    public ConnectionHandle(){
        connectionListeners=new CopyOnWriteArrayList<>();
    }

    /**
     * Apre ad una connessione sulla porta specificata
     * @param port la porta
     * @param name il nome con il quale il soggetto desidera essere identificato
     */
    public void setConnection(int port, String name) {

        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            serverSocket.close();
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(name);
            othersName=(String)inputStream.readObject();
            setConnected(true);

        } catch (IOException | ClassNotFoundException ex) {
            if (!closingRequested){
                setConnected(false);
                closeConnection();
            }
        }
    }

    /**
     * Si connette all'indirizzo specificato
     * @param ip indirizzo ip
     * @param port porta
     * @param name il nome con il quale il soggetto desidera essere identificato
     */
    public void setConnection(String ip, int port, String name) {
        try {
            socket = new Socket(ip, port);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream.writeObject(name);
            othersName=(String) inputStream.readObject();
            setConnected(true);
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

    /**
     *  Inizia ad ascoltare i messaggi della controparte, i quali vengono trasferiti al receiver
     */
    public void startListening(){
            new Thread(() -> {
                while (connected) {
                    if (receiver!=null)
                        listen();
                }
                closeConnection();
            }).start();

    }

    /**
     * Invia alla controparte un oggetto
     * @param object oggetto da inviare
     */
    public void sendObject(Serializable object) {
        try {
            outputStream.writeObject(object);
        } catch (IOException e) {
           setConnected(false);
           closeConnection();
        }
    }

    /**
     *  Chiude la connessione
     */
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

    /**
     * @return il nome con cui si identifica la controparte
     */
    public String getOthersInfo(){
        return othersName;
    }

    /**
     * Imposta il soggetto destinatario dei messaggi ricevuti tramite la rete dalla controparte
     * @param receiver il destinatario
     */
    public void setReceiver(NetworkReceiver receiver) {
        this.receiver = receiver;
    }
}
