for file in `ls testsource/*.scala`; do
	scalac $file -d testclass
done;
