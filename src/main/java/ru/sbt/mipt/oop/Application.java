package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.processing.EventProcessor;
import ru.sbt.mipt.oop.serialization.SmartHomeDeserializer;
import ru.sbt.mipt.oop.serialization.SmartHomeJsonDeserializer;

public class Application {

    public static void main(String... args) {

        SmartHomeDeserializer deserializer = new SmartHomeJsonDeserializer("smart-home-1.js");
        SmartHome smartHome = deserializer.deserialize();

        EventProcessor eventProcessor = new EventProcessor(smartHome);
        eventProcessor.process(smartHome);
    }
}
