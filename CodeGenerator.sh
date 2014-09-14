for i in 10 20 30 40 50 60; do
	scala CodeGenerator 10 $i > testsource/TestCode$i.scala
done;
