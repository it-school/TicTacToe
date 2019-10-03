package itschool;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static final int N = 6;
    static int field[][];
    static Scanner scanner;

    public static void main(String[] args) {
        int stepCounter = 0;
        field = new int[N][N];
        scanner = new Scanner(System.in);

        boolean isWinner = false;
        do {
            stepCounter++;
            System.out.println(stepCounter);
            if (stepCounter <= N * N) {
                move(stepCounter);
            } else
                break;
            drawField();
            isWinner = findWinner();
        } while (isWinner == false);
        if (isWinner)
            System.out.println((stepCounter % 2 == 1 ? "X" : "O") + " is winner!");
        else
            System.out.println("Draw!");
    }

    private static boolean findWinner() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N - 1; col++) {
                if (field[row][col] > 0 && field[row][col + 1] > 0) {
                    if (field[row][col] != field[row][col + 1])
                        break;
                    else if (col == N - 1 - 1 && field[row][col] > 0) {
                        System.out.println("Hor: " + (row + 1));
                        return true;
                    }
                } else
                    break;
            }
        }

        for (int col = 0; col < N; col++) {
            for (int row = 0; row < N - 1; row++) {
                if (field[row][col] > 0 && field[row + 1][col] > 0) {
                    if (field[row][col] != field[row + 1][col])
                        break;
                    else if (row == N - 1 - 1 && field[row][col] > 0) {
                        System.out.println("Ver: " + (col + 1));
                        return true;
                    }
                } else
                    break;
            }
        }

        exit:
        for (int col = 0; col < N; col++) {
            if (field[col][col] > 0 && field[col + 1][col + 1] > 0) {
                if (field[col][col] != field[col + 1][col + 1])
                    break exit;
                else if (col == N - 1 - 1 && field[col][col] > 0) {
                    System.out.println("DiagM: ");
                    return true;
                }
            } else
                break exit;
        }

        exit:
        for (int row = 0; row < N - 1; row++) {
            for (int col = 0; col < N; col++) {
                if (row + col == N - 1) {
                    if (field[row][col] > 0 && field[row + 1][col - 1] > 0) {
                        if (field[row][col] != field[row + 1][col - 1])
                            break exit;
                        else if (row == N - 1 - 1 && field[row][col] > 0) {
                            System.out.println("DiagS: ");
                            return true;
                        }
                    } else
                        break exit;
                }
            }
        }

        return false;
    }

    static void move(int step) {
        int row = -1, col = -1;
        Random random = new Random();
        System.out.println(step % 2 == 1 ? "Computer moves" : "Human, ");
        do {
            if (step % 2 == 1) {
                row = random.nextInt(N);
                col = random.nextInt(N);
            }
            else
            {
                System.out.print("input new coordinates: ");
                row = scanner.nextInt();
                col = scanner.nextInt();
            }
        } while (row >= N || row < 0 || col >= N || col < 0 || field[row][col] != 0);
        field[row][col] = step % 2 == 1 ? 1 : 2;
    }

    static void drawField() {
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (field[row][col] == 1)
                    System.out.print("X ");
                else if (field[row][col] == 2)
                    System.out.print("O ");
                else
                    System.out.print("  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}