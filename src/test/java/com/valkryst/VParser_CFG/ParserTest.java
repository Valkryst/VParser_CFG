package com.valkryst.VParser_CFG;

import Parser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ParserTest {
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
    public void testParseTransitions_withValidRules() {
        final Parser parser = new Parser();
        parser.parseTransitions(rules);

        Assert.assertEquals(10, parser.getInitialTransitions().length);
        Assert.assertEquals(25, parser.getTransitions().size());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testParseTransitions_withOneInvalidRule() {
        rules.set(1, "A");

        final Parser parser = new Parser();
        parser.parseTransitions(rules);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testParseTransitions_withNullRules() {
        final Parser parser = new Parser();
        parser.parseTransitions(null);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testParseTransitions_withEmptyRulesList() {
        rules.clear();

        final Parser parser = new Parser();
        parser.parseTransitions(rules);
    }

    @Test
    public void testToString() {
        final Parser parser = new Parser();
        parser.parseTransitions(rules);
        Assert.assertFalse(parser.toString().isEmpty());
    }
}
