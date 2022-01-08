package com.valkryst.VParser_CFG;

import lombok.Getter;
import lombok.NonNull;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Rule {
	@Getter private final String terminal;

	private String[] nonTerminals = new String[0];

	/**
	 * Constructs a new rule.
	 *
	 * @param rule
	 * 		A rule expressed as a string of one terminal followed by zero or
	 * 		more non-terminals, each separated a space.
	 *
	 * 		Empty terminals are ignored as whitespace characters are removed
	 * 		from the terminal and non-terminals. You must add empty terminals
	 * 		after creating the rule.
	 */
	public Rule(@NonNull String rule) {
		if (rule.isEmpty()) {
			throw new IllegalArgumentException("The rule cannot be empty.");
		}

		var parts = rule.trim().split("\\s+");

		// Remove all whitespace from parts.
		final var sb = new StringBuilder();
		for (int i = 0 ; i < parts.length ; i++) {
			for (int j = 0 ; j < parts[i].length() ; j++) {
				final int codePoint = parts[i].codePointAt(j);

				if (!Character.isWhitespace(codePoint)) {
					sb.appendCodePoint(codePoint);
				}
			}

			parts[i] = sb.toString();
			sb.setLength(0);
		}

		// Add non-terminals.
		for (int i = 1 ; i < parts.length ; i++) {
			addNonTerminal(parts[i]);
		}

		this.terminal = parts[0];
	}

	/**
	 * Constructs a new rule.
	 *
	 * @param terminal A terminal.
	 * @param nonTerminals Zero or more non-terminal.
	 */
	public Rule(final @NonNull String terminal, final String ... nonTerminals) {
		if (terminal.isEmpty()) {
			throw new IllegalArgumentException("The terminal cannot be empty.");
		}

		this.terminal = terminal;
		addNonTerminals(nonTerminals);
	}

	/**
	 * Adds one or more non-terminal to this rule.
	 *
	 * @param nonTerminals One or more non-terminal.
	 */
	public void addNonTerminals(final String ... nonTerminals) {
		for (final String nonTerminal : nonTerminals) {
			addNonTerminal(nonTerminal);
		}
	}

	/**
	 * Adds a non-terminal symbol to this rule.
	 *
	 * @param nonTerminal A non-terminal symbol.
	 */
	public void addNonTerminal(final String nonTerminal) {
		if (nonTerminal == null) {
			return;
		}

		for (final String temp : nonTerminals) {
			if (Objects.equals(temp, nonTerminal)) {
				return;
			}
		}

		nonTerminals = Arrays.copyOf(
			nonTerminals,
			nonTerminals.length + 1
		);
		nonTerminals[nonTerminals.length - 1] = nonTerminal;
	}

	/**
	 * Retrieves a random non-terminal.
	 *
	 * @return A random non-terminal.
	 */
	public String randomNonTerminal() {
		validate();

		return nonTerminals[
			ThreadLocalRandom.current().nextInt(0, nonTerminals.length)
		];
	}

	/** Determines whether this rule is valid, and throws an exception if not. */
	public void validate() {
		if (nonTerminals.length == 0) {
			throw new IllegalStateException("A rule must have at least one non-terminal symbol. This one has none.");
		}
	}

	/**
	 * Retrieves a copy of the non-terminal.
	 *
	 * @return A copy of the non-terminal.
	 */
	public String[] getNonTerminals() {
		return nonTerminals.clone();
	}
}
