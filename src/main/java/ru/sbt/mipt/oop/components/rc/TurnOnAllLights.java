package ru.sbt.mipt.oop.components.rc;

import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.SmartHome;

public class TurnOnAllLights implements Command {
    private final SmartHome smartHome;

    public TurnOnAllLights(SmartHome smartHome) {
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