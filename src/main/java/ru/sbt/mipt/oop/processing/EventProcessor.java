package ru.sbt.mipt.oop.processing;

import ru.sbt.mipt.oop.handlers.*;
import ru.sbt.mipt.oop.sensor.SensorEvent;

import java.util.List;

public class EventProcessor {

    private final List<EventHandler> handlers;
    private final EventGetter getter;

    public EventProcessor(EventGetter getter, List<EventHandler> handlers) {
         this.handlers = handlers;
         this.getter = getter;
    }

    public void process() {
        SensorEvent event = this.getter.getNextSensorEvent();

        while (event != null) {

            for (EventHandler handler : handlers) {
                handler.handleEvent(event);
            }

            event = this.getter.getNextSensorEvent();
        }
    }
}
