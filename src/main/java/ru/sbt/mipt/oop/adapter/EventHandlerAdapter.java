package ru.sbt.mipt.oop.adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.constants.SensorEventType;
import ru.sbt.mipt.oop.handlers.EventHandler;
import ru.sbt.mipt.oop.sensor.SensorEvent;

import java.util.List;
import java.util.Map;

public class EventHandlerAdapter implements com.coolcompany.smarthome.events.EventHandler {
    private final List<EventHandler> handlers;
    private final Map<String, SensorEventType> CCS2Sensor;

    public EventHandlerAdapter(List<EventHandler> handlers, Map<String, SensorEventType> CCS2Sensor) {
        this.handlers = handlers;
        this.CCS2Sensor = CCS2Sensor;
    }

    private SensorEvent getSensorFromCCS(CCSensorEvent event) {
        if (CCS2Sensor.containsKey(event.getEventType()))
            return new SensorEvent(CCS2Sensor.get(event.getEventType()), event.getEventType());
        else
            return null;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent sensorEvent = getSensorFromCCS(event);

        if (sensorEvent == null) {
            return;
        }

        for (EventHandler eventHandler : handlers) {
            eventHandler.handleEvent(sensorEvent);
        }
    }
}
