package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.sensor.SensorEvent;

import java.util.List;

public class SignalizationEventHandlerDecorator implements EventHandler {

    private final SmartHome smartHome;
    private final List<EventHandler> eventHandlers;

    public SignalizationEventHandlerDecorator(SmartHome smartHome, List<EventHandler> eventHandlers) {
        this.smartHome = smartHome;
        this.eventHandlers = eventHandlers;
    }

    public void handleEvent(SensorEvent event) {

        if (smartHome.getSignalization().isActivated()) {
            smartHome.getSignalization().toAlarmMode();
        }

        if (smartHome.getSignalization().isAlarm()) {
            System.out.println("sending sms");
        } else {
            for (EventHandler eventHandler : eventHandlers) {
                eventHandler.handleEvent(event);
            }
        }
    }
}
