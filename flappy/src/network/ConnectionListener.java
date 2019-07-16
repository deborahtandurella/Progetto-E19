package network;

/**
 *  Un connectionListener riceve update sullo stato della connessione di cui è ascoltatore
 */
public interface ConnectionListener {
    /**
     * @param connected lo stato della connessione
     */
    void connectionStatus(boolean connected);
}
