package test;

import network.ConnectionHandle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConnectionHandleTest  {
    private ConnectionHandle clientHandle;
    private ConnectionHandle serverHandle;
    private static final String clientName= "Francesco";
    private static final String serverName= "Antonio";

    public ConnectionHandleTest(){

    }
    @Test
    public void checkOthersInfo() {

        serverHandle= new ConnectionHandle();
        clientHandle= new ConnectionHandle();
        (new Thread(() -> serverHandle.setConnection(4444, serverName))).start();
        clientHandle.setConnection("127.0.0.1", 4444, clientName);
        assertEquals(serverHandle.getOthersInfo(), clientName);
        assertEquals(clientHandle.getOthersInfo(), serverName);
    }


}