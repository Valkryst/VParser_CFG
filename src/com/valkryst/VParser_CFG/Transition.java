package com.valkryst.VParser_CFG;

import lombok.Getter;

import java.util.concurrent.ThreadLocalRandom;

public class Transition {
    /** The transition character. */
    @Getter private final String transitionCharacter;
    /** The substitutions. */
    @Getter private final String[] substitutions;

    /**
     * Constructs a Transition.
     *
     * @param rule
     *        The rule defining the transition.
     *
     * @throws IllegalArgumentException
     *         If there is a semantic error in one of the rules.
     */
    public Transition(final String rule) throws IllegalArgumentException {
        if (rule.isEmpty()) {
            throw new IllegalArgumentException("A rule cannot be empty.");
        }

        final String[] parts = rule.split(" ");

        if (parts.length < 2) {
            throw new IllegalArgumentException("A rule must have a transition and at-least one substitution.");
        }

        // Remove any remaining spaces from transition/substitutions:
        for (int i = 0 ; i < parts.length ; i++) {
            parts[i] = parts[i].replace(" ", "");
        }

        if (parts[0].length() != 1) {
            throw new IllegalArgumentException("A rule's transition can be one, and only one, character."
                                               + "\nFound \"" + parts[0] + "\"");
        }


        transitionCharacter = parts[0].substring(0, 1);

        substitutions = new String[parts.length - 1];
        System.arraycopy(parts, 1, substitutions, 0, substitutions.length);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Transition:");
        sb.append("\n\tChar: ").append(transitionCharacter);
        sb.append("\n\tSubstitutions: ");

        for (final String string : substitutions) {
            sb.append(string).append(" ");
        }

        return sb.toString();
    }

    /**
     * Replaces each instance of the transition character, in a string,
     * with a random substitution.
     *
     * @param string
     *        The string.
     *
     * @return
     *        A fully-transitioned string where no instances of the
     *        transition character remain.
     */
    public String performTransition(String string) {
        while (canApplyTransition(string)) {
            string = string.replaceFirst(transitionCharacter, chooseRandomSubstitution());
        }

        return string;
    }

    /**
     * Determines if a string contains the transition character.
     *
     * @param string
     *        The string.
     *
     * @return
     *        Whether or not the string contains the transition character.
     */
    public boolean canApplyTransition(final String string) {
        return string.contains(transitionCharacter);
    }

    /**
     * Chooses a random substitution.
     *
     * @return
     *        A substitution.
     */
    private String chooseRandomSubstitution() {
        final int index = ThreadLocalRandom.current().nextInt(substitutions.length);
        return substitutions[index];
    }
}
