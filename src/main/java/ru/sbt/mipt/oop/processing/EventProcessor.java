package ru.sbt.mipt.oop.processing;

import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.handlers.SmartHomeEventHandler;

public class EventProcessor {
    public static void process(SmartHome smartHome) throws Exception {
        SensorEvent event = EventGetter.getNextSensorEvent();
        while (event != null) {
            SmartHomeEventHandler.handleEvent(smartHome, event);
            event = EventGetter.getNextSensorEvent();
        }
    }
}
