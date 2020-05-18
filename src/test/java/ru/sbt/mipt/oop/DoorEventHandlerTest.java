package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.handlers.DoorEventHandler;
import ru.sbt.mipt.oop.handlers.EventHandler;
import ru.sbt.mipt.oop.sensor.SensorEvent;

import java.util.Arrays;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.constants.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.constants.SensorEventType.DOOR_OPEN;

public class DoorEventHandlerTest {

    // соберем базовый дом

    private Door door0 = new Door(true, "0");
    private Door door1 = new Door(false, "1");

    private Light light0 = new Light("0", false);
    private Light light1 = new Light("1", true);

    private SmartHome smartHome = new SmartHome(Arrays.asList(
            new Room(Arrays.asList(light0), Arrays.asList(door0), "room0"),
            new Room(Arrays.asList(light1), Arrays.asList(door1), "room1")
    ));

    private EventHandler handler = new DoorEventHandler(smartHome);

    @Test
    public void handleDoorCloseEvent() {
        handler.handleEvent(new SensorEvent(DOOR_CLOSED, "0"));

        // состояние ламп не изменилось
        assertFalse(light0.isOn());
        assertTrue(light1.isOn());

        assertFalse(door0.getOpen());
        assertFalse(door1.getOpen());
    }


    @Test
    public void handleDoorOpenEvent() {
        handler.handleEvent(new SensorEvent(DOOR_OPEN, "1"));

        // состояние ламп не изменилось
        assertFalse(light0.isOn());
        assertTrue(light1.isOn());

        assertTrue(door0.getOpen());
        assertTrue(door1.getOpen());
    }
}