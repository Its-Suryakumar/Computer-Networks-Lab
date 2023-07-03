//Server Checksum - 21MIS1146
import java.io.*;
import java.net.*;
import java.util.zip.CRC32;

public class Sender {
    public static void main(String[] args) {
        String message = "General instructions to the departments"; // Example message

        try {
            // Create a socket and connect to the receiver
            Socket socket = new Socket("localhost", 12345);

            // Get the output stream to send data
            OutputStream outputStream = socket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            // Calculate checksum
            CRC32 crc32 = new CRC32();
            crc32.update(message.getBytes());
            long checksum = crc32.getValue();

            // Create a message object with the message and checksum
            Message transmittedMessage = new Message(message, checksum);

            // Send the message object
            objectOutputStream.writeObject(transmittedMessage);
            objectOutputStream.flush();

            // Close the streams and socket
            objectOutputStream.close();
            outputStream.close();
            socket.close();

            System.out.println("Message sent successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}