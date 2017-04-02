package com.ail.crxmarkets;

import com.ail.crxmarkets.exception.ApplicationException;
import org.apache.commons.lang3.RandomUtils;

public class Utils {

	public static int[] randomArray(int length, int min, int max) {
		int[] arr = new int[length];
		for (int i = 0; i < length; i++) {
			arr[i] = RandomUtils.nextInt(min, max);
		}
		return arr;
	}

	@SuppressWarnings("WeakerAccess")
	public static int max(int[] arr) {
		int result = arr[0];
		for (int elem : arr) {
			if (elem > result) {
				result = elem;
			}
		}
		return result;
	}

	public static int min(int[] arr) {
		int result = arr[0];
		for (int elem : arr) {
			if (elem < result) {
				result = elem;
			}
		}
		return result;
	}

	public static long sum(int[] arr) {
		long sum = 0;
		for (int i : arr) {
			sum += i;
		}
		return sum;
	}

	public static int average(int[] arr) {
		double average = 0;

		double sum = 0;
		for (int elem : arr) {
			sum += elem;
		}

		if (sum != 0) {
			average = sum / arr.length;
		}

		return (int) average;
	}

	public static int[] parseIntArray(String string) throws ApplicationException {
		try {
			String[] array = string.replaceAll("[ \n\r]", "").split(",");
			int[] result = new int[array.length];
			for (int i = 0; i < array.length; i++) {
				result[i] = Integer.parseInt(array[i]);
			}
			return result;
		} catch (Exception e) {
			throw new ApplicationException(e);
		}
	}

	public static String printAsText(int[] arr) {
		if (arr == null || arr.length == 0) {
			return "";
		}

		StringBuilder sb = new StringBuilder().append(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			sb.append(',').append(arr[i]);
		}

		return sb.toString();
	}

}
