![Java CI with Maven](https://github.com/Valkryst/VParser_CFG/workflows/Java%20CI%20with%20Maven/badge.svg?branch=master)


## Table of Contents

* [Installation](https://github.com/Valkryst/VParser_CFG#installation)
    * [Gradle](https://github.com/Valkryst/VParser_CFG#-gradle)
    * [Maven](https://github.com/Valkryst/VParser_CFG#-maven)
    * [sbt](https://github.com/Valkryst/VParser_CFG#-scala-sbt)
* [Usage](https://github.com/Valkryst/VParser_CFG#usage)
  * [Notes](https://github.com/Valkryst/VParser_CFG#usage)
  * [Example](https://github.com/Valkryst/VParser_CFG#usage)

## Installation

VParser_CFG is hosted on the [JitPack package repository](https://jitpack.io/#Valkryst/VParser_CFG)
which supports Gradle, Maven, and sbt.

### ![Gradle](https://i.imgur.com/qtc6bXq.png?1) Gradle

Add JitPack to your `build.gradle` at the end of repositories.

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

Add VParser_CFG as a dependency.

```
dependencies {
	implementation 'com.github.Valkryst:VParser_CFG:2022.01.08-2'
}
```

### ![Maven](https://i.imgur.com/2TZzobp.png?1) Maven

Add JitPack as a repository.

``` xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```
Add VParser_CFG as a dependency.

```xml
<dependency>
    <groupId>com.github.Valkryst</groupId>
    <artifactId>VParser_CFG</artifactId>
    <version>2022.01.08-2</version>
</dependency>
```

### ![Scala SBT](https://i.imgur.com/Nqv3mVd.png?1) Scala SBT

Add JitPack as a resolver.

```
resolvers += "jitpack" at "https://jitpack.io"
```

Add VParser_CFG as a dependency.

```
libraryDependencies += "com.github.Valkryst" % "VParser_CFG" % "2022.01.08-2"
```

## Usage

1. Create a set of rules.
2. Create a `ContextFreeGrammar` using the rules.
3. Run the `ContextFreeGrammar`.

### Notes

A `ContextFreeGrammar` requires at least one rule, and each rule must begin with
a terminal and be followed by zero or more non-terminals. These are examples
of valid rules:

```
A aA aB aC a
B bA bC b
c
A thisA isB
B aA aC
C validA ruleB c
```

### Example

```java
public class Example {
	public static void main(final String[] args) {
		/*
		 * The following set of rules was created using these names:
		 *
		 * 		Balin, Bifur, Bofur, Bombur, Borin, Dain, Dis, Dori, Dwalin,
		 * 		Farin, Fili, Floi, Frar, Frerin, Fror, Fundin, Gaiml, Gimli,
		 * 		Gloin, Groin, Gror, Ibun, Khim, Kili, Loni, Mim, Nain, Nali,
		 * 		Nar, Narvi, Nori, Oin, Ori, Telchar, Thorin, Thrain, Thror
		 */
		final var rules = new String[]{
            "A B C D E F G H I K L M N O P Q R S T U V W X Y Z",
            "A a aL aI aR",
            "B b bA bI bO",
            "C c",
            "D d dA dI dO dW dU",
            "E e eR eL",
            "F f fA fI fL fR fU fO",
            "G g gA gI gL gR",
            "H h hI hA",
            "I i",
            "K k kH kI",
            "L l lO",
            "M m mI",
            "N n nA nO",
            "O o oI oR",
            "P p",
            "Q q",
            "R r rI rO rV",
            "S s",
            "T t tE tH",
            "U u uR uN",
            "V v",
            "W w wA",
            "X x",
            "Y y",
            "Z z"
		};

		final var cfg = new ContextFreeGrammar(rules);

		for (int i = 0 ; i < 1000 ; i++) {
			String res = cfg.run();

			// Exclude low-quality results.
			if (res.length() > 3) {
				System.out.println(res);
			}
		}
	}
}
```

```
guroro
hfkh
eroi
forv
oror
bunoroi
erorv
gnoi
thlo
glorv
oroi
lori
loroi
hter
wurv
funo
elori
thhi
furi
norv
ungrv
unoi
teri
dher
loro
froi
...
```
