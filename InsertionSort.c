#include <stdlib.h>
#include <stdio.h>
#include <time.h>

//#include <jni.h>


// JNIEXPORT jintArray JNICALL Java_InsertionSort_insertsort
// (JNIEnv *env, jobject object, jintArray values) {
void insertionSort(int values[], int length);

int main(int argc, char** argv) {

	srand(time(NULL));   // should only be called once
	int array[10000];
	int i;
	for(i = 0; i < 10000; i++) {
		array[i] = rand();
	}

	insertionSort(&array, 10000);
	for(i = 0; i < 10000-1; i++) {
		if(!(array[i] <= array[i+1])) printf("WRONG");
	}


}

void insertionSort(int values[], int length) {

	int pos;
	int value;

	int i;
	for(i = 1; i <= length; i++) {
		value = values[i];
		pos = i;

		while(pos > 0 & values[pos-1] > value) {
			values[pos] = values[pos-1];
			pos--;
		}
		values[pos] = value;
	}
}