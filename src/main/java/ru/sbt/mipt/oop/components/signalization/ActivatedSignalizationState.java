package ru.sbt.mipt.oop.components.signalization;

public class ActivatedSignalizationState implements SignalizationState {
    private final Signalization signalization;

    public ActivatedSignalizationState(Signalization signalization) {
        this.signalization = signalization;
    }

    @Override
    public void activate(String code) {
        toAlarmMode();
    }

    @Override
    public void deactivate(String code) {
        if (signalization.getCode().equals(code)) {
            signalization.setState(new DeactivatedSignalizationState(signalization));
            System.out.println("Deactivated");
        } else {
            toAlarmMode();
        }
    }

    @Override
    public void toAlarmMode() {
        System.out.println("!!!Screeeeeeeeeeeeeeeeaming!!!");
        signalization.setState(new ScreamingSignalizationState(signalization));
    }
}
