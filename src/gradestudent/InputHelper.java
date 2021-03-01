package gradestudent;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHelper {
    
    /**
     * Input integer and validate value
     * 
     * @param min Minimum
     * @param max Maximum
     * @return Integer value
     */
    public static int inputVal(Scanner scanner, int min, int max) {
        int num;
        do {
            try {
                num = scanner.nextInt();
                if (num < min || num > max) {
                    System.out.printf("Please input a number in range (%d - %d): ", min, max);
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Please input a number: ");
                num = -1;
            }
        } while (num < min || num > max);
        return num;
    }
    
}
