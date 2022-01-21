package ru.job4j.serialization;

import java.util.Arrays;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Student {
    private boolean scholarship;
    private int id;
    private String name;
    private String[] courses;
    private Contact contact;

    public Student(boolean scholarship, int id, String name, String[] courses, Contact contact) {
        this.scholarship = scholarship;
        this.id = id;
        this.name = name;
        this.courses = courses;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Student{"
                + "scholarship="
                + scholarship
                + ", id="
                + id
                + ", name='"
                + name
                + '\''
                + ", courses="
                + Arrays.toString(courses)
                + ", contact="
                + contact
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Student student = (Student) o;
        return scholarship == student.scholarship
                && id == student.id
                && name.equals(student.name)
                && Arrays.equals(courses, student.courses)
                && contact.equals(student.contact);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(scholarship, id, name, contact);
        result = 31 * result + Arrays.hashCode(courses);
        return result;
    }

    public static void main(String[] args) {
        Student origin = new Student(true,
                12345,
                "John Smith",
                new String[] {"Java programming", "Algorithms", "Design patterns"},
                new Contact(123, "88002600"));
        final Gson gson = new GsonBuilder().create();
        String studentJSON = gson.toJson(origin);
        System.out.println(studentJSON);
        Student copy = gson.fromJson(studentJSON, Student.class);
        System.out.println(copy.equals(origin));


    }
}

