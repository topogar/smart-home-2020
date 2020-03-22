package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;

import static ru.sbt.mipt.oop.constants.SensorEventType.LIGHT_ON;

public class LightEventHandler {
    public static void handleEvent(SmartHome smartHome, SensorEvent event) throws Exception {
        Light light = findLight(smartHome, event);

        if (event.getType() == LIGHT_ON) {
            light.setOn(true);
            System.out.println("Light " + light.getId() + " in room " + " was turned on.");
        } else {
            light.setOn(false);
            System.out.println("Light " + light.getId() + " in room " + " was turned off.");
        }
    }

    private static Light findLight(SmartHome smartHome, SensorEvent event) throws Exception {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    return light;
                }
            }
        }
        throw new Exception("No light with this ID");
    }

}
