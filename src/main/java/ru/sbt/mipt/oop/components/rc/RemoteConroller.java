package ru.sbt.mipt.oop.components.rc;

import rc.RemoteControl;

import java.util.HashMap;
import java.util.Map;


public class RemoteConroller implements RemoteControl{
    private Map<String, Command> commandsMap = new HashMap<>();

    public void set(String buttonCode, Command command) {
        commandsMap.put(buttonCode, command);
    }

    public Command get(String buttonCode) {
        return commandsMap.get(buttonCode);
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        commandsMap.get(buttonCode).execute();
    }
}