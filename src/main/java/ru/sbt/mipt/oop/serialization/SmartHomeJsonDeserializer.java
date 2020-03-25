package ru.sbt.mipt.oop.serialization;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.components.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SmartHomeJsonDeserializer implements SmartHomeDeserializer {
    @Override
    public SmartHome deserialize() throws IOException {
        // считываем состояние дома из файла, считаем что файл может быть только smart-home-1.js
        Gson gson = new Gson();
        String json;
        try {
            json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
        } catch (IOException e) {
            System.out.println(e);
            json = null;
        }
        SmartHome smartHome = gson.fromJson(json, SmartHome.class);
        return smartHome;
    }
}
