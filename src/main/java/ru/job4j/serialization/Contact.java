package ru.job4j.serialization;

import java.io.*;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "contact")
public class Contact implements Serializable {
    private static final Logger LOG = LoggerFactory.getLogger(Contact.class.getName());
    private static final long serialVersionUID = 1L;

    @XmlAttribute
    private int zipCode;

    @XmlAttribute
    private String phone;

    public Contact() { }

    public Contact(int zipCode, String phone) {
        this.zipCode = zipCode;
        this.phone = phone;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode="
                + zipCode
                + ", phone='"
                + phone
                + '\''
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
        Contact contact = (Contact) o;
        return zipCode == contact.zipCode && Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, phone);
    }

    public static void main(String[] args) {
        Contact origin = new Contact(12345, "88002600");
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("./data/contact.json"))) {
            oos.writeObject(origin);
        } catch (IOException e) {
            LOG.error("Serialization failed!", e);
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./data/contact.json"))) {
            Contact copy = (Contact) ois.readObject();
            System.out.println(copy);
            System.out.println(origin.equals(copy));
        } catch (IOException | ClassNotFoundException e) {
            LOG.error("Deserialization failed!", e);
        }
    }
}
