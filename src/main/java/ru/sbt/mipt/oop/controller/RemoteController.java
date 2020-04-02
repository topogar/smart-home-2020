package ru.sbt.mipt.oop.controller;

import rc.RemoteControl;

import java.util.HashMap;
import java.util.Map;

public class RemoteController implements RemoteControl {
    private Map<String, Command> commandsMap = new HashMap<>();

    public void set(String buttonCode, Command cmd) {
        commandsMap.put(buttonCode, cmd);
    }

    public Command get(String buttonCode) {
        return commandsMap.get(buttonCode);
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        commandsMap.get(buttonCode).execute();
    }
}