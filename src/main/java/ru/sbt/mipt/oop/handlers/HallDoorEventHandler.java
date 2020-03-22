package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.CommandSender;
import ru.sbt.mipt.oop.constants.CommandType;
import ru.sbt.mipt.oop.sensor.SensorCommand;
import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;

import static ru.sbt.mipt.oop.constants.SensorEventType.DOOR_CLOSED;

public class HallDoorEventHandler implements EventHandler{
    public static void handleEvent(SmartHome smartHome, SensorEvent event) throws Exception {
        if (event.getType() != DOOR_CLOSED) {
            return;
        }
        if (isHallDoor(smartHome, event.getObjectId())) {
            turnOffAllLights(smartHome);
        }

    }

    private static boolean isHallDoor(SmartHome smartHome, String objectId) throws Exception {
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(objectId)) {
                    return room.getName().equals("hall");
                }
            }
        }
        throw new Exception("No door with ID");
    }

    private static void turnOffAllLights(SmartHome smartHome) {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                CommandSender.sendCommand(command);
            }
        }
    }
}
