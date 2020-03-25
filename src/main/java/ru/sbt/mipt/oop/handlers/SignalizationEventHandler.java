package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.constants.SensorEventType;
import ru.sbt.mipt.oop.sensor.SensorEvent;

public class SignalizationEventHandler implements EventHandler {
    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == SensorEventType.ALARM_ACTIVATE) {
            smartHome.getSignalization().activate(event.getObjectId());
        }
        if (event.getType() == SensorEventType.ALARM_DEACTIVATE) {
            smartHome.getSignalization().deactivate(event.getObjectId());
        }
    }
}
