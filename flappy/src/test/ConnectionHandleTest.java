package test;

import network.ConnectionHandle;
import network.ConnectionListener;
import network.NetworkReceiver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


 class ConnectionHandleTest  {
    private ConnectionHandle clientHandle;
    private ConnectionHandle serverHandle;
    private static final String clientName= "Francesco";
    private static final String serverName= "Antonio";

     ConnectionHandleTest(){

    }
    @Test
     void checkOthersInfo() {

        serverHandle= new ConnectionHandle();
        clientHandle= new ConnectionHandle();
        (new Thread(() -> serverHandle.setConnection(4444, serverName))).start();
        clientHandle.setConnection("127.0.0.1", 4444, clientName);
        assertEquals(serverHandle.getOthersInfo(), clientName);
        assertEquals(clientHandle.getOthersInfo(), serverName);
    }

    @Test
     void checkConnectionStatusWorking(){
        serverHandle= new ConnectionHandle();
        clientHandle= new ConnectionHandle();
        ConcreteConnectionListener listener = new ConcreteConnectionListener();
        clientHandle.addConnectionListener(listener);
        (new Thread(() -> serverHandle.setConnection(4444, serverName))).start();
        clientHandle.setConnection("127.0.0.1", 4444, clientName);
        assertTrue(listener.isConnectionWorking());
    }
    @Test
     void checkConnectionStatusNotWorking(){
        serverHandle= new ConnectionHandle();
        clientHandle= new ConnectionHandle();
        ConcreteConnectionListener listener = new ConcreteConnectionListener();
        clientHandle.addConnectionListener(listener);
        (new Thread(() -> serverHandle.setConnection(4444, serverName))).start();
        clientHandle.setConnection("127.0.0.1", 4444, clientName);
        clientHandle.closeConnection();
        assertTrue(listener.isConnectionWorking());
    }

    @Test
     void checkSendObject(){
        String message = "Messaggio";
        serverHandle= new ConnectionHandle();
        clientHandle= new ConnectionHandle();
        (new Thread(() -> serverHandle.setConnection(4444, serverName))).start();
        clientHandle.setConnection("127.0.0.1", 4444, clientName);
        ConcreteNetworkReceiver receiver = new ConcreteNetworkReceiver();
        clientHandle.setReceiver(receiver);
        clientHandle.startListening();
        serverHandle.sendObject(message);
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(message, receiver.getReceivedMessage());
    }

    class ConcreteConnectionListener implements ConnectionListener{
        private boolean connectionWorking;
        @Override
        public void connectionStatus(boolean connected) {
            connectionWorking=connected;
        }

         boolean isConnectionWorking() {
            return connectionWorking;
        }
    }

    class ConcreteNetworkReceiver implements NetworkReceiver{
        private String receivedMessage;
        @Override
        public void receive(Object message) {
            receivedMessage = (String) message;
        }

         String getReceivedMessage() {
            return receivedMessage;
        }
    }

}