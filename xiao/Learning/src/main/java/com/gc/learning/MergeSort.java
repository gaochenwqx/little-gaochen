package com.gc.learning;

/** 
* @ClassName: MergeSort 
* @Description: TODO
* @author chen.gao@baidao.com   
* @date 2017年5月18日 上午11:01:41 
*  
*/

public class MergeSort {

	public static void main(String[] args) {
		int[] array = { 4, 2, 6, 23, 5, 4, 8, 3, 78, 456, 878, 45, 36 };
		merge(array, 0, array.length - 1);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			System.out.print(" ");
		}
	}

	public static void merge(int[] array, int start, int end) {
		if (end - start > 0) {
			int midle = (start + end) / 2;
			merge(array, start, midle);
			merge(array, midle + 1, end);
			sort(array, start, midle, end);
		}
	}

	public static void sort(int[] array, int start, int midle, int end) {
		int leftSize = midle - start + 1;
		int rightSize = end - midle;
		int[] left = new int[leftSize + 1];
		int[] right = new int[rightSize + 1];
		for (int i = 0; i < leftSize; i++) {
			left[i] = array[start + i];
		}
		left[leftSize] = Integer.MAX_VALUE;
		for (int i = 0; i < rightSize; i++) {
			right[i] = array[midle + i + 1];
		}
		right[rightSize] = Integer.MAX_VALUE;
		int li = 0;
		int ri = 0;
		for (int i = start; i <= end; i++) {
			if (left[li] <= right[ri]) {
				array[i] = left[li];
				li++;
			} else {
				array[i] = right[ri];
				ri++;
			}
		}
	}

}
