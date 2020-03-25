package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import ru.sbt.mipt.oop.adapter.EventHandlerAdapter;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.handlers.*;
import ru.sbt.mipt.oop.serialization.SmartHomeDeserializer;
import ru.sbt.mipt.oop.serialization.SmartHomeJsonDeserializer;

import java.io.IOException;
import java.util.Arrays;

public class SmartHomeConfiguration {
    @Bean
    public SmartHome smartHome() throws IOException {
        SmartHomeDeserializer deserializer = new SmartHomeJsonDeserializer();
        return deserializer.deserialize();
    }

    @Bean
    public EventHandler handler(SmartHome smartHome) {
        return new SignalizationEventHandlerDecorator(Arrays.asList(new DoorEventHandler(),
                                                                    new LightEventHandler(),
                                                                    new HallDoorEventHandler(),
                                                                    new SignalizationEventHandler()));
    }

    @Bean
    public SensorEventsManager sensorEventsManager(SmartHome smartHome, EventHandler eventHandler) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(new EventHandlerAdapter(smartHome, eventHandler));
        return sensorEventsManager;
    }
}
