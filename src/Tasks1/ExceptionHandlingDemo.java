package Tasks1;
import java.util.Scanner;

public class ExceptionHandlingDemo {
    public static void processInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Enter a number: ");
            double number = scanner.nextDouble();
            double reciprocal = 1 / number;
            System.out.println("Reciprocal: " + reciprocal);
        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero is not allowed.");
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: Invalid input. Please enter a numerical value.");
        } finally {
            scanner.close();
            System.out.println("Scanner closed.");
        }
    }

    public static void main(String[] args) {
        processInput();
    }
}
