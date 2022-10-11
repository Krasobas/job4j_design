package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Emulator {
    public static final int SET_DIRECTORY = 1;
    public static final int UPLOAD_FILE = 2;
    public static final int DOWNLOAD_FILE = 3;

    public static final String MENU = """
                Введите 1, чтобы указать кэшируемую директорию.
                Введите 2, загрузить содержимое файла в кэш.
                Введите 3, получить содержимое файла из кэша.
                Введите любое другое число для выхода.
            """;

    public static final String SETTING = "=== Установка директории ===";
    public static final String UPLOADING = "=== Загрузка файла в кэш ===";
    public static final String DOWNLOADING = "=== Выгрузка файла из кэша ===";
    public static final String PRINTING = "=== Печать содержимого файла ===";



    public static final String SELECT = "Выберите меню";
    public static final String DIRECTORY = "Укажите путь к директории";
    public static final String NO_DIRECTORY = "Необходимо указать кэшируемую директорию";
    public static final String FILE_NAME = "Укажите имя файла";
    public static final String WRONG_PATH = "Вы указали неверный путь.";
    public static final String EXIT = "Конец работы";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        start(scanner);

    }

    private static void start(Scanner scanner) {
        boolean run = true;
        DirFileCache cache = null;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (SET_DIRECTORY == userChoice) {
                System.out.println(SETTING);
                System.out.println(DIRECTORY);
                String path = scanner.nextLine();
                if (Files.isDirectory(Path.of(path))) {
                    cache = new DirFileCache(path);
                } else {
                    System.out.println(WRONG_PATH);
                }

            } else if (UPLOAD_FILE == userChoice) {
                System.out.println(UPLOADING);
                if (cache != null) {
                    System.out.println(FILE_NAME);
                    String path = scanner.nextLine();
                    cache.put(path, "");
                } else {
                    System.out.println(NO_DIRECTORY);
                }

            } else if (DOWNLOAD_FILE == userChoice) {
                System.out.println(DOWNLOADING);
                if (cache != null) {
                    System.out.println(FILE_NAME);
                    String path = scanner.nextLine();
                    System.out.println(PRINTING);
                    System.out.println(cache.get(path));
                } else {
                    System.out.println(NO_DIRECTORY);
                }

            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }
}
