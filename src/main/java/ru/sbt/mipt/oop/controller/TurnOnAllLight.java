package ru.sbt.mipt.oop.controller;

import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.SmartHome;

public class TurnOnAllLight implements Command {
    private final SmartHome smartHome;

    public TurnOnAllLight(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(component -> {
            if (component instanceof Light) {
                Light light = (Light) component;
                light.setOn(true);
            }
        });
    }
}