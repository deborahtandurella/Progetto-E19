package resources;

public class InvalidKeyException extends RuntimeException {
    public InvalidKeyException() {
        super("Specified key not found in config file");
    }

    public InvalidKeyException(String message) {
        super(message);
    }
}
