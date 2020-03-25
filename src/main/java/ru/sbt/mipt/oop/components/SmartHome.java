package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.Actionable;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.components.signalization.Signalization;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    Collection<Room> rooms;
    private Signalization signalization;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    public Signalization getSignalization() {
        return signalization;
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
        rooms.forEach(room -> room.execute(action));
    }
}
