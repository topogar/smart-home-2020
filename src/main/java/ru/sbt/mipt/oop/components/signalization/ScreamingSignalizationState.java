package ru.sbt.mipt.oop.components.signalization;

public class ScreamingSignalizationState implements SignalizationState {
    private final Signalization signalization;
    private final String code;

    public ScreamingSignalizationState(Signalization signalization, String code) {
        this.signalization = signalization;
        this.code = code;
    }

    @Override
    public void activate(String code) {
        System.out.println("Уже активирована");
    }

    @Override
    public void deactivate(String code) {
        if (this.code.equals(code)) {
            signalization.setState(new DeactivatedSignalizationState(signalization));
            System.out.println("Deactivated");
        } else {
           System.out.println("Введите другой код");
        }
    }

    @Override
    public void toAlarmMode() {
        System.out.println("Уже включен режим тревоги");
    }
}
