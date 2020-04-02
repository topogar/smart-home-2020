package ru.sbt.mipt.oop.controller;

import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;

public class CloseHallDoor implements Command {
    private final SmartHome smartHome;

    public CloseHallDoor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {

        smartHome.execute(component -> {
            if (component instanceof Room) {
                Room room = (Room) component;
                if (room.getName().equals("hall")) {
                    room.execute(roomComponent -> {
                        if (roomComponent instanceof Door) {
                            Door door = (Door) roomComponent;
                            door.setOpen(false);
                        }
                    });
                }

            }
        });
    }
}