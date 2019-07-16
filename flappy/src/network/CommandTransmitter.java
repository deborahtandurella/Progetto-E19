package network;

public class CommandTransmitter {
    private ConnectionHandle network;

    public CommandTransmitter(ConnectionHandle network) {
        this.network = network;
    }
    public void sendCommand(Command command){
        network.sendObject(command);
    }
}
