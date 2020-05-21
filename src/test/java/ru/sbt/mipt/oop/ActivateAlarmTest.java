package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.components.signalization.ScreamingSignalizationState;
import ru.sbt.mipt.oop.components.signalization.Signalization;
import ru.sbt.mipt.oop.controller.ActivateAlarm;
import ru.sbt.mipt.oop.controller.ActivateSignalizationState;
import ru.sbt.mipt.oop.controller.Command;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

public class ActivateAlarmTest {

    // соберем базовый дом

    private Door door0 = new Door(true, "0");
    private Door door1 = new Door(false, "1");

    private Light light0 = new Light("0", false);
    private Light light1 = new Light("1", true);

    private SmartHome smartHome = new SmartHome(Arrays.asList(
            new Room(Arrays.asList(light0), Arrays.asList(door0), "room0"),
            new Room(Arrays.asList(light1), Arrays.asList(door1), "room1")
    ), new Signalization());

    @Test
    public void activateTest() {
        String code = "code";
        Command command = new ActivateSignalizationState(smartHome, code);
        command.execute();

        Command activateAlarm = new ActivateAlarm(smartHome);
        activateAlarm.execute();

        assertTrue(smartHome.getSignalization().getState() instanceof ScreamingSignalizationState);
    }
}
