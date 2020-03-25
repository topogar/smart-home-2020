package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.components.SmartHome;

public interface EventHandler {
    void handleEvent(SmartHome smartHome, SensorEvent event);
}

