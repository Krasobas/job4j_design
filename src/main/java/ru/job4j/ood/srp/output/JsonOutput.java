package ru.job4j.ood.srp.output;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.Map;


public class JsonOutput implements Output {
    private final Gson builder;

    public JsonOutput() {
        builder = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public String print(List<Map<String, String>> data) {
        return builder.toJson(data);
    }
}
