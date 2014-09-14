all: testclass/TestCode.class

CodeGenerator.class: CodeGenerator.scala
	scalac CodeGenerator.scala

testsource/CodeGenerator.sentinel: CodeGenerator.class
	. ./CodeGenerator.sh
	touch testsource/CodeGenerator.sentinel

testclass/CodeCompiler.sentinel: testsource/CodeGenerator.sentinel
	. ./CodeCompiler.sh
	touch testclass/CodeCompiler.sentinel

clean:
	rm testsource/*
