package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        String[] result = null;
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            result = text.toString().split(System.lineSeparator());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result != null) {
            for (String num : result) {
                System.out.println(Integer.parseInt(num) % 2 == 0);
            }
        }
    }
}
