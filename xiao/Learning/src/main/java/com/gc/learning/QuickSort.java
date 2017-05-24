package com.gc.learning;

/** 
* @ClassName: QuickSort 
* @Description: 快速排序
* @author chen.gao@baidao.com   
* @date 2017年5月18日 下午5:40:52 
*  
*/

public class QuickSort {

	public static int move(int[] array, int start, int end) {
		int middle = array[end];
		int key = start;
		for (int i = start; i < end; i++) {
			if (array[i] <= middle) {
				int t = array[i];
				array[i] = array[key];
				array[key] = t;
				key++;
			}
		}
		int t = array[key];
		array[key] = array[end];
		array[end] = t;
		return key;
	}

	public static void sort(int[] array, int start, int end) {
		if (end > start) {
			int middle = move(array, start, end);
			sort(array, start, middle - 1);
			sort(array, middle, end);
		}
	}

	public static void main(String[] args) {
		int[] array = { 4, 2, 6, 23, 5, 4, 8, 3, 78, 456, 878, 45, 36 };
		sort(array, 0, array.length - 1);
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			System.out.print(" ");
		}
	}
}
