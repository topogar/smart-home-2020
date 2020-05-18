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
        if (event.getType() == LIGHT_ON) {
            changeState(event, true);
        }
        if (event.getType() == LIGHT_OFF){
            changeState(event, false);
        }
    }

    private void changeState(SensorEvent event, boolean on) {
        this.smartHome.execute(component -> {
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
