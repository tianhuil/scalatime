all: testcode/TestCode.class

CodeGenerator.class: CodeGenerator.scala
	scalac CodeGenerator.scala -d

testcode/TestCode.scala: CodeGenerator.class
	scala CodeGenerator.class > testcode/TestCode.scala

testcode/TestCode.class: testcode/TestCode.scala
	scalac testcode/TestCode.scala -d testcode
