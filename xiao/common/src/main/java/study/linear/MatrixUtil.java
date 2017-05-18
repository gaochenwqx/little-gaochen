package study.linear;

import java.util.ArrayList;
import java.util.List;

/** 
* @ClassName: Matrix 
* @Description: 矩阵工具
* @author chen.gao@baidao.com   
* @date 2017年1月24日 下午2:07:38 
*  
*/

public class MatrixUtil {

	public static void main(String[] args) throws Exception {
		List<Long> matrix = new ArrayList<Long>();
		//		matrix.add(1L);
		//		matrix.add(2L);
		//		matrix.add(3L);
		//		matrix.add(-1L);
		//		matrix.add(0L);
		//		matrix.add(5L);
		//		matrix.add(4L);
		//		matrix.add(7L);
		//		matrix.add(6L);
		////////////////
		//		matrix.add(0L);
		//		matrix.add(0L);
		//		matrix.add(0L);
		//		matrix.add(1L);
		//		matrix.add(0L);
		//		matrix.add(0L);
		//		matrix.add(2L);
		//		matrix.add(0L);
		//		matrix.add(0L);
		//		matrix.add(3L);
		//		matrix.add(0L);
		//		matrix.add(0L);
		//		matrix.add(4L);
		//		matrix.add(0L);
		//		matrix.add(0L);
		//		matrix.add(0L);
		//		System.out.println(determinant(matrix));
	}

	/**
	 * 行列式计算
	 */
	public static long determinant(List<Long> matrix) throws Exception {
		if (matrix == null) {
			throw new Exception("matrix must not be null");
		}
		int size = matrix.size();
		double doubleN = Math.sqrt(size);
		if (doubleN != (int) doubleN) {
			throw new Exception("this collection is not matrix ");
		}
		int n = (int) doubleN;
		long sum = 0L;
		for (int i = 0; i < 2 * n - 3; i++) {
			long pn = 1L;
			long mn = 1L;
			for (int j = 0; j < n; j++) {
				System.out.print((i + j * n + j) % n + j * n);
				System.out.print(" -");
				pn *= matrix.get((i + j * n + j) % n + j * n);
				System.out.print((j * n + 1) * 2 - (n + 1) % 2 - (i + j * n + j) % n - j * n);
				System.out.print(" ");
				mn *= matrix.get((j * n + 1) * 2 - (n + 1) % 2 - (i + j * n + j) % n - j * n);
			}
			System.out.println();
			sum += pn - mn;
		}
		return sum;
	}

	public static int inverseOrdinalNumbers(List<Long> queue) throws Exception {
		if (queue == null) {
			throw new Exception("queue must not be null");
		}
		int sum = 0;
		int N = queue.size();
		int[] subSum = new int[N];
		int[] inverseNumber = new int[N];
		List<Long> inverseElement = new ArrayList<Long>();
		for (int i = 0; i < queue.size(); i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (queue.get(i) - queue.get(j) < 0) {
					sum++;
					subSum[i]++;

				}
			}
		}
		return sum;
	}

}
