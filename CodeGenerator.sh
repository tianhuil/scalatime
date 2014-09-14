for i in 10 20 30 40 50; do
	scala CodeGenerator $i > testsource/TestCode$i.scala
done;
