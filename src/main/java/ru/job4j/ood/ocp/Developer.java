package ru.job4j.ood.ocp;

/**
 * This class shows some cases when OCP wasn't respected
 */
public class Developer {

    /**
     * @param language wrong parameter type, it should be an abstraction
     */
    public void code(String language) {
        if ("java".equals(language)) {
            int time = 0;
            while (time < 100) {
                System.out.println("Coding in java");
                if (time % 25 == 0) {
                    System.out.println("Scrolling instagram");
                }
            }
        } else if ("python".equals(language)) {
            System.out.println("Coding in python");
        }
    }
}
