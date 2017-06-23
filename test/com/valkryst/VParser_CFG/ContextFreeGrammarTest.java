package com.valkryst.VParser_CFG;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ContextFreeGrammarTest {
    private final List<String> rules = new ArrayList<>();

    @Before
    public void initializeRules() {
        rules.clear();
        rules.add("B D F G K L M N O T");
        rules.add("A a aL aI aR");
        rules.add("B b bA bI bO");
        rules.add("C c");
        rules.add("D d dA dI dO dW dU");
        rules.add("E e eR eL");
        rules.add("F f fA fI fL fR fU fO");
        rules.add("G g gA gI gL gR");
        rules.add("H h hI hA");
        rules.add("I i");
        rules.add("K k kH kI");
        rules.add("L l lO");
        rules.add("M m mI");
        rules.add("N n nA nO");
        rules.add("O o oI oR");
        rules.add("P p");
        rules.add("Q q");
        rules.add("R r rI rO rV");
        rules.add("S s");
        rules.add("T t tE tH");
        rules.add("U u uR uN");
        rules.add("V v");
        rules.add("W w wA");
        rules.add("X x");
        rules.add("Y y");
        rules.add("Z z");
    }

    @Test
    public void testConstructor_withValidRules() {
        new ContextFreeGrammar(rules);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testConstructor_withOneInvalidRule() {
        rules.set(1, "A");

        new ContextFreeGrammar(rules);
    }

    @Test
    public void testToString() {
        final ContextFreeGrammar cfg = new ContextFreeGrammar(rules);
        Assert.assertFalse(cfg.toString().isEmpty());
    }

    @Test
    public void testRun_noArguments() {
        final ContextFreeGrammar cfg = new ContextFreeGrammar(rules);
        cfg.run();
    }

    @Test
    public void testRun_intArgument_withValidIndex() {
        final ContextFreeGrammar cfg = new ContextFreeGrammar(rules);
        cfg.run(1);
    }

    @Test
    public void testRun_intArgument_withNegativeIndex() {
        final ContextFreeGrammar cfg = new ContextFreeGrammar(rules);
        cfg.run(-1);
    }

    @Test
    public void testRun_intArgument_withIndexAboveMaximum() {
        final ContextFreeGrammar cfg = new ContextFreeGrammar(rules);
        cfg.run(Integer.MAX_VALUE);
    }

    @Test
    public void testRun_stringArgument_withValidTransition() {
        final ContextFreeGrammar cfg = new ContextFreeGrammar(rules);
        Assert.assertEquals("z", cfg.run("Z"));
    }

    @Test
    public void testRun_stringArgument_withInvalidTransition() {
        final ContextFreeGrammar cfg = new ContextFreeGrammar(rules);
        Assert.assertEquals("[", cfg.run("["));
    }

    @Test
    public void testRun_stringArgument_withEmptyTransition() {
        final ContextFreeGrammar cfg = new ContextFreeGrammar(rules);
        Assert.assertEquals("", cfg.run(""));
    }

    @Test
    public void testRun_stringArgument_withNullTransition() {
        final ContextFreeGrammar cfg = new ContextFreeGrammar(rules);
        Assert.assertEquals("", cfg.run(null));
    }
}
