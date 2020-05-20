package ru.sbt.mipt.oop.adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.constants.SensorEventType;
import ru.sbt.mipt.oop.handlers.EventHandler;
import ru.sbt.mipt.oop.sensor.SensorEvent;

import java.util.List;
import java.util.Map;

public class EventHandlerAdapter implements com.coolcompany.smarthome.events.EventHandler {
    private final EventHandler handler;
    private final Map<String, SensorEventType> CCTypes;

    public EventHandlerAdapter(EventHandler eventHandler, Map<String, SensorEventType> CCTypes) {
        this.handler = eventHandler;
        this.CCTypes = CCTypes;
    }

    private SensorEvent getSensorFromCC(CCSensorEvent event) {
        if (CCTypes.containsKey(event.getEventType()))
            return new SensorEvent(CCTypes.get(event.getEventType()), event.getEventType());
        else
            return null;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = getSensorFromCC(event);

        if (sensorEvent == null) {
            return;
        }

        handler.handleEvent(sensorEvent);
    }
}
