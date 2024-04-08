package ru.job4j.kiss.fool;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Fool {
    private InputStream in;
    private PrintStream out;

    public Fool(InputStream in, PrintStream out) {
        this.in = in;
        this.out = out;
    }
    private String getAnswer(int value) {
        boolean isFizz = value % 3 == 0;
        boolean isBuzz = value % 5 == 0;
        String result;

        if (isFizz && isBuzz) {
            result = "FizzBuzz";
        } else if (isFizz) {
            result = "Fizz";
        } else if (isBuzz) {
            result = "Buzz";
        } else {
            result = String.valueOf(value);
        }
        return result;
    }
    public void run(int max) {
        out.println("Игра FizzBuzz.");
        int startAt = 1;
        try (Scanner input = new Scanner(in)) {
            while (startAt <= max) {
                out.println(getAnswer(startAt++));
                String answer = input.nextLine();
                if (!getAnswer(startAt++).equals(answer)) {
                    out.println("Ошибка. Начинай снова.");
                    startAt = 1;
                }
            }
        } catch (NoSuchElementException e) {
            out.println("Произошла ошибка, запустите игру заново.");
        }
    }



    public static void main(String[] args) {
        Fool fool = new Fool(System.in, System.out);
        fool.run(100);
    }
}