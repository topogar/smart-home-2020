package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;

import static ru.sbt.mipt.oop.constants.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.constants.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements EventHandler {

    private final SmartHome smartHome;

    public DoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void handleEvent(SensorEvent event) {
        if (event.getType() == DOOR_OPEN) {
            changeState(event, true);
        }
        if (event.getType() == DOOR_CLOSED) {
            changeState(event, false);
        }
    }

    private void changeState(SensorEvent event, boolean open) {
        this.smartHome.execute(component -> {
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
