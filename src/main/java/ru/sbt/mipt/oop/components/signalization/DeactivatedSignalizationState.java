package ru.sbt.mipt.oop.components.signalization;

public class DeactivatedSignalizationState implements SignalizationState {
    private final Signalization signalization;

    public DeactivatedSignalizationState(Signalization signalization) {
        this.signalization = signalization;
    }

    @Override
    public void activate(String code) {
        System.out.println("Активирована");
        signalization.setState(new ActivatedSignalizationState(signalization, code));
    }

    @Override
    public void deactivate(String code) {
        System.out.println("Уже деактивирована");
    }

    @Override
    public void toAlarmMode() {
        System.out.println("Сигнализация не активирована");
    }
}
