for i in 10 25 50 75 100; do
	scala CodeGenerator $i > testsource/TestCode$i.scala
done;
