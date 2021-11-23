package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        matrixToFile(multiple(9));
    }

    public static int[][] multiple(int size) {
        int[][] result = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = (i + 1) * (j + 1);
            }
        }
        return result;
    }

    public static void matrixToFile(int[][] result) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    String output = result[i][j] + " ";
                    out.write(output.getBytes());
                    if (result[i][j] < 10) {
                        out.write(" ".getBytes());
                    }
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
