package gradestudent;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputPossitive {

    /**
     * Input integer and validate value
     * 
     * @param min
     *                Minimum
     * @param max
     *                Maximum
     * @return Integer value
     */
    public static int inputPos(Scanner scanner) {
        int num;
        do {
            try {
                num = scanner.nextInt();
                if (num < 0) {
                    System.out.print("Number do not less than 0! Try again: ");
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.print("Please input a number: ");
                num = -1;
            }
        } while (num < 0);
        return num;
    }

}
