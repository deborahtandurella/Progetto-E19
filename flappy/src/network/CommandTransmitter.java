package network;

public class CommandTransmitter {
    private NetworkHandle network;

    public CommandTransmitter(NetworkHandle network) {
        this.network = network;
    }
    public void sendCommand(Command command){
        network.sendObject(command);
    }
}
