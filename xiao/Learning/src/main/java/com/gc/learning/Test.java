package com.gc.learning;

/** 
* @ClassName: Test 
* @Description: TODO
* @author chen.gao@baidao.com   
* @date 2017年5月24日 下午4:13:26 
*  
*/

public class Test {

	public static void COUNTINGSORT(int[] A, int[] B, int array_size, int k) {
		int[] C = new int[k + 1];
		int i;
		int value;
		int pos;
		for (i = 0; i <= k; i++) {
			C[i] = 0;
		}
		for (i = 0; i < array_size; i++) {
			C[A[i]]++;
		}
		for (i = 1; i <= k; i++) {
			C[i] = C[i] + C[i - 1];
		}
		for (i = array_size - 1; i >= 0; i--) {
			value = A[i];
			pos = C[value];
			B[pos - 1] = value;
			C[value]--;
		}
	}

	public static void main(String[] args) {
		int[] A = { 2, 5, 3, 0, 2, 3, 0, 3 };
		int[] B = new int[8];
		COUNTINGSORT(A, B, 8, 5);
		for (int i = 0; i <= 7; i++) {
			System.out.print(B[i]);
		}
		System.out.println();
	}

}
