package com.ail.crxmarkets;

import com.ail.crxmarkets.exception.ApplicationException;
import org.apache.commons.lang3.RandomUtils;

/**
 * Helper class with different util methods
 *
 * @author Arthur Lomsadze (ailman1985@gmail.com)
 */
public class Utils {

	/**
	 * Generates array with random values
	 *
	 * @param length length of the array to be generated
	 * @param min    min inclusive bound of values to be generated in array
	 * @param max    max exclusive bound of values to be generated in array
	 * @return generated array
	 */
	public static int[] randomArray(int length, int min, int max) {
		int[] arr = new int[length];
		for (int i = 0; i < length; i++) {
			if (min < 0 && max < 0) {
				arr[i] = -RandomUtils.nextInt(-max, -min);
			} else if (min < 0) {
				arr[i] = RandomUtils.nextInt(0, max - min) + min;
			} else {
				arr[i] = RandomUtils.nextInt(min, max);
			}
		}
		return arr;
	}

	/**
	 * Gets max value in array
	 *
	 * @param arr an array to search
	 * @return max value
	 */
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

	/**
	 * Gets min value in array
	 *
	 * @param arr an array to search
	 * @return min value
	 */
	public static int min(int[] arr) {
		int result = arr[0];
		for (int elem : arr) {
			if (elem < result) {
				result = elem;
			}
		}
		return result;
	}

	/**
	 * Sums all value in array
	 *
	 * @param arr an array
	 * @return sum of all values in array
	 */
	public static long sum(int[] arr) {
		long sum = 0;
		for (int i : arr) {
			sum += i;
		}
		return sum;
	}

	/**
	 * Calculates the average value of values in array
	 *
	 * @param arr an array to calculate
	 * @return average value
	 */
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

	/**
	 * Parses an array of ints from string. Values must be separated by commas
	 *
	 * @param string string for parse
	 * @return array of parsed values
	 * @throws ApplicationException exception wrapper for this application
	 */
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

	/**
	 * Prints an array as string of values separated by commas
	 *
	 * @param arr array to print as text
	 * @return string of values separated by commas
	 */
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
