for i in 10 20 30 40 50 60; do
	scala ForCompCodeGen NestedForComp $i 3 > testsource/NestedForComp$i.scala
done;

for i in 10 20 30 40 50 60; do
	scala ForCompCodeGen MultiForComp 3 $i > testsource/MultiForComp$i.scala
done;

for i in 10 20 30 40; do
	j=`expr 60 - $i`  # number of implicits
	scala ImplicitCodeGen Implicit $i $j 20 > testsource/Implicit$j.scala
done;

for i in 2 3 4 5 6 7 8; do
	scala CaseMatchCodeGenerator CaseMatch $i > testsource/CaseMatch$i.scala
done;

