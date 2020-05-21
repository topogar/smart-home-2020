package ru.sbt.mipt.oop;

import org.junit.Test;
import ru.sbt.mipt.oop.components.signalization.ActivatedSignalizationState;
import ru.sbt.mipt.oop.components.signalization.DeactivatedSignalizationState;
import ru.sbt.mipt.oop.components.signalization.ScreamingSignalizationState;
import ru.sbt.mipt.oop.components.signalization.Signalization;

import static org.junit.Assert.*;

public class SignalizationTest {

    @Test
    public void GoodDeactivateActiveSignalization() {
        Signalization signalization = new Signalization();
        signalization.activate("1");
        signalization.deactivate("1");
        assertTrue(signalization.getState() instanceof DeactivatedSignalizationState);
    }

    @Test
    public void BadDeactivateActiveSignalization() {
        Signalization signalization = new Signalization();
        signalization.activate("1");
        signalization.deactivate("2");
        assertTrue(signalization.getState() instanceof ScreamingSignalizationState);
    }

    @Test
    public void GoodDeactivateScreamingSignalization() {
        Signalization signalization = new Signalization();
        signalization.activate("1");
        signalization.toAlarmMode();
        signalization.deactivate("1");
        assertTrue(signalization.getState() instanceof DeactivatedSignalizationState);
    }

    @Test
    public void BadDeactivateScreamingSignalization() {
        Signalization signalization = new Signalization();
        signalization.activate("1");
        signalization.toAlarmMode();
        signalization.deactivate("2");
        assertTrue(signalization.getState() instanceof ScreamingSignalizationState);
    }
}