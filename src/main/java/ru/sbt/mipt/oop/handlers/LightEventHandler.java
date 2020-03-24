package ru.sbt.mipt.oop.handlers;

import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;

import static ru.sbt.mipt.oop.constants.SensorEventType.LIGHT_ON;

public class LightEventHandler {
    public static void handleEvent(SmartHome smartHome, SensorEvent event) throws Exception {
        if (event.getType() == LIGHT_ON) {
            changeState(smartHome, event, true);
        } else {
            changeState(smartHome, event, false);
        }
    }

    private static void changeState(SmartHome smartHome, SensorEvent event, boolean on) {
        smartHome.execute(component -> {
            if (component instanceof Room) {
                Room room = (Room) component;
                room.execute(roomComponent -> {
                    if (roomComponent instanceof Light) {
                        Light light = (Light) roomComponent;
                        if (light.getId().equals(event.getObjectId())) {
                            light.setOn(on);
                            System.out.println("light " + light.getId() + " was turned "
                                    + (on ? "on." : "off."));
                        }
                    }
                });
            }
        });
    }


}
