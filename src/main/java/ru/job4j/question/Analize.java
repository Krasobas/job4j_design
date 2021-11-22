package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        if (previous.equals(current)) {
            return new Info(added, changed, deleted);
        }
        Map<Integer, User> map = new HashMap<>();
        for (User user : previous) {
            map.put(user.getId(), user);
        }
        for (User user : current) {
            User temp = map.get(user.getId());
            if (temp != null) {
                changed = temp.getName().equals(user.getName()) ? changed : changed + 1;
            } else {
                added++;
            }
        }
        deleted = previous.size() - (current.size() - added);
        return new Info(added, changed, deleted);
    }

}
