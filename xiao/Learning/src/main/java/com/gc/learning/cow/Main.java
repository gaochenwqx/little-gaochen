package com.gc.learning.cow;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			int a = scanner.nextInt();
			System.out.println(get(a - 1));
		}
	}

	public static int get(int n) {
		List<Integer> list = new LinkedList<Integer>();
		list.add(4);
		while (n-- > 0) {
			int size = list.size();
			for (int i = 0; i < size; i++) {
				list.set(i, list.get(i) + 1);
				if (list.get(i) >= 4) {
					list.add(1);
				}
			}
		}
		return list.size();
	}

}
