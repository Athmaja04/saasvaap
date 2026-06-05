package Day2;

import java.util.InputMismatchException;
import java.util.Scanner;

class DivideZeroException extends Exception {
    DivideZeroException(String message) {
        super(message);
    }
}

public class Calculator {

    public static void add(int a, int b) {
        System.out.println("Result = " + (a + b));
    }

    public static void sub(int a, int b) {
        System.out.println("Result = " + (a - b));
    }

    public static void mul(int a, int b) {
        System.out.println("Result = " + (a * b));
    }

    public static void div(int a, int b) throws DivideZeroException {
        if (b == 0) {
            throw new DivideZeroException("Cannot divide by zero");
        }
        System.out.println("Result = " + (a / b));
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            try {
                System.out.print("\n1. ADD\n2. SUBTRACT\n3. DIVIDE\n4. MULTIPLY\n5. QUIT\nEnter your choice: ");
                int ch = sc.nextInt();

                if (ch == 5) {
                    System.out.println("Exiting Program...");
                    break;
                }

                if (ch < 1 || ch > 5) {
                    System.out.println("Invalid Choice");
                    continue;
                }

                System.out.print("Enter 2 Numbers: ");
                int a = sc.nextInt();
                int b = sc.nextInt();

                switch (ch) {

                    case 1:
                        add(a, b);
                        break;

                    case 2:
                        sub(a, b);
                        break;

                    case 3:
                        try {
                            div(a, b);
                        } catch (DivideZeroException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 4:
                        mul(a, b);
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter valid integers only.");
                sc.nextLine();
            }
        }

        sc.close();
    }
}