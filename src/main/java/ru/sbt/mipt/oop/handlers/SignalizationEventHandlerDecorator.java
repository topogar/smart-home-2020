package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.sensor.SensorEvent;

import java.util.List;

public class SignalizationEventHandlerDecorator implements EventHandler {

    private final List<EventHandler> eventHandlers;

    public SignalizationEventHandlerDecorator(List<EventHandler> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    public void handleEvent(SmartHome smartHome, SensorEvent event) {

        if (smartHome.getSignalization().isActivated()) {
            smartHome.getSignalization().toAlarmMode();
        }

        if (smartHome.getSignalization().isAlarm()) {
            System.out.println("sending sms");
        } else {
            for (EventHandler eventHandler : eventHandlers) {
                eventHandler.handleEvent(smartHome, event);
            }
        }
    }
}
