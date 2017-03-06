#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#include <jni.h>
#include "insertionSort.h"

JNIEXPORT jintArray JNICALL Java_InsertionSort_insertionSort (JNIEnv *env, jobject object, jintArray values) {
	jintArray returnValues;
	jsize len; 
	jint* newValues;
	jint *mem_acc_count;
	jint pos;
	jint value;
	jboolean *iscopy;

	len = (*env)->GetArrayLength(env, values);
	newValues = (jint *) (*env)->GetIntArrayElements(env, values, iscopy);
	*mem_acc_count += 2;

	int i;
	for(i = 1; i < len; i++) {
		value = newValues[i];
		pos = i;
		*mem_acc_count += 1;

		while(pos > 0 & newValues[pos-1] > value) {
			newValues[pos] = newValues[pos-1];
			pos--;
			*mem_acc_count += 1;
		}
		newValues[pos] = value;
		*mem_acc_count += 1;
	}
	
	jintArray result;
	result = (*env)->NewIntArray(env, len+1);
	(*env)->SetIntArrayRegion(env, result, 0, len, newValues);
	(*env)->SetIntArrayRegion(env, result, len, 1, mem_acc_count);
	return result;
}
