package ru.job4j.ood.srp;

public class Developer {
    public void code(String language) {
        if ("java".equals(language)) {
            System.out.println("Codding in java...");
            drinkCoffee();
            System.out.println("Codding in java...");


        } else if ("python".equals(language)) {
            drinkCoffee();
            System.out.println("Codding in python...");
            drinkCoffee();
        } else {
            drinkCoffee();
            study(language);
            drinkCoffee();
            System.out.println(String.format("Codding in %s", language));
        }
    }

    public void study(String language) {
        System.out.println(String.format("Studying %s", language));
    }

    public void drinkCoffee() {
        System.out.println("Drinking coffee...");
    }
}
