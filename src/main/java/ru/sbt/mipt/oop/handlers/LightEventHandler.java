package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;

import static ru.sbt.mipt.oop.constants.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.constants.SensorEventType.LIGHT_ON;

public class LightEventHandler implements EventHandler{

    private final SmartHome smartHome;

    public LightEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void handleEvent(SensorEvent event) {

        if (event.getType() != LIGHT_ON & event.getType() != LIGHT_OFF)
            return;

        Light light = findLight(event);

        if (light == null) {
            System.out.println("Light not found");
            return;
        }
        if (event.getType() == LIGHT_ON) {
            light.setOn(true);
            System.out.println("Light " + light.getId() + " in room " + " was turned on.");
        } else {
            light.setOn(false);
            System.out.println("Light " + light.getId() + " in room " + " was turned off.");
        }
    }

    private Light findLight(SensorEvent event) {
        for (Room room : this.smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    return light;
                }
            }
        }
        return null;
    }

}
