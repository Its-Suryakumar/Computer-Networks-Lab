//Main Program - Suryakumar 21MIS1146
public class IPv4OptionsLengthCalculator {
    public static void main(String[] args) {
        // Header length value in binary
        String headerLengthBinary = "1000";

        // Convert binary to decimal
        int headerLengthDecimal = Integer.parseInt(headerLengthBinary, 2);

        // Calculate number of bytes of options
        int optionsLength = (headerLengthDecimal * 4) - 20;

        System.out.println("Number of bytes of options: " + optionsLength);
    }
}
