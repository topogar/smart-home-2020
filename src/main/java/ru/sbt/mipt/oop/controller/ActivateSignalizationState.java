package ru.sbt.mipt.oop.controller;

import ru.sbt.mipt.oop.components.SmartHome;

public class ActivateSignalizationState implements Command {
    private final SmartHome smartHome;
    private final String code;

    public ActivateSignalizationState(SmartHome smartHome, String code) {
        this.smartHome = smartHome;
        this.code = code;
    }

    @Override
    public void execute() {
        smartHome.getSignalization().activate(code);
    }
}