package ru.job4j.gc.leak;

import java.util.Random;
import java.util.Scanner;
import java.util.StringJoiner;

public class Menu {

    public static final Integer ADD_POST = 1;
    public static final Integer ADD_MANY_POST = 2;
    public static final Integer SHOW_ALL_POSTS = 3;
    public static final Integer DELETE_POST = 4;

    public static final String SELECT = "Выберите меню";
    public static final String COUNT = "Выберите количество создаваемых постов";
    public static final String TEXT_OF_POST = "Введите текст";
    public static final String EXIT = "Конец работы";
    public static final String ID_FOR_DELETE = "Удаление всех постов...";

    public static final String MENU = new StringJoiner(System.lineSeparator()).add(
                "Введите 1 для создание поста.")
            .add("Введите 2, чтобы создать определенное количество постов.")
            .add("Введите 3, чтобы показать все посты.")
            .add("Введите 4, чтобы удалить все посты.")
            .add("Введите любое другое число для выхода.")
            .toString();

    public static void main(String[] args) {
        Random random = new Random();
        UserGenerator userGenerator = new UserGenerator(random);
        CommentGenerator commentGenerator = new CommentGenerator(random, userGenerator);
        Scanner scanner = new Scanner(System.in);
        PostStore postStore = new PostStore();
        start(commentGenerator, scanner, userGenerator, postStore);
    }

    private static void start(CommentGenerator commentGenerator, Scanner scanner, UserGenerator userGenerator, PostStore postStore) {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println(SELECT);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (ADD_POST == userChoice) {
                System.out.println(TEXT_OF_POST);
                String text = scanner.nextLine();
                //условие на пустоту, перед генерацией
                if (UserGenerator.getUsers().isEmpty()) {
                    userGenerator.generate();
                }
                //условие на пустоту, перед генерацией
                if (CommentGenerator.getComments().isEmpty()) {
                    commentGenerator.generate();
                }
                postStore.add(new Post(text, CommentGenerator.getComments()));
            } else if (ADD_MANY_POST == userChoice) {
                System.out.println(TEXT_OF_POST);
                String text = scanner.nextLine();
                System.out.println(COUNT);
                String count = scanner.nextLine();
                for (int i = 0; i < Integer.parseInt(count); i++) {
                    createPost(commentGenerator, userGenerator, postStore, text);
                }
            } else if (SHOW_ALL_POSTS == userChoice) {
                System.out.println(PostStore.getPosts());
            } else if (DELETE_POST == userChoice) {
                System.out.println(ID_FOR_DELETE);
                postStore.removeAll();
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }

    private static void createPost(CommentGenerator commentGenerator,
                                   UserGenerator userGenerator, PostStore postStore, String text) {
        //условие на пустоту, перед генерацией
        if (UserGenerator.getUsers().isEmpty()) {
            userGenerator.generate();
        }
        //условие на пустоту, перед генерацией
        if (CommentGenerator.getComments().isEmpty()) {
            commentGenerator.generate();
        }
        postStore.add(new Post(text, CommentGenerator.getComments()));
    }
}
//- запускаю программу и жду 30 секунд;
//
//        - создаю 100 постов, снова жду 30 секунд;
//
//        - удаляю все посты;
//
//        - через 30 секунд создаю 100 постов и жду минуту.
//
//        Общая работа программы  – 2,5 минуты.