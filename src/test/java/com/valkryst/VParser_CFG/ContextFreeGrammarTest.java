package com.valkryst.VParser_CFG;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContextFreeGrammarTest {
	private final ContextFreeGrammar grammar;
	private final Faker faker = new Faker();

	public ContextFreeGrammarTest() {
		final var rules = new String[] {
			"A a aL aI aR",
			"B b bA bI bO",
			"C c",
			"D d dA dI dO dW dU",
			"E e eR eL",
			"F f fA fI fL fR fU fO",
			"G g gA gI gL gR",
			"H h hI hA",
			"I i",
			"J j jA jI jO",
			"K k kH kI",
			"L l lO",
			"M m mI",
			"N n nA No",
			"O o oI oR",
			"P p",
			"Q q",
			"R r rI rO rV",
			"S s",
			"T t tE tH",
			"U u uA uI uO",
			"V v",
			"W w wA",
			"X x",
			"Y y",
			"Z z"
		};
		grammar = new ContextFreeGrammar(rules);
	}

	@Test
	public void createContextFreeGrammarWithNullRules() {
		Assertions.assertThrows(NullPointerException.class, () -> {
			new ContextFreeGrammar(null);
		});
	}

	@Test
	public void createContextFreeGrammarWithNoRules() {
		Assertions.assertThrows(IllegalArgumentException.class, ContextFreeGrammar::new);
	}

	@Test
	public void createContextFreeGrammarWithAValidRule() {
		final var grammar = new ContextFreeGrammar("A b cA");
		Assertions.assertEquals(1, grammar.getRules().length);
		Assertions.assertEquals("A", grammar.getRules()[0].getTerminal());
		Assertions.assertArrayEquals(
			new String[] { "b", "cA" },
			grammar.getRules()[0].getNonTerminals()
		);
	}

	@Test
	public void createContextFreeGrammarWithAnInvalidRule() {
		Assertions.assertThrows(Exception.class, () -> {
			new ContextFreeGrammar("");
		});
	}

	@Test
	public void randomTerminal() {
		final var randomTerminal = grammar.randomTerminal();
		Assertions.assertNotNull(randomTerminal);
		Assertions.assertFalse(randomTerminal.isEmpty());

		for (final var rule : grammar.getRules()) {
			if (randomTerminal.equals(rule.getTerminal())) {
				return;
			}
		}

		Assertions.fail("The randomTerminal is not a terminal for any rule in the grammar.");
	}

	@Test
	public void testRun() {
		final var result = grammar.run();
		Assertions.assertNotNull(result);
		Assertions.assertFalse(result.isEmpty());

		for (final var rule : grammar.getRules()) {
			Assertions.assertFalse(result.contains(rule.getTerminal()));
		}
	}

	@Test
	public void testRunWithNullTerminal() {
		Assertions.assertThrows(NullPointerException.class, () -> {
			grammar.run(null);
		});
	}

	@Test
	public void testRunWithEmptyTerminal() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			grammar.run("");
		});
	}

	@Test
	public void testRunWithInvalidTerminal() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			grammar.run("/");
		});
	}

	@Test
	public void testRunWithValidTerminal() {
		for (int i = 0 ; i < faker.number().randomDigitNotZero() ; i++) {
			final var result = grammar.run(
				String.valueOf((char) (Math.random() * 26 + 'A'))
			);
			Assertions.assertFalse(result.isEmpty());
		}
	}

	@Test
	public void testRunWithInfiniteLoop() {
		Assertions.assertThrows(RuntimeException.class, () -> {
			final var grammar = new ContextFreeGrammar("A A");
			grammar.run("A");
		});
	}
}
