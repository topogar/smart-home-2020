package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.handlers.DoorEventHandler;
import ru.sbt.mipt.oop.handlers.EventHandler;
import ru.sbt.mipt.oop.handlers.HallDoorEventHandler;
import ru.sbt.mipt.oop.sensor.SensorEvent;

import java.util.Arrays;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.constants.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.constants.SensorEventType.DOOR_OPEN;

public class HallDoorEventHandlerTest {

    // соберем базовый дом

    private Door door0 = new Door(true, "0");
    private Door door1 = new Door(false, "1");
    private Door door2 = new Door(false, "2");
    private Door door3 = new Door(true, "3");

    private Light light0 = new Light("0", false);
    private Light light1 = new Light("1", true);
    private Light light2 = new Light("2", true);
    private Light light3 = new Light("3", false);

    private SmartHome smartHome = new SmartHome(Arrays.asList(
            new Room(Arrays.asList(light0), Arrays.asList(door0), "room0"),
            new Room(Arrays.asList(light1), Arrays.asList(door1), "room1"),
            new Room(Arrays.asList(light2), Arrays.asList(door2), "hall"),
            new Room(Arrays.asList(light3), Arrays.asList(door3), "hall")
    ));

    private EventHandler handler = new HallDoorEventHandler(smartHome);

    @Test
    public void handleHallDoorCloseEvent() {
        handler.handleEvent(new SensorEvent(DOOR_CLOSED, "3"));

        // все лампы погасли
        assertFalse(light0.isOn());
        assertFalse(light1.isOn());
        assertFalse(light2.isOn());
        assertFalse(light3.isOn());

        assertTrue(door0.getOpen());
        assertFalse(door1.getOpen());
        assertFalse(door2.getOpen());
        assertFalse(door3.getOpen());
    }


    @Test
    public void handleHallDoorOpenEvent() {
        handler.handleEvent(new SensorEvent(DOOR_OPEN, "2"));

        // состояние ламп не изменилось
        assertFalse(light0.isOn());
        assertTrue(light1.isOn());
        assertTrue(light2.isOn());
        assertFalse(light3.isOn());

        assertTrue(door0.getOpen());
        assertFalse(door1.getOpen());
        assertTrue(door2.getOpen());
        assertTrue(door3.getOpen());
    }
}