//UDP Clinet - 21MIS1146
import java.io.IOException;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            InetAddress host = InetAddress.getByName("localhost");
            int port = 8000;

            String message = "Hello 21MIS1146";
            byte[] data = message.getBytes();

            // Send data
            DatagramPacket request = new DatagramPacket(data, data.length, host, port);
            socket.send(request);

            // Receive response
            byte[] buffer = new byte[65536];
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            socket.receive(response);

            data = response.getData();
            message = new String(data, 0, response.getLength());
            System.out.println("Received message: " + message);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
