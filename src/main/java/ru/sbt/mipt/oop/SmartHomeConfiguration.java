package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.adapter.EventHandlerAdapter;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.constants.SensorEventType;
import ru.sbt.mipt.oop.handlers.*;
import ru.sbt.mipt.oop.serialization.SmartHomeDeserializer;
import ru.sbt.mipt.oop.serialization.SmartHomeJsonDeserializer;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class SmartHomeConfiguration {

    @Bean
    SmartHomeDeserializer smartHomeDeserializer() {
        return new SmartHomeJsonDeserializer();
    }

    @Bean
    public SmartHome smartHome(SmartHomeDeserializer smartHomeDeserializer) {
        return smartHomeDeserializer.deserialize();
    }

    @Bean
    public EventHandler doorEventHandler(SmartHome smartHome) {
        return new DoorEventHandler(smartHome);
    }

    @Bean
    public EventHandler lightEventHandler(SmartHome smartHome) {
        return new LightEventHandler(smartHome);
    }

    @Bean
    public EventHandler hallDoorEventHandler(SmartHome smartHome) {
        return new HallDoorEventHandler(smartHome);
    }

    @Bean
    public EventHandler signalizationEventHandler(SmartHome smartHome) {
        return new SignalizationEventHandler(smartHome);
    }

    @Bean
    public EventHandler safetyHandler(SmartHome smartHome, List<EventHandler> eventHandlers) {
        return new SignalizationEventHandlerDecorator(smartHome, eventHandlers);
    }

    @Bean
    public List<EventHandler> handlers(SmartHome smartHome) {
        return Arrays.asList(doorEventHandler(smartHome),
                lightEventHandler(smartHome),
                hallDoorEventHandler(smartHome),
                signalizationEventHandler(smartHome));
    }

    @Bean
    public Map<String, SensorEventType> CCS2Sensor() {
        return Stream.of(
                new AbstractMap.SimpleImmutableEntry<>("DoorIsOpen", SensorEventType.DOOR_OPEN),
                new AbstractMap.SimpleImmutableEntry<>("DoorIsClosed", SensorEventType.DOOR_CLOSED),
                new AbstractMap.SimpleImmutableEntry<>("LightIsOn", SensorEventType.LIGHT_ON),
                new AbstractMap.SimpleImmutableEntry<>("LightIsOff", SensorEventType.LIGHT_OFF))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Bean
    public EventHandlerAdapter eventHandlerAdapter(List<EventHandler> handlers) {
        return new EventHandlerAdapter(handlers, CCS2Sensor());
    }

    @Bean
    public SensorEventsManager sensorEventsManager(SmartHome smartHome) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(eventHandlerAdapter(handlers(smartHome)));
        return sensorEventsManager;
    }
}
