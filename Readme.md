Data Science for Compiler Speeds
--------------------------------

This repository tests the compile time of various `scala` language features.  It does this by auto-generating code, compiling it, and measuring the shortest-of-three compiles.

The main idea behind this analysis is to generate code that uses a variable number of the same feature (e.g. 10, 20, 30 implicits) but is otherwise the same.  We then compare the compile times to establish the cost of an additional use of this language feature (e.g. the additional compile time for a single implicit).

We are primarily interested in the relative cost of different features so the results should be independent of the exact machine on which the results are run.

Features tested
---------------
So far, we have only tested two features:
1. for comprehensions
1. implicits

Results
-------
Contrary to popular lore, implicits are not very slow!  A file with 60 implicants and functions that need 10 of them only takes 3.5 seconds to compile.  By increasing the number of implicits in the code, we can tell it's about **0.3ms per additional implicit per function invocation**.

On the other hand, for-comprehensions (over options and lists) are expensive.  When nested, they are about **17ms - 26ms per level** (i.e. per `flatmap` / `map` that they represent).  The lower estimate is for a for comprehension is just appended to the first list:
```
val x0 = for {
	v0 <- list
	...
} yield ...

val x1 = for {
	v1 <- list
	...
} yield ...
```
The higher estimate is when the additional element is nested, i.e.
```
val x0 = for {
	v0 <- list1
	v1 <- list2
	...
} yield ...
```
This shouldn't be that surprising since for comprehensions have a complex amount of type inference that has to go on.

To run the code
---------------

To run the code, just type
```
make
```
Below is the output from from running the makefile (it is somewhat difficult to parse without looking at the source code).  It was run on a Macbook Pro with 16GB of Ram and a 2.3 Ghz i7 processor running OSX 10.9.4.
```
testsource/Implicit10.scala
3.94771099091
testsource/Implicit20.scala
4.02056384087
testsource/Implicit30.scala
4.10808706284
testsource/Implicit40.scala
4.19162583351
testsource/Implicit50.scala
4.18769097328
testsource/MultiForComp10.scala
3.46595621109
testsource/MultiForComp20.scala
4.12855482101
testsource/MultiForComp30.scala
4.71714091301
testsource/MultiForComp40.scala
5.25777506828
testsource/MultiForComp50.scala
5.56335186958
testsource/MultiForComp60.scala
6.14822793007
testsource/NestedForComp10.scala
3.54741191864
testsource/NestedForComp20.scala
4.53939890862
testsource/NestedForComp30.scala
5.23711299896
testsource/NestedForComp40.scala
5.98019194603
testsource/NestedForComp50.scala
6.87505888939
testsource/NestedForComp60.scala
7.71049785614
```