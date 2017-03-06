make:
	javac *.java
	javah InsertionSort
	#gcc -c -fPIC -I/System/Library/Frameworks/JavaVM.framework/Versions/A/Headers/ lib_InsertionSort.c -o libInsertionSort.o
	#libtool -dynamic -lSystem libInsertionSort.o -o libInsertionSort.dylib
	#LD_LIBRARY_PATH=$LD_LIBRARY_PATH:.

	gcc -I$(JAVA_HOME)/include -I$(JAVA_HOME)/include/linux -shared -fpic -o libInsertionSort.so lib_InsertionSort.c
		

clean:
	rm Adjudicator.class
	rm Executive.class
	rm HeapSort.class
	rm InsertionSort.class
	rm PrimaryV.class
	rm DataGenerator.class
	rm FileRW.class
	rm SecondaryV.class
	rm WatchDog.class
	rm libInsertionSort.dylib
	rm libInsertionSort.o
	rm InsertionSort.h

run:
	export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:.
	java DataGenerator test.txt 1000
	java Executive test.txt test2.txt 0.00001 0.00001 1000
