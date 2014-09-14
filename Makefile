all: analysis

CodeGenerator.class: CodeGenerator.scala
	scalac CodeGenerator.scala

testsource/CodeGenerator.sentinel: CodeGenerator.class CodeGenerator.sh
	. CodeGenerator.sh
	touch testsource/CodeGenerator.sentinel

analysis: testsource/CodeGenerator.sentinel time_compile.py
	ls testsource/*.scala | xargs python time_compile.py

clean:
	rm testsource/*
