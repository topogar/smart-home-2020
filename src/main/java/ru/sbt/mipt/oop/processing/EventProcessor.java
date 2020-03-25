package ru.sbt.mipt.oop.processing;

import ru.sbt.mipt.oop.handlers.*;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.components.SmartHome;

import java.util.Arrays;
import java.util.List;

public class EventProcessor {
    public static void process(SmartHome smartHome) throws Exception {
        List<EventHandler> eventHandlers = Arrays.asList(new DoorEventHandler(),
                                                         new LightEventHandler(),
                                                         new HallDoorEventHandler(),
                                                         new SignalizationEventHandler());

        SensorEvent event = EventGetter.getNextSensorEvent();
        while (event != null) {
            EventHandler SafetyEventHandler = new SignalizationEventHandlerDecorator(eventHandlers);
            SafetyEventHandler.handleEvent(smartHome, event);
            event = EventGetter.getNextSensorEvent();
        }
    }
}
