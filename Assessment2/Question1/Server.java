//Server.java
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server {

    // Function to generate the nth prime number
    private static int generateNthPrime(int n) {
        List<Integer> primes = new ArrayList<>();
        primes.add(2);

        int num = 3;
        while (primes.size() < n) {
            boolean isPrime = true;
            for (int prime : primes) {
                if (num % prime == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(num);
            }
            num += 2;
        }
        return primes.get(n - 1);
    }

    // Function to encode the message
    private static String encodeMessage(String message, int n) {
        StringBuilder encodedMessage = new StringBuilder();
        int prime = generateNthPrime(n);
        for (char c : message.toCharArray()) {
            int ascii = (int) c;
            int encodedAscii = ascii % prime;
            encodedMessage.append(encodedAscii).append(" ");
        }
        return encodedMessage.toString().trim();
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server listening on port 8080...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Receive the n-value and the message from the client
                DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
                int n = inputStream.readInt();
                String message = inputStream.readUTF();

                // Encode the message
                String encodedMessage = encodeMessage(message, n);
                System.out.println("Received Message: " + message);
                System.out.println("Encoded Message: " + encodedMessage);

                // Send the encoded message back to the client
                DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
                outputStream.writeUTF(encodedMessage);

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
