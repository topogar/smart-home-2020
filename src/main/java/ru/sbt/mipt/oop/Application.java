package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.handlers.*;
import ru.sbt.mipt.oop.processing.EventProcessor;
import ru.sbt.mipt.oop.processing.EventGetter;
import ru.sbt.mipt.oop.serialization.SmartHomeDeserializer;
import ru.sbt.mipt.oop.serialization.SmartHomeJsonDeserializer;

import java.util.Arrays;
import java.util.List;

public class Application {

    public static void main(String... args) {

        SmartHomeDeserializer deserializer = new SmartHomeJsonDeserializer("smart-home-1.js");
        SmartHome smartHome = deserializer.deserialize();

        List<EventHandler> handlers = Arrays.asList(new DoorEventHandler(smartHome),
                                                    new LightEventHandler(smartHome),
                                                    new HallDoorEventHandler(smartHome),
                                                    new SignalizationEventHandler(smartHome));

        EventGetter getter = new EventGetter();
        EventProcessor eventProcessor = new EventProcessor(smartHome, getter, handlers);
        eventProcessor.process();
    }
}