import java.util.Scanner;

public class EvenOddChecker {
    public static void main(String[] args) {
        
        // Create Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Ask user for input
        System.out.print("Enter an integer: ");
        int number = scanner.nextInt();

        // Check if number is even or odd using modulus operator
        if (number % 2 == 0) {
            System.out.println(number + " is Even.");
        } else {
            System.out.println(number + " is Odd.");
        }

        // Close the scanner to avoid resource leak
        scanner.close();
    }
}
