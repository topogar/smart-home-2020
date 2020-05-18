package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.Actionable;

public class Door implements Actionable {
    private final String id;
    private boolean isOpen;

    public Door(boolean isOpen, String id) {
        this.isOpen = isOpen;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean getOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public void execute(Action action) {
        action.execute(this);
    }
}
