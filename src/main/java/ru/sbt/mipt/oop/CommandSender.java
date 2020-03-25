package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.sensor.SensorCommand;

public class CommandSender {
    public static void sendCommand(SensorCommand command) {
        System.out.println("Pretent we're sending command " + command);
    }
}
