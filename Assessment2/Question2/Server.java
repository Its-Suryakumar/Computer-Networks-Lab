//Server.java
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static Map<String, Customer> customers;

    static {
        // Initialize some customer details
        customers = new HashMap<>();
        customers.put("1234567890123456", new Customer("Suryakumar", "1234", 5000000, "AC-1234"));
        customers.put("9876543210987654", new Customer("Tony Stark", "5678", 10000, "AC-5678"));
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server listening on port 8080...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Handle client request in a separate thread
                Thread thread = new Thread(new ClientHandler(clientSocket));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

                // Read card number and PIN from the client
                String cardNumber = inputStream.readUTF();
                String pin = inputStream.readUTF();

                // Check if customer exists and validate the PIN
                if (customers.containsKey(cardNumber) && customers.get(cardNumber).validatePin(pin)) {
                    Customer customer = customers.get(cardNumber);
                    outputStream.writeUTF("SUCCESS"); // PIN validation successful

                    // Read the withdrawal amount from the client
                    int withdrawalAmount = inputStream.readInt();

                    // Check if the withdrawal amount is within the available balance
                    if (withdrawalAmount <= customer.getBalance()) {
                        customer.withdraw(withdrawalAmount); // Update the customer's balance
                        outputStream.writeUTF("SUCCESS"); // Withdrawal successful
                        outputStream.writeInt(customer.getBalance()); // Send updated balance to the client
                        outputStream.writeUTF(customer.getName()); // Send customer name to the client
                        outputStream.writeUTF(customer.getAccountNumber()); // Send customer account number to the client
                    } else {
                        outputStream.writeUTF("INSUFFICIENT_BALANCE"); // Insufficient balance
                    }
                } else {
                    outputStream.writeUTF("INVALID_CREDENTIALS"); // Invalid card number or PIN
                }

                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class Customer {
        private String name;
        private String pin;
        private int balance;
        private String accountNumber;

        public Customer(String name, String pin, int balance, String accountNumber) {
            this.name = name;
            this.pin = pin;
            this.balance = balance;
            this.accountNumber = accountNumber;
        }

        public boolean validatePin(String pin) {
            return this.pin.equals(pin);
        }

        public void withdraw(int amount) {
            this.balance -= amount;
        }

        public int getBalance() {
            return balance;
        }

        public String getName() {
            return name;
        }

        public String getAccountNumber() {
            return accountNumber;
        }
    }
}
