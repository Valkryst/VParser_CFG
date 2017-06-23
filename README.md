#### License:

Use this code however you wish. Modifications and improvements are welcome.

Please refer to the LICENSE file for additional information.

---

![](https://travis-ci.org/Valkryst/VParser_CFG.svg?branch=master) [![codecov](https://codecov.io/gh/Valkryst/VParser_CFG/branch/master/graph/badge.svg)](https://codecov.io/gh/Valkryst/VParser_CFG)

## How to Setup:

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

## How to Use:

1. Create a set of CFG Rules.
2. Load your rules into the program.
3. Create an instance of *ContextFreeGrammar* using your rules.
4. If no IllegalArgumentError occurs on creation of the *ContextFreeGrammar*, then your rules are semantically correct.
5. Run the CFG.

---

## JavaDoc Documentation:

Whenever a Travis CI build passes, the JavaDocs are auto-generated and made available at the following link.

https://valkryst.github.io/VParser_CFG/.