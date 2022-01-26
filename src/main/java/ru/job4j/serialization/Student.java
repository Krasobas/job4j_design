package ru.job4j.serialization;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringWriter;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

    @XmlAttribute
    private boolean scholarship;

    @XmlAttribute
    private int id;

    @XmlAttribute
    private String name;

    @XmlElementWrapper(name = "courses")
    @XmlElement(name = "course")
    private String[] courses;

    @XmlElement
    private Contact contact;

    public Student() { }

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

    public static void main(String[] args) throws Exception {
        Student origin = new Student(true,
                12345,
                "John Smith",
                new String[] {"Java programming", "Algorithms", "Design patterns"},
                new Contact(123, "88002600"));

        /**
         * JSON serialization with GSON
         */
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String studentJSON = gson.toJson(origin);
        System.out.println(studentJSON);
        Student copy = gson.fromJson(studentJSON, Student.class);
        System.out.println(copy.equals(origin));
        String manualJSON = "{\"scholarship\":true,\"id\":12345,\"name\":\"John Smith\",\"courses\":[\"Java programming\", \"Algorithms\", \"Design patterns\"],\"contact\":{\"zipCode\":123,\"phone\":\"88002600\"}}";
        copy = gson.fromJson(manualJSON, Student.class);
        System.out.println(copy.equals(origin));
        /**
         * XML serialization with JAXB
         */
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String studentXML = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(origin, writer);
            studentXML = writer.getBuffer().toString();
            System.out.println(studentXML);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(studentXML)) {
            copy = (Student) unmarshaller.unmarshal(reader);
            System.out.println(copy.equals(origin));
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("./data/Student.xml"))) {
            copy = (Student) unmarshaller.unmarshal(reader);
            System.out.println(copy.equals(origin));
        }
    }
}

