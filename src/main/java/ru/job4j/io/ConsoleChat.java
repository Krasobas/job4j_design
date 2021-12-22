package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ConsoleChat {

    private final String path;
    private final String botAnswers;
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";

    /**
     * @param path - имя файла, в который будет записан весь диалог между ботом и пользователем;
     * @param botAnswers - имя файла в котором находятся строки с ответами, которые будет использовать бот.
     */
    public ConsoleChat(String path, String botAnswers) {
        if (!Files.exists(Paths.get(botAnswers)) || Files.isDirectory(Paths.get(botAnswers))) {
            throw new IllegalArgumentException(botAnswers + " doesn't exist or isn't a file!");
        }
        if (!Pattern.matches(".+\\.[a-z]+", path)) {
            throw new IllegalArgumentException(path + " isn't a file!");
        }
        this.path = path;
        this.botAnswers = botAnswers;
    }
    /**
     * Метод содержит логику чата:
     * - пользователь вводит слово-фразу, программа берет случайную фразу из текстового файла и выводит в ответ.
     * - программа замолкает если пользователь вводит слово «стоп», при этом он может продолжать отправлять сообщения в чат.
     * - если пользователь вводит слово «продолжить», программа снова начинает отвечать.
     * - при вводе слова «закончить» программа прекращает работу.
     * - запись диалога, включая слова-команды стоп/продолжить/закончить должны быть записаны в текстовый лог.
     */
    public void run() {
        List<String> log = new LinkedList<>();
        List<String> answers = readPhrases();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {
            String userInput = "";
            boolean silence = false;
            while (!OUT.equals(userInput)) {
                userInput = in.readLine();
                log.add(userInput);
                if (STOP.equals(userInput) || OUT.equals(userInput)) {
                    silence = true;
                }
                if (CONTINUE.equals(userInput)) {
                    silence = false;
                }
                if (!silence) {
                    double randomIndex = Math.random() * answers.size();
                    String answer = answers.get((int) randomIndex);
                    log.add(answer);
                    System.out.println(answer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveLog(log);
        log.forEach(System.out::println);
    }
    /**
     * Метод читает фразы из файла @value botAnswers
     * и возвращает список строк @return answers.
     */
    private List<String> readPhrases() {
        List<String> answers = List.of();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            answers = in.lines().collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return answers;
    }
    /**
     * Метод сохраняет лог чата @param log в файл @value path.
     */
    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new FileWriter(path))) {
            log.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data/log.txt", "data/botAnswers.txt");
        cc.run();
    }
}
