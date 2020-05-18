package ru.sbt.mipt.oop.processing;

import ru.sbt.mipt.oop.CommandSender;
import ru.sbt.mipt.oop.handlers.*;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.components.SmartHome;

import java.util.Arrays;
import java.util.List;

public class EventProcessor {
    private final List<EventHandler> handlers;
    private final EventHandler safetyEventHandler;
    private final EventGetter getter;

    public EventProcessor(SmartHome smartHome, EventGetter getter) {
        this.handlers = Arrays.asList(new DoorEventHandler(smartHome),
                new LightEventHandler(smartHome),
                new HallDoorEventHandler(smartHome),
                new SignalizationEventHandler(smartHome));
        this.safetyEventHandler = new SignalizationEventHandlerDecorator(smartHome, handlers);
        this.getter = getter;
    }

    public void process() {
        SensorEvent event = getter.getNextSensorEvent();

        while (event != null) {
            safetyEventHandler.handleEvent(event);
            event = getter.getNextSensorEvent();
        }
    }
}