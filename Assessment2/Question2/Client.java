//Cliet.java
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8080);
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream inputStream = new DataInputStream(socket.getInputStream());

            // Card number and PIN for login
            String loginCardNumber = "1234567890123456";
            String pin = "1234";

            // Withdrawal amount
            int withdrawalAmount = 2000;

            // Send card number and PIN to the server
            outputStream.writeUTF(loginCardNumber);
            outputStream.writeUTF(pin);

            // Receive the login result from the server
            String loginResult = inputStream.readUTF();

            if (loginResult.equals("SUCCESS")) {
                // Send the withdrawal amount to the server
                outputStream.writeInt(withdrawalAmount);

                // Receive the withdrawal result from the server
                String withdrawalResult = inputStream.readUTF();

                if (withdrawalResult.equals("SUCCESS")) {
                    // Receive the updated balance from the server
                    int updatedBalance = inputStream.readInt();
                    // Receive the customer name from the server
                    String customerName = inputStream.readUTF();
                    // Receive the customer account number from the server
                    String accountNumber = inputStream.readUTF();

                    System.out.println("Withdrawal successful!");
                    System.out.println("Customer: " + customerName);
                    System.out.println("Account Number: " + accountNumber);
                    System.out.println("Updated Balance: INR " + updatedBalance);
                } else if (withdrawalResult.equals("INSUFFICIENT_BALANCE")) {
                    System.out.println("Insufficient balance for withdrawal!");
                } else {
                    System.out.println("Withdrawal failed!");
                }
            } else if (loginResult.equals("INVALID_CREDENTIALS")) {
                System.out.println("Invalid card number or PIN!");
            } else {
                System.out.println("Login failed!");
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
