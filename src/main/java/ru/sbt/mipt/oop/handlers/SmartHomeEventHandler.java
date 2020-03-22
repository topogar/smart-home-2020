package ru.sbt.mipt.oop.handlers;


import ru.sbt.mipt.oop.sensor.SensorEvent;
import ru.sbt.mipt.oop.components.SmartHome;

import static ru.sbt.mipt.oop.constants.SensorEventType.*;

public class SmartHomeEventHandler  implements EventHandler {
    public static void handleEvent(SmartHome smartHome, SensorEvent event) throws Exception {
        System.out.println("Got event: " + event);

        if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF)
            LightEventHandler.handleEvent(smartHome, event);

        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            DoorEventHandler.handleEvent(smartHome, event);
            // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
            // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
            HallDoorEventHandler.handleEvent(smartHome, event);
        }
    }
}
