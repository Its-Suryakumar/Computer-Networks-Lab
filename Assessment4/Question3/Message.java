//Message Class
import java.io.Serializable;

public class Message implements Serializable {
    private final String message;
    private final long checksum;

    public Message(String message, long checksum) {
        this.message = message;
        this.checksum = checksum;
    }

    public String getMessage() {
        return message;
    }

    public long getChecksum() {
        return checksum;
    }
}
    