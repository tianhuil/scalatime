all: analysis

ForCompCodeGen.class: ForCompCodeGen.scala
	scalac ForCompCodeGen.scala

ImplicitCodeGen.class: ImplicitCodeGen.scala
	scalac ImplicitCodeGen.scala

testsource/CodeGenerator.sentinel: ForCompCodeGen.class ImplicitCodeGen.class CodeGenerator.sh
	. CodeGenerator.sh
	touch testsource/CodeGenerator.sentinel

analysis: testsource/CodeGenerator.sentinel time_compile.py
	ls testsource/*.scala | xargs python time_compile.py

clean:
	rm testsource/*
