package ru.sbt.mipt.oop.controller;

import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;

public class TurnOnHallLight implements Command {
    private final SmartHome smartHome;

    public TurnOnHallLight(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(component -> {
            if (component instanceof Room) {
                Room room = (Room) component;
                if (room.getName().equals("hall")) {
                    room.execute(roomComponent -> {
                        if (roomComponent instanceof Light) {
                            Light light = (Light) roomComponent;
                            light.setOn(true);
                        }
                    });
                }

            }
        });
    }
}