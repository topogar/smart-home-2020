package ru.sbt.mipt.oop.components.signalization;

public interface SignalizationState {
    void activate(String code);
    void deactivate(String code);
    void toAlarmMode();
}
