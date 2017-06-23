#### License

Use this code however you wish. Modifications and improvements are welcome.

Please refer to the LICENSE file for additional information.

---

![](https://travis-ci.org/Valkryst/VParser_CFG.svg?branch=master) [![codecov](https://codecov.io/gh/Valkryst/VParser_CFG/branch/master/graph/badge.svg)](https://codecov.io/gh/Valkryst/VParser_CFG)

## How to Setup

1. Clone the project from GitHub.
2. Open your project in IntelliJ.
3. Open the *Project Structure* menu. (S + ALT + CTRL + SHIFT)
4. Enter the *Modules* subsection.
5. Click the green "+" at the top of the window.
6. Select *Import Module*.
7. Navigate to the cloned project and double-click on *pom.xml*.
8. Click on the name of your project in the *Modules* subsection.
9. Enter the *Dependencies* tab of your project.
10. Click on the green "+" at the right-edge of the window.
11. Click *Module Dependency...*.
12. Select *VParser_CFG*.

Now you can use VParser_CFG in your project.

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

## JavaDoc Documentation

Whenever a Travis CI build passes, the JavaDocs are auto-generated and made available at the following link.

https://valkryst.github.io/VParser_CFG/.