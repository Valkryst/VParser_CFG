#### License

Use this code however you wish. Modifications and improvements are welcome.

Please refer to the LICENSE file for additional information.

---

[![MIT License](https://img.shields.io/badge/license-MIT_License-green.svg)](https://github.com/Valkryst/VParser_CFG/blob/master/LICENSE) ![](https://travis-ci.org/Valkryst/VParser_CFG.svg?branch=master) [![codecov](https://codecov.io/gh/Valkryst/VParser_CFG/branch/master/graph/badge.svg)](https://codecov.io/gh/Valkryst/VParser_CFG) [![Release](https://jitpack.io/v/Valkryst/VParser_CFG.svg)](https://jitpack.io/#Valkryst/VParser_CFG)

## Jar Files & Maven

To use this project as a Maven dependency, click on the JitPack badge [![Release](https://jitpack.io/v/Valkryst/VParser_CFG.svg)](https://jitpack.io/#Valkryst/VParser_CFG), select a version, click the "Get it!" button, and then follow the instructions.

If you would rather use a Jar file, then you can find the Jars on the [releases](https://github.com/Valkryst/VParser_CFG/releases) page.


---

## How to Use

1. Create a set of CFG Rules.
2. Load your rules into the program.
3. Create an instance of *ContextFreeGrammar* using your rules.
4. If no IllegalArgumentError occurs on creation of the *ContextFreeGrammar*, then your rules are semantically correct.
5. Run the CFG.

### Example

```java
public static void main(final String[] args) {
    final List<String> rules = new ArrayList<>();
    // Balin, Bifur, Bofur, Bombur, Borin
    // Dain, Dis, Dori, Dwalin, Farin, Fili
    // Floi, Frar, Frerin, Fror, Fundin, Gaiml
    // Gimli, Gloin, Groin, Gror, Ibun, Khim
    // Kili, Loni, Mim, Nain, Nali, Nar, Narvi
    // Nori, Oin, Ori, Telchar, Thorin, Thrain
    // Thror,

    rules.add("B D F G K L M N O T '");
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

    final ContextFreeGrammar cfg = new ContextFreeGrammar(rules);

    for (int i = 0 ; i < 100 ; i++) {
        String res = cfg.run();

        if (res.length() > 3) {
            System.out.println(res);
        }
    }
}
```
```
gloroi
telor
froroi
dwarori
furoi
fori
falori
nalori
dari
lorv
durv
```

---

## CFG Rules

* A CFG requires a minimum of one rule.

* The first rule in a list of input rules must always be a set of initial transitions. When the CFG is run, it will use one of these initial transitions to begin running with.

* The rules following the first rule are transitions. When the CFG runs, it takes an initial transition and applies every possible transition to the initial transition. The result of this operation then has every possible transition applied to it. This process repeats until there are no transitions that can be run against a result.

* This CFG parser is fairly basic, so it's entirely possible to send it into an infinite loop with a poorly written set of rules.

* Rule Format
  * A rule always begins with a single transition character. This character is followed by one or more substitutions. See the following points for examples.
    * Valid Rules
      * A a aB aAB
      * B b bC
      * C c
    * Invalid Rules
      * AA a aB aAB
      * B
---

## JavaDoc Documentation

Whenever a Travis CI build passes, the JavaDocs are auto-generated and made available at the following link.

https://valkryst.github.io/VParser_CFG/.
