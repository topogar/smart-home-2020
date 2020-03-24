package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.processing.EventProcessor;
import ru.sbt.mipt.oop.serialization.SmartHomeDeserializer;
import ru.sbt.mipt.oop.serialization.SmartHomeJsonDeserializer;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHomeDeserializer deserializer = new SmartHomeJsonDeserializer();
        SmartHome smartHome = deserializer.deserialize();
        // начинаем обработку событий
        EventProcessor.process(smartHome);
    }
}
