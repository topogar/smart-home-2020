package ru.sbt.mipt.oop.processing;

import ru.sbt.mipt.oop.handlers.*;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.components.SmartHome;

import java.util.List;

public class EventProcessor {

    private final EventHandler eventHandler;
    private final EventGetter getter;

    public EventProcessor(SmartHome smartHome, EventGetter getter, List<EventHandler> handlers) {
        this.eventHandler = new SignalizationEventHandlerDecorator(smartHome, handlers);
        this.getter = getter;
    }

    public void process() {
        SensorEvent event = getter.getNextSensorEvent();

        while (event != null) {
            eventHandler.handleEvent(event);
            event = getter.getNextSensorEvent();
        }
    }
}