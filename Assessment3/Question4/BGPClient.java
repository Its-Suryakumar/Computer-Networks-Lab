//BGP Message Transfer Client - 21MIS1146
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class BGPClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000); // Connect to the server at localhost:5000

            // Send a message to the server
            OutputStream outputStream = socket.getOutputStream();
            String message = "Hello AS1!";
            outputStream.write(message.getBytes());

            // Receive the response from the server
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);

            String responseMessage = new String(buffer, 0, bytesRead);
            System.out.println("Server response: " + responseMessage);

            // Close the connections
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
