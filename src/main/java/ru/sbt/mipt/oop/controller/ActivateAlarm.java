package ru.sbt.mipt.oop.controller;

import ru.sbt.mipt.oop.components.SmartHome;

public class ActivateAlarm implements Command {
    private final SmartHome smartHome;

    public ActivateAlarm(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.getSignalization().toAlarmMode();
    }
}