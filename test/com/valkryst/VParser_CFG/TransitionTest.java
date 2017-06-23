package com.valkryst.VParser_CFG;

import org.junit.Assert;
import org.junit.Test;

public class TransitionTest {
    @Test
    public void testConstructor_withOnlyOneSubstitution() {
        final Transition transition = new Transition("A a");

        Assert.assertEquals("A", transition.getTransitionCharacter());
    }

    @Test
    public void testConstructor_withMultipleSubstitutions() {
        final Transition transition = new Transition("A a aL aI aR");
        final String[] substitutions = transition.getSubstitutions();

        Assert.assertEquals("A", transition.getTransitionCharacter());
        Assert.assertEquals("a", substitutions[0]);
        Assert.assertEquals("aL", substitutions[1]);
        Assert.assertEquals("aI", substitutions[2]);
        Assert.assertEquals("aR", substitutions[3]);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withEmptyRule() {
        new Transition("");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withNoSubstitutions() {
        new Transition("A");
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withMultiCharacterTransition() {
        new Transition("AB a aL aI aR");
    }

    @Test
    public void testPerformTransition() {
        final Transition transition = new Transition("A b");
        final String input = "AAA";

        Assert.assertEquals("bbb", transition.performTransition(input));
    }

    @Test
    public void testPerformTransition_withNullInput() {
        final Transition transition = new Transition("A b");

        Assert.assertEquals("", transition.performTransition(null));
    }

    @Test
    public void testCanApplyTransition_withTrueCase() {
        final Transition transition = new Transition("A b");
        final String input = "AAA";

        Assert.assertTrue(transition.canApplyTransition(input));
    }

    @Test
    public void testCanApplyTransition_withFalseCase() {
        final Transition transition = new Transition("A b");
        final String input = "ZZZ";

        Assert.assertFalse(transition.canApplyTransition(input));
    }

    @Test
    public void testCanApplyTransition_withNullInput() {
        final Transition transition = new Transition("A b");

        Assert.assertFalse(transition.canApplyTransition(null));
    }

    @Test
    public void testChooseRandomSubstitution() {
        final Transition transition = new Transition("A b c");
        final String input = "AAAAAAAAAA";

        boolean result = false;

        for (int i = 0 ; i < 10 ; i++) {
            final String output = transition.performTransition(input);

            result = output.contains("b");
            result &= output.contains("c");

            if (result) {
                break;
            }
        }

        Assert.assertTrue(result);
    }
}
