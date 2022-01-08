package com.valkryst.VParser_CFG;

import lombok.Getter;
import lombok.NonNull;

import java.util.concurrent.ThreadLocalRandom;

public class ContextFreeGrammar {
	@Getter private final Rule[] rules;

    /**
     * Constructs a new ContextFreeGrammar.
     *
     * @param rules
	 * 		One or more rules, where each is expressed as a string of one
	 * 		terminal followed by zero or more non-terminals.
     */
    public ContextFreeGrammar(final @NonNull String ... rules) {
		if (rules.length == 0) {
			throw new IllegalArgumentException("There must be at least one rule.");
		}

		this.rules = new Rule[rules.length];

		for (int i = 0 ; i < rules.length ; i++) {
			this.rules[i] = new Rule(rules[i]);
			this.rules[i].validate();
		}
    }

	/**
	 * Retrieves a random terminal.
	 *
	 * @return A random terminal.
	 */
	public String randomTerminal() {
		return rules[
			ThreadLocalRandom.current().nextInt(0, rules.length)
		].getTerminal();
	}

	/**
	 * Runs the ContextFreeGrammar with a random terminal, and returns a result.
	 *
	 * @return A result.
	 */
	public String run() {
		return run(randomTerminal());
	}

	/**
	 * Runs the ContextFreeGrammar and returns a result.
	 *
	 * @param terminal A starting terminal.
	 * @return A result.
	 */
	public String run(final @NonNull String terminal) {
		if (terminal.isEmpty()) {
			throw new IllegalArgumentException("The terminal cannot be empty.");
		}

		boolean isValidTerminal = false;

		for (final Rule rule : rules) {
			if (rule.getTerminal().equals(terminal)) {
				isValidTerminal = true;
				break;
			}
		}

		if (!isValidTerminal) {
			throw new IllegalArgumentException("'" + terminal + "' is not a valid terminal. It does not correspond to any terminal for any rule.");
		}

		final var sb = new StringBuilder(terminal);
		int iterations = 0;

		while (true) {
			boolean continueRunning = false;

			for (final var rule : rules) {
				final var temp = sb.toString();

				if (temp.contains(rule.getTerminal())) {
					sb.setLength(0);
					sb.append(
						temp.replaceFirst(
							rule.getTerminal(),
							rule.randomNonTerminal()
						)
					);

					continueRunning = true;
					iterations++;
				}
			}

			if (!continueRunning) {
				break;
			}

			if (iterations >= 10_000) {
				throw new RuntimeException("The grammar has performed " + iterations + " iterations and is likely stuck in an infinite loop.");
			}
		}

		return sb.toString();
	}
}
