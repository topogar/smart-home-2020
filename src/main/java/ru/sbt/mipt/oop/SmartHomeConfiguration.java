package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.adapter.EventHandlerAdapter;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.components.signalization.ActivatedSignalizationState;
import ru.sbt.mipt.oop.constants.SensorEventType;
import ru.sbt.mipt.oop.controller.*;
import ru.sbt.mipt.oop.handlers.*;
import ru.sbt.mipt.oop.serialization.SmartHomeDeserializer;
import ru.sbt.mipt.oop.serialization.SmartHomeJsonDeserializer;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;
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

    @Bean
    ActivateAlarm activateAlarm(SmartHome smartHome) {
        return new ActivateAlarm(smartHome);
    }

    @Bean
    ActivateSignalizationState activateSignalizationState(SmartHome smartHome) {
        return new ActivateSignalizationState(smartHome, "123");
    }

    @Bean
    CloseHallDoor closeHallDoor(SmartHome smartHome) {
        return new CloseHallDoor(smartHome);
    }

    @Bean
    TurnOffAllLight turnOffAllLight(SmartHome smartHome) {
        return new TurnOffAllLight(smartHome);
    }

    @Bean
    TurnOnAllLight turnOnAllLight(SmartHome smartHome) {
        return new TurnOnAllLight(smartHome);
    }

    @Bean
    TurnOnHallLight turnOnHallLight(SmartHome smartHome) {
        return new TurnOnHallLight(smartHome);
    }

    @Bean
    RemoteController remoteController(SmartHome smartHome) {
        Map<String, Map<String, Command>> map = new HashMap<>();
        map.put("id", new HashMap<String, Command>() {{
                put("A", activateAlarm(smartHome));
                put("B", activateSignalizationState(smartHome));
                put("C", closeHallDoor(smartHome));
                put("1", turnOffAllLight(smartHome));
                put("2", turnOnAllLight(smartHome));
                put("3", turnOnHallLight(smartHome));
        }});
        return new RemoteController(map);
    }
    @Bean
    RemoteControlRegistry remoteControlRegistry(SmartHome smartHome) {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControlRegistry.registerRemoteControl(remoteController(smartHome), "id");
        return remoteControlRegistry;
    }
}
