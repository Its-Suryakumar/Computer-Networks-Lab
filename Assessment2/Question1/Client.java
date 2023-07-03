//Client.java
import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            // Send the n-value and the message to the server
            int n = 3;
            String message = "Suryakumar P 21MIS1146";
            outputStream.writeInt(n);
            outputStream.writeUTF(message);

            // Receive the encoded message from the server
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            String encodedMessage = inputStream.readUTF();
            System.out.println("Encoded Message: " + encodedMessage);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
