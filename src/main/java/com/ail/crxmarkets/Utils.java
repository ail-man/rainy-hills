package com.ail.crxmarkets;

import org.apache.commons.lang3.RandomUtils;

public class Utils {

	public static int[] randomArray(int length, int min, int max) {
		int[] arr = new int[length];
		for (int i = 0; i < length; i++) {
			arr[i] = RandomUtils.nextInt(min, max);
		}
		return arr;
	}

	public static long sum(int[] arr) {
		long sum = 0;
		for (int i : arr) {
			sum += i;
		}
		return sum;
	}

}
