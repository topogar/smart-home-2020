package ru.sbt.mipt.oop.components.signalization;

public class Signalization {
    private SignalizationState state;

    public Signalization() {
        this.state = new DeactivatedSignalizationState(this);
    }

    public void activate(String code) {
        state.activate(code);
    }

    public void deactivate(String code) {
        state.deactivate(code);
    }

    public void toAlarmMode() {
        state.toAlarmMode();
    }

    public void setState(SignalizationState state) {
        this.state = state;
    }

    public SignalizationState getState() {
        return this.state;
    }

    public boolean isActivated() {
        return (state instanceof ActivatedSignalizationState || state instanceof ScreamingSignalizationState);
    }

    public boolean isAlarm() {
        return state instanceof ScreamingSignalizationState;
    }

}
