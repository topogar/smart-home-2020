package ru.sbt.mipt.oop.adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.constants.SensorEventType;
import ru.sbt.mipt.oop.handlers.EventHandler;
import ru.sbt.mipt.oop.sensor.SensorEvent;

public class EventHandlerAdapter implements com.coolcompany.smarthome.events.EventHandler {
    private final EventHandler eventHandler;
    private final SmartHome smartHome;

    public EventHandlerAdapter(SmartHome smartHome, EventHandler eventHandler) {
        this.eventHandler = eventHandler;
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        if (event.getEventType().equals("LightIsOn")) {
            eventHandler.handleEvent(smartHome, new SensorEvent(SensorEventType.LIGHT_ON, event.getObjectId()));
        }
        if (event.getEventType().equals("LightIsOff")) {
            eventHandler.handleEvent(smartHome, new SensorEvent(SensorEventType.LIGHT_OFF, event.getObjectId()));
        }
        if (event.getEventType().equals("DoorIsOpen")) {
            eventHandler.handleEvent(smartHome, new SensorEvent(SensorEventType.DOOR_OPEN, event.getObjectId()));
        }
        if (event.getEventType().equals("DoorIsClosed")) {
            eventHandler.handleEvent(smartHome, new SensorEvent(SensorEventType.DOOR_CLOSED, event.getObjectId()));
        }
    }
}
