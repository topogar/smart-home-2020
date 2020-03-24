package ru.sbt.mipt.oop.serialization;

import ru.sbt.mipt.oop.components.SmartHome;

import java.io.IOException;

public interface SmartHomeDeserializer {
    public SmartHome deserialize() throws IOException;
}
