//BGP Message Transfer Server - 21MIS1146
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BGPServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000); // Create a server socket on port 5000

            System.out.println("Waiting for a client connection...");

            Socket clientSocket = serverSocket.accept(); // Wait for a client to connect

            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

            // Receive the message from the client
            InputStream inputStream = clientSocket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);

            String receivedMessage = new String(buffer, 0, bytesRead);
            System.out.println("Received message: " + receivedMessage);

            // Process the received message as needed

            // Send a response back to the client
            OutputStream outputStream = clientSocket.getOutputStream();
            String responseMessage = "Message received!";
            outputStream.write(responseMessage.getBytes());

            // Close the connections
            inputStream.close();
            outputStream.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
