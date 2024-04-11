package ru.job4j.ood.lsp;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Developer {
    protected int experience;
    protected String language;

    public Developer() {
        experience = 0;
    }
    public void code(int storyPoint) {
        int time = 0;
        do {
            System.out.println(String.format("Coding in %s", language));
            time++;
        } while (storyPoint - (experience + time) > 0);
        experience++;
    }
    public void leadTeam() {
        if (experience >= 5) {
            System.out.println(String.format("Leading team of %s developers", language));
        }
    }

    public boolean test(String code) {
        return code.contains("error");
    }

}
