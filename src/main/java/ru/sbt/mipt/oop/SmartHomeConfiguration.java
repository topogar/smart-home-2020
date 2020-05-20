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

import java.util.*;

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
    public SignalizationEventHandlerDecorator safetyHandler(SmartHome smartHome, List<EventHandler> eventHandlers) {
        return new SignalizationEventHandlerDecorator(smartHome, eventHandlers);
    }

    @Bean
    public Map<String, SensorEventType> CCTypes() {
        HashMap<String, SensorEventType> map = new HashMap<>();
        map.put("DoorIsOpen", SensorEventType.DOOR_OPEN);
        map.put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        map.put("LightIsOn", SensorEventType.LIGHT_ON);
        map.put("LightIsOff", SensorEventType.LIGHT_OFF);
        return map;
    }

    @Bean
    public EventHandlerAdapter eventHandlerAdapter(SignalizationEventHandlerDecorator handler) {
        return new EventHandlerAdapter(handler, CCTypes());
    }

    @Bean
    public SensorEventsManager sensorEventsManager(SmartHome smartHome, List<EventHandler> eventHandlers) {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(eventHandlerAdapter(safetyHandler(smartHome, eventHandlers)));
        return sensorEventsManager;
    }
}
