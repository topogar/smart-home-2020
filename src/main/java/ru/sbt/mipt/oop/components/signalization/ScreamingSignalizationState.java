package ru.sbt.mipt.oop.components.signalization;

public class ScreamingSignalizationState implements SignalizationState {
    private final Signalization signalization;

    public ScreamingSignalizationState(Signalization signalization) {
        this.signalization = signalization;
    }

    @Override
    public void activate(String code) {
        System.out.println("Уже активирована");
    }

    @Override
    public void deactivate(String code) {
        if (signalization.getCode().equals(code)) {
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
