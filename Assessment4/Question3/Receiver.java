//Receiver - 21MIS1146
import java.io.*;
import java.net.*;
import java.util.zip.CRC32;

public class Receiver {
    public static void main(String[] args) {
        try {
            // Create a server socket and start listening for incoming connections
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Waiting for incoming connection...");

            // Accept the connection from the sender
            Socket socket = serverSocket.accept();
            System.out.println("Connection established.");

            // Get the input stream to receive data
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            // Receive the message object
            Message receivedMessage = (Message) objectInputStream.readObject();

            // Get the message and checksum from the received message
            String message = receivedMessage.getMessage();
            long receivedChecksum = receivedMessage.getChecksum();

            // Calculate checksum on the receiver side
            CRC32 crc32 = new CRC32();
            crc32.update(message.getBytes());
            long calculatedChecksum = crc32.getValue();

            // Compare the received checksum with the calculated checksum
            if (receivedChecksum == calculatedChecksum) {
                System.out.println("Checksum verification successful. Message received without data loss.");
                System.out.println("Received message: " + message);
            } else {
                System.out.println("Checksum verification failed. Data loss detected during transmission.");
            }

            // Close the streams and socket
            objectInputStream.close();
            inputStream.close();
            socket.close();
            serverSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}