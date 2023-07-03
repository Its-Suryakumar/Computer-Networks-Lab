//Broadcast Client - 21MIS1146
import java.io.IOException;
import java.net.*;

public class BroadcastClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(10000);
            byte[] buffer = new byte[65536];
            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);

            while (true) {
                socket.receive(incoming);
                byte[] data = incoming.getData();
                String message = new String(data, 0, incoming.getLength());
                System.out.println("Received message: " + message);
            }
        } catch (SocketException e) {
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
