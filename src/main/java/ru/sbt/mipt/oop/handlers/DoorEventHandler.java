package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;

import static ru.sbt.mipt.oop.constants.SensorEventType.DOOR_OPEN;

public class DoorEventHandler {
    public static void handleEvent(SmartHome smartHome, SensorEvent event) throws Exception {
        if (event.getType() == DOOR_OPEN) {
            changeState(smartHome, event, true);
        } else {
            changeState(smartHome, event, false);
        }
    }

    private static void changeState(SmartHome smartHome, SensorEvent event, boolean open) {
        smartHome.execute(component -> {
            if (component instanceof Room) {
                Room room = (Room) component;
                room.execute(roomComponent -> {
                    if (roomComponent instanceof Door) {
                        Door door = (Door) roomComponent;
                        if (door.getId().equals(event.getObjectId())) {
                            door.setOpen(open);
                            System.out.println("Door " + door.getId() + " was "
                                                       + (open ? "opened." : "closed."));
                        }
                    }
                });
            }
        });
    }

}
