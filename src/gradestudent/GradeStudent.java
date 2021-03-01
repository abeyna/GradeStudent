package gradestudent;

import java.util.Scanner;

/**
 * Main class
 * 
 * @author Luan N Nguyen
 * @since January 10th 2021
 * @version 1.0
 */
public class GradeStudent {

    /** Calculate weight for mid term */
    private static int weightMidterm;
    /** Calculate weight for final */
    private static int weightFinal;
    /** Calculate weight for homework */
    private static int weightHomework;

    /** Calculate final score for mid term */
    private static double scoreMidterm;
    /** Calculate final score for final term */
    private static double scoreFinal;
    /** Calculate final score for homework */
    private static double scoreHomework;

    /** Scanner for user to input data */
    private static Scanner sc;

    // Main method for program
    public static void main(String[] args) {
        sc = new Scanner(System.in);

        begin();
        midterm();
        finalterm();
        homework();
        report();

        sc.close();
    }

    /**
     * Show introducing
     */
    private static void begin() {

        System.out.println("Welcome! This program reads exam/homework scores and reports your overall course grade.");

    }

    /**
     * Calculate mid term score
     */
    private static void midterm() {

        System.out.println("Midterm:");

        System.out.print("Weight (0-100)? ");
        weightMidterm = InputHelper.inputVal(sc, 0, 100);

        System.out.print("Score earned? ");
        int scoreEarned = InputPossitive.inputPos(sc);

        System.out.print("Were scores shifted (1 = yes, 2 = no)? ");
        int shiftAmount = 0;
        int scoreShifted = InputHelper.inputVal(sc, 1, 2);
        if (scoreShifted == 1) {
            System.out.print("Shift amount? ");
            shiftAmount = InputPossitive.inputPos(sc);
        }

        // Using totalScore method to calculate totalPoint
        int totalPoint = totalScore(scoreEarned, shiftAmount);
        System.out.printf("Total point = %d/100%n ", totalPoint);

        // Using calculateScore method to calculate scoreMidterm
        scoreMidterm = calculateScore(totalPoint, 100, weightMidterm);
        System.out.printf("Weighted score = %.1f/%d%n%n", scoreMidterm, weightMidterm);

    }

    /**
     * Calculate final score
     */
    private static void finalterm() {

        System.out.println("Final:");

        System.out.print("Weight (0-100)? ");
        weightFinal = InputHelper.inputVal(sc, 0, 100);

        // Make sure that total inputs for weights do not more than 100
        while (weightFinal + weightMidterm > 100) {
            System.out.println("Total input for three of weight scores do not more than 100! Try again: ");
            weightFinal = InputHelper.inputVal(sc, 0, 100);
        }

        System.out.print("Score earned? ");
        int scoreEarned = InputPossitive.inputPos(sc);

        System.out.print("Were scores shifted (1 = yes, 2 = no)? ");
        int shiftAmount = 0;
        int scoreShifted = InputHelper.inputVal(sc, 1, 2);
        if (scoreShifted == 1) {
            System.out.print("Shift amount? ");
            shiftAmount = InputPossitive.inputPos(sc);
        }

        // Using totalScore method to calculate totalPoint
        int totalPoint = totalScore(scoreEarned, shiftAmount);
        System.out.printf("Total point = %d/100%n ", totalPoint);

        // Using calculateScore method to calculate scoreMidterm
        scoreFinal = calculateScore(totalPoint, 100, weightFinal);
        System.out.printf("Weighted score = %.1f/%d%n%n", scoreFinal, weightFinal);

    }

    /**
     * Calculate homework score
     */
    private static void homework() {

        System.out.println("Homework:");

        System.out.print("Weight (0-100)? ");
        weightHomework = InputHelper.inputVal(sc, 0, 100);

        // Make sure that total inputs for weights do not more than 100
        while (weightHomework + weightFinal + weightMidterm > 100
                || weightHomework + weightFinal + weightMidterm < 100) {
            System.out.print("Total input for three of weight scores do not more or less than 100! Try again: ");
            weightHomework = InputHelper.inputVal(sc, 0, 100);
        }

        System.out.print("Number of assignments? ");
        int assignments = InputPossitive.inputPos(sc);

        int totalPoints = 0;
        int totalMax = 0;
        // Counting input assignments
        for (int i = 1; i <= assignments; i++) {
            System.out.printf("Assignment %d score and max? ", i);
            int score = InputPossitive.inputPos(sc);
            int max = InputPossitive.inputPos(sc);
            totalPoints += score;
            totalMax += max;
        }

        if (totalPoints > 150) {
            totalPoints = 150;
        }
        if (totalMax > 150) {
            totalMax = 150;
        }

        System.out.print("How many sections did you attend? ");
        int sections = InputPossitive.inputPos(sc);

        int sectionPoints = sections * 5;
        if (sectionPoints > 30) {
            sectionPoints = 30;
        }

        System.out.println("Section points = " + sectionPoints + "/30");

        totalPoints += sectionPoints;
        totalMax += 30;
        System.out.printf("Total points = %d/%d%n", totalPoints, totalMax);

        // Using calculateScore method to calculate scoreHomework
        scoreHomework = calculateScore(totalPoints, totalMax, weightHomework);
        System.out.printf("Weighted score = %.1f/%d%n%n", scoreHomework, weightHomework);

    }

    /**
     * Display report
     */
    private static void report() {

        double totalGrade = scoreMidterm + scoreFinal + scoreHomework;
        System.out.printf("Overall percentage = %.1f%n", totalGrade);

        if (totalGrade >= 85) {
            System.out.println("Your grade will be at least: 3.0\nGreat job!!!");
        } else if (totalGrade < 85 && totalGrade >= 75) {
            System.out.println("Your grade will be at least: 2.0\nKeep going, we're almost there!!!");
        } else if (totalGrade < 75 && totalGrade >= 60) {
            System.out.println("Your grade will be at least: 0.7\nStay focus!!!");
        } else {
            System.out.println("Your grade will be at least: 0.0\nYou need to find a mentor!!!");
        }

    }

    /**
     * Calculate for total score
     * 
     * @param earned
     *                   User's score
     * @param shift
     *                   User's shift amount
     * @return Total score
     */
    private static int totalScore(int earned, int shift) {
        int total = earned + shift;
        if (total > 100) {
            total = 100;
        }
        return total;
    }

    /**
     * Calculate final score
     * 
     * @param total
     *                   User's total score
     * @param max
     *                   Maximum amount
     * @param weight
     *                   User's weight score
     * @return Final score
     */
    private static double calculateScore(int total, int max, int weight) {
        return (double) total / max * weight;
    }
}