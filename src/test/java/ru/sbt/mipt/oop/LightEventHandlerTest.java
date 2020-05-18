package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.handlers.EventHandler;
import ru.sbt.mipt.oop.handlers.LightEventHandler;
import ru.sbt.mipt.oop.sensor.SensorEvent;

import java.util.Arrays;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.constants.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.constants.SensorEventType.LIGHT_ON;

public class LightEventHandlerTest {

    // соберем базовый дом

    private Door door0 = new Door(true, "0");
    private Door door1 = new Door(false, "1");

    private Light light0 = new Light("0", false);
    private Light light1 = new Light("1", true);

    private SmartHome smartHome = new SmartHome(Arrays.asList(
            new Room(Arrays.asList(light0), Arrays.asList(door0), "room0"),
            new Room(Arrays.asList(light1), Arrays.asList(door1), "room1")
    ));

    private EventHandler handler = new LightEventHandler(smartHome);

    @Test
    public void handleDoorCloseEvent() {
        handler.handleEvent(new SensorEvent(LIGHT_ON, "0"));

        // состояние ламп не изменилось
        assertTrue(light0.isOn());
        assertTrue(light1.isOn());

        // состояние дверей не изменилось
        assertTrue(door0.getOpen());
        assertFalse(door1.getOpen());
    }


    @Test
    public void handleDoorOpenEvent() {
        handler.handleEvent(new SensorEvent(LIGHT_OFF, "1"));

        assertFalse(light0.isOn());
        assertFalse(light1.isOn());

        assertTrue(door0.getOpen());
        assertFalse(door1.getOpen());
    }
}