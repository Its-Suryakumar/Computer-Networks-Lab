//UDP Server - 21MIS1146
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class UDPServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(8000);
            byte[] buffer = new byte[65536];
            DatagramPacket incoming = new DatagramPacket(buffer, buffer.length);

            while (true) {
                socket.receive(incoming);
                byte[] data = incoming.getData();
                String message = new String(data, 0, incoming.getLength());
                System.out.println("Received message: " + message);

                // Calculate age
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                LocalDate dob = LocalDate.parse(message, formatter);
                LocalDate now = LocalDate.now();
                Period period = Period.between(dob, now);
                int age = period.getYears();
                String responseMessage = "Your age is " + age + " years";

                // Send response
                byte[] responseData = responseMessage.getBytes();
                DatagramPacket response = new DatagramPacket(responseData, responseData.length, incoming.getAddress(), incoming.getPort());
                socket.send(response);
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
