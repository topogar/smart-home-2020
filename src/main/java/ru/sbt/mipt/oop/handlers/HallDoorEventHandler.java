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
import static ru.sbt.mipt.oop.constants.SensorEventType.DOOR_OPEN;

public class HallDoorEventHandler implements EventHandler {

    private final SmartHome smartHome;

    public HallDoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void handleEvent(SensorEvent event) {
        if (event.getType() == DOOR_CLOSED) {
            handleHallDoor(event.getObjectId(), false);
        }
        if (event.getType() == DOOR_OPEN) {
            handleHallDoor(event.getObjectId(), true);
        }
    }

    private void handleHallDoor (String objectId, boolean open) {
        this.smartHome.execute(component -> {
            if (component instanceof Room) {
                Room room = (Room) component;
                if (room.getName().equals("hall")) {
                    room.execute(roomComponent -> {
                        if (roomComponent instanceof Door) {
                            Door door = (Door) roomComponent;
                            if (door.getId().equals(objectId)) {
                                door.setOpen(open);
                                if (!open) {
                                    turnOffAllLights();
                                }
                            }
                        }
                    });
                };
            }
        });
    }

    private void turnOffAllLights() {
        for (Room homeRoom : this.smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                CommandSender.sendCommand(command);
            }
        }
    }
}
