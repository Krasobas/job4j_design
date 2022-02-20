package ru.job4j.spammer;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class ImportDB implements AutoCloseable {

    private Properties cfg;
    private String dump;
    private Connection cnt;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    private void initConnection() {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            this.cnt = DriverManager.getConnection(
                    cfg.getProperty("jdbc.url"),
                    cfg.getProperty("jdbc.username"),
                    cfg.getProperty("jdbc.password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createTable() {
        try (Statement st = cnt.createStatement()) {
            String sql = String.format("create table if not exists users (%s, %s, %s);",
                    "id serial primary key",
                    "name varchar(255)",
                    "email varchar(255)"
            );
            st.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            users = rd.lines()
                      .filter(s -> !s.isEmpty())
                      .map(s -> s.split(";"))
                      .filter(args -> args.length == 2)
                      .map(args -> new User(args[0], args[1]))
                      .collect(Collectors.toCollection(ArrayList::new));
        }
        return users;
    }

    public void save(List<User> users) throws SQLException {
        initConnection();
        createTable();
        for (User user : users) {
            try (PreparedStatement ps = cnt.prepareStatement("insert into users (name, email) values (?, ?);")) {
                ps.setString(1, user.name);
                ps.setString(2, user.email);
                ps.execute();
            }
        }
        showTable();
    }

    public void showTable() {
        if (cnt == null) {
            initConnection();
        }
        var rowSeparator = "-".repeat(53).concat(System.lineSeparator());
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        try (var statement = cnt.createStatement()) {
            var resultSet = statement.executeQuery(
                    "select * from users"
            );
            var metaData = resultSet.getMetaData();
            buffer.add(String.format("%-5s|%-20s|%-25s|%n",
                    metaData.getColumnName(1),
                    metaData.getColumnName(2),
                    metaData.getColumnName(3)));
            while (resultSet.next()) {
                buffer.add(String.format("%-5s|%-20s|%-25s|%n",
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(buffer);
    }

    @Override
    public void close() throws Exception {
        if (cnt != null) {
            cnt.close();
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("./app.properties")) {
            cfg.load(in);
            try (ImportDB db = new ImportDB(cfg, "./dump.txt")) {
                db.save(db.load());
            }
        }
    }
}
