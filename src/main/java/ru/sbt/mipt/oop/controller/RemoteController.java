package ru.sbt.mipt.oop.controller;

import rc.RemoteControl;

import java.util.HashMap;
import java.util.Map;

public class RemoteController implements RemoteControl {
    private final Map<String, Map<String, Command>> commandsMap;

    public RemoteController(Map<String, Map<String, Command>> commandsMap) {
        // изменил формат, перечитав задание
        // id : code : command
        this.commandsMap = commandsMap;
    }

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {
        if (commandsMap.containsKey(rcId) && commandsMap.get(rcId).containsKey(buttonCode)) {
            commandsMap.get(rcId).get(buttonCode).execute();
        }
    }
}