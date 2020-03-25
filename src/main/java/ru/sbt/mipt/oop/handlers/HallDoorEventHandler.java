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

public class HallDoorEventHandler implements EventHandler {
    @Override
    public void handleEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() != DOOR_CLOSED) {
            return;
        }
        if (isHallDoor(smartHome, event.getObjectId())) {
            turnOffAllLights(smartHome);
        }

    }

    private static boolean isHallDoor(SmartHome smartHome, String objectId) {
        final boolean[] isHallDoor = {false};
        smartHome.execute(component -> {
            if (component instanceof Room) {
                Room room = (Room) component;
                if (room.getName().equals("hall")) {
                    room.execute(roomComponent -> {
                        if (roomComponent instanceof Door) {
                            Door door = (Door) roomComponent;
                            if (door.getId().equals(objectId)) {
                                isHallDoor[0] = true;
                                door.setOpen(false);
                            }
                        }
                    });
                };
            }
        });
       return isHallDoor[0]; // на лекции так показывалось
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
