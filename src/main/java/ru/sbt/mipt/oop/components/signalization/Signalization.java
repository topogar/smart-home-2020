package ru.sbt.mipt.oop.components.signalization;

public class Signalization {
    private String code;
    private SignalizationState state;

    public Signalization() {
        this.state = new DeactivatedSignalizationState(this);
        this.code = " ";
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

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public boolean isActivated() {
        return (state instanceof ActivatedSignalizationState || state instanceof ScreamingSignalizationState);
    }

    public boolean isAlarm() {
        return state instanceof ScreamingSignalizationState;
    }

}
