for i in 10 20 30 40 50 60; do
	scala CodeGenerator NestedForComp $i 3 > testsource/NestedForComp$i.scala
done;

for i in 10 20 30 40 50 60; do
	scala CodeGenerator MultiForComp 3 $i > testsource/MultiForComp$i.scala
done;
