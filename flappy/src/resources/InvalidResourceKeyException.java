package resources;

public class InvalidResourceKeyException extends RuntimeException {
    public InvalidResourceKeyException(ResourcePack pack, Resource resource) {
        super("Key " + resource + " not found in resource pack " + pack);
    }
    public InvalidResourceKeyException(ResourcePack pack) {
        super("Resource pack " + pack + " not found ");
    }
    public InvalidResourceKeyException(String message) {
        super(message);
    }
}
