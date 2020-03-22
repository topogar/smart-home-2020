package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;

import static ru.sbt.mipt.oop.constants.SensorEventType.DOOR_OPEN;

public class DoorEventHandler {
    public static void handleEvent(SmartHome smartHome, SensorEvent event) throws Exception {
        Door door = findDoor(smartHome, event);

        if (event.getType() == DOOR_OPEN) {
            door.setOpen(true);
            System.out.println("Door " + door.getId() + " was opened.");
        } else {
            door.setOpen(false);
            System.out.println("Door " + door.getId() + " was closed.");
        }
    }

    private static Door findDoor(SmartHome smartHome, SensorEvent event) throws Exception {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    return door;
                }
            }
        }
        throw new Exception("No light with this ID");
    }
}
