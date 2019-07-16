package network;

/**
 *  NetworkReceiver è in grado di ricevere messaggi generici
 */
public interface NetworkReceiver {
    void receive(Object message);
}
