package com.valkryst.VParser_CFG;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Parser {
    /** The initial transitions. */
    @Getter private String[] initialTransitions;
    /** The transitions. */
    @Getter private List<Transition> transitions;

    /**
     * Parses a set of rules into transitions.
     *
     * @param rules
     *        The rules.
     *
     * @throws IllegalArgumentException
     *         If there is a semantic error in one of the rules.
     */
    public void parseTransitions(final List<String> rules) throws IllegalArgumentException {
        if (rules == null || rules.isEmpty()) {
            throw new IllegalArgumentException("The list of rules cannot be empty or null.");
        }

        initialTransitions = rules.get(0).split(" ");
        rules.remove(0);

        transitions = new ArrayList<>(rules.size());

        for (final String string : rules) {
            transitions.add(new Transition(string));
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CFG Transitions:");
        sb.append("\n\tInitial Transitions: \n\t\t");

        for (final String string : initialTransitions) {
            sb.append(string).append(" ");
        }

        for (final Transition transition : transitions) {
            String tmp = transition.toString();
            tmp = tmp.replace("Transition:", "\tTransition:");
            tmp = tmp.replace("\n\t", "\n\t\t");

            sb.append("\n\n").append(tmp);
        }

        return sb.toString();
    }
}
