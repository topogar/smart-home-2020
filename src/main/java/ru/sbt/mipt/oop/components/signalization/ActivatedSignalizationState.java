package ru.sbt.mipt.oop.components.signalization;

public class ActivatedSignalizationState implements SignalizationState {
    private final Signalization signalization;
    private final String code;

    public ActivatedSignalizationState(Signalization signalization, String code) {
        this.code = code;
        this.signalization = signalization;
    }

    @Override
    public void activate(String code) {
        // ничего
    }

    @Override
    public void deactivate(String code) {
        if (this.code.equals(code)) {
            signalization.setState(new DeactivatedSignalizationState(signalization));
            System.out.println("Deactivated");
        } else {
            toAlarmMode();
        }
    }

    @Override
    public void toAlarmMode() {
        System.out.println("!!!Screeeeeeeeeeeeeeeeaming!!!");
        signalization.setState(new ScreamingSignalizationState(signalization, code));
    }
}
