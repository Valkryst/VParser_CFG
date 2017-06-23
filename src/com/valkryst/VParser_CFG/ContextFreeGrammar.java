package com.valkryst.VParser_CFG;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ContextFreeGrammar {
    /** The initial transitions. */
    private String[] initialTransitions;
    /** The transitions. */
    private Transition[] transitions;

    /**
     * Constructs a ContextFreeGrammar.
     *
     * @param rules
     *        The rules of the CFG.
     *
     * @throws IllegalArgumentException
     *         If there is a semantic error in one of the rules.
     */
    public ContextFreeGrammar(final List<String> rules) throws IllegalArgumentException {
        final Parser parser = new Parser();
        parser.parseTransitions(rules);


        final int totalTransitions = parser.getTransitions().size();

        initialTransitions = parser.getInitialTransitions();
        transitions = parser.getTransitions().toArray(new Transition[totalTransitions]);
    }

    /**
     * Runs the CFG with a random initial transition.
     *
     * @return
     *        Result of the CFG.
     */
    public String run() {
        return run(ThreadLocalRandom.current().nextInt(initialTransitions.length));
    }

    /**
     * Runs the CFG with an initial transition.
     *
     * If the index is out of the initial transitions array
     * bounds, then it defaults to 0.
     *
     * @param index
     *        Index of the initial transition.
     *
     * @return
     *        Result of the CFG.
     */
    public String run(int index) {
        if (index < 0 || index > initialTransitions.length) {
            index = 0;
        }

        return run(initialTransitions[index]);
    }

    /**
     * Runs the CFG with an initial transition.
     *
     * @param initialTransition
     *        The initial transition.
     *
     * @return
     *        Result of the CFG.
     */
    public String run(String initialTransition) {
        if (initialTransition == null) {
            return "";
        }

        boolean replacementOccurred = true;

        while (replacementOccurred) {
            replacementOccurred = false;

            for (final Transition transition : transitions) {
                if (transition.canApplyTransition(initialTransition)) {
                    initialTransition = transition.performTransition(initialTransition);
                    replacementOccurred = true;
                }
            }
        }

        return initialTransition;
    }
}
