package com.valkryst.VParser_CFG;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RuleTest {
	private final Faker faker = new Faker();

	public Rule createRuleWithNoNonTerminals() {
		return new Rule(faker.lorem().word());
	}

	public Rule createRuleWithNonTerminals() {
		return createRuleWithNonTerminals(faker.number().randomDigitNotZero());
	}

	public Rule createRuleWithNonTerminals(final int totalNonTerminals) {
		final var rule = createRuleWithNoNonTerminals();
		for (int i = 0 ; i < totalNonTerminals ; i++) {
			rule.addNonTerminal(faker.lorem().word());
		}
		return rule;
	}

	@Test
	public void createRuleWithNullRule() {
		Assertions.assertThrows(NullPointerException.class, () -> {
			new Rule(null);
		});
	}

	@Test
	public void createRuleWithEmptyRule() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rule("");
		});
	}

	@Test
	public void createRuleWithNoNonTerminalsAndWithExtraLeadingWhitespace() {
		var rule = new Rule(" T");
		Assertions.assertEquals("T", rule.getTerminal());
		Assertions.assertArrayEquals(new String[0], rule.getNonTerminals());

		rule = new Rule("  T");
		Assertions.assertEquals("T", rule.getTerminal());
		Assertions.assertArrayEquals(new String[0], rule.getNonTerminals());
	}

	@Test
	public void createRuleWithNoNonTerminalsAndWithExtraTrailingWhitespace() {
		var rule = new Rule("T ");
		Assertions.assertEquals("T", rule.getTerminal());
		Assertions.assertArrayEquals(new String[0], rule.getNonTerminals());

		rule = new Rule("T  ");
		Assertions.assertEquals("T", rule.getTerminal());
		Assertions.assertArrayEquals(new String[0], rule.getNonTerminals());
	}

	@Test
	public void createRuleWithNonTerminalsAndExtraWhitespace() {
		final var rule = new Rule("  T e  s ting a fun    c         Tion");
		Assertions.assertEquals("T", rule.getTerminal());
		Assertions.assertArrayEquals(
			new String[] { "e", "s", "ting", "a", "fun", "c", "Tion" },
			rule.getNonTerminals()
		);
	}

	@Test
	public void createRuleWithNullTerminal() {
		Assertions.assertThrows(NullPointerException.class, () -> {
			new Rule(null, new String[0]);
		});
	}

	@Test
	public void createRuleWithEmptyTerminal() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			new Rule("", new String[0]);
		});
	}

	@Test
	public void createRuleWithValidTerminalAndNoNonTerminals() {
		final var terminal = faker.lorem().word();

		final var rule = new Rule(terminal, new String[0]);
		Assertions.assertEquals(terminal, rule.getTerminal());
		Assertions.assertEquals(0, rule.getNonTerminals().length);
	}

	@Test
	public void createRuleWithValidTerminalAndValidTerminals() {
		final var terminal = faker.lorem().word();
		final var nonTerminals = faker.lorem().sentence().split(" ");
		final var rule = new Rule(terminal, nonTerminals);

		Assertions.assertEquals(terminal, rule.getTerminal());
		Assertions.assertArrayEquals(nonTerminals, rule.getNonTerminals());
	}

	@Test
	public void addNonTerminals() {
		final var rule = createRuleWithNoNonTerminals();
		for (int i = 0 ; i < faker.number().randomDigitNotZero() ; i++) {
			final var nonTerminal = String.valueOf(i);

			rule.addNonTerminals(nonTerminal);
			Assertions.assertEquals(i + 1, rule.getNonTerminals().length);
			Assertions.assertEquals(
				nonTerminal,
				rule.getNonTerminals()[i]
			);
		}
	}

	@Test
	public void addNullNonTerminal() {
		final var rule = createRuleWithNoNonTerminals();
		rule.addNonTerminal(null);

		Assertions.assertEquals(0, rule.getNonTerminals().length);
	}

	@Test
	public void addEmptyNonTerminal() {
		final var rule = createRuleWithNoNonTerminals();
		rule.addNonTerminal("");

		Assertions.assertEquals(1, rule.getNonTerminals().length);
		Assertions.assertEquals("", rule.getNonTerminals()[0]);
	}

	@Test
	public void addValidNonTerminal() {
		final var rule = createRuleWithNoNonTerminals();

		final var nonTerminal = faker.lorem().word();
		rule.addNonTerminal(nonTerminal);

		Assertions.assertEquals(1, rule.getNonTerminals().length);
		Assertions.assertEquals(nonTerminal, rule.getNonTerminals()[0]);
	}

	@Test
	public void addDuplicateNonTerminal() {
		final var rule = createRuleWithNoNonTerminals();

		final var nonTerminal = faker.lorem().word();
		for (int i = 0 ; i < 2 ; i++) {
			rule.addNonTerminal(nonTerminal);

			Assertions.assertEquals(1, rule.getNonTerminals().length);
			Assertions.assertEquals(nonTerminal, rule.getNonTerminals()[0]);
		}
	}

	@Test
	public void randomNonTerminalWhenNoNonTerminalsHaveBeenAdded() {
		Assertions.assertThrows(IllegalStateException.class, () -> {
			createRuleWithNoNonTerminals().randomNonTerminal();
		});
	}

	@Test
	public void randomNonTerminalWhenNonTerminalsHaveBeenAdded() {
		final var nonTerminals = new String[] { "A", "B", "C" };
		final var rule = new Rule("D", nonTerminals);

		final var randomNonTerminal = rule.randomNonTerminal();
		for (final String nonTerminal : nonTerminals) {
			if (randomNonTerminal.equals(nonTerminal)) {
				return;
			}
		}

		Assertions.fail("A rule generated a random non-terminal that it does not contain.");
	}

	@Test
	public void validateWhenNoNonTerminalsHaveBeenAdded() {
		Assertions.assertThrows(IllegalStateException.class, () -> {
			createRuleWithNoNonTerminals().validate();
		});
	}

	@Test
	public void validateWhenAtLeastOneNonTerminalHasBeenAdded() {
		createRuleWithNonTerminals().validate();
	}

	@Test
	public void getNonTerminalsWhenNoTerminalsHaveBeenAdded() {
		Assertions.assertEquals(
			0,
			createRuleWithNoNonTerminals().getNonTerminals().length
		);
	}

	@Test
	public void getNonTerminalsWhenTerminalsHaveBeenAdded() {
		Assertions.assertEquals(
			1,
			createRuleWithNonTerminals(1).getNonTerminals().length
		);
	}
}
