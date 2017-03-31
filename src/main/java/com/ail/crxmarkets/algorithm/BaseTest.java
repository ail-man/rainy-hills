package com.ail.crxmarkets.algorithm;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

public class BaseTest {

	List<Pair> getTestData() {
		List<Pair> testDataList = new ArrayList<>();

		testDataList.add(Pair.of(new int[] { 0 }, 0L));

		testDataList.add(Pair.of(new int[] { 7 }, 0L));
		testDataList.add(Pair.of(new int[] { 0, 0 }, 0L));

		testDataList.add(Pair.of(new int[] { 1, 1 }, 0L));
		testDataList.add(Pair.of(new int[] { 1, 0, 1 }, 1L));
		testDataList.add(Pair.of(new int[] { 1, 1, 0, 1 }, 1L));
		testDataList.add(Pair.of(new int[] { 1, 0, 0, 1 }, 2L));
		testDataList.add(Pair.of(new int[] { 1, 0, 0, 0, 1 }, 3L));

		testDataList.add(Pair.of(new int[] { 3, 3 }, 0L));
		testDataList.add(Pair.of(new int[] { 3, 0, 3 }, 3L));
		testDataList.add(Pair.of(new int[] { 3, 3, 0, 3 }, 3L));
		testDataList.add(Pair.of(new int[] { 3, 0, 0, 3 }, 6L));
		testDataList.add(Pair.of(new int[] { 3, 0, 0, 0, 3 }, 9L));

		testDataList.add(Pair.of(new int[] { 3, 0, 2 }, 2L));
		testDataList.add(Pair.of(new int[] { 2, 0, 3 }, 2L));

		testDataList.add(Pair.of(new int[] { 3, 0, 1 }, 1L));
		testDataList.add(Pair.of(new int[] { 1, 0, 3 }, 1L));

		testDataList.add(Pair.of(new int[] { 3, 3, 0, 3 }, 3L));
		testDataList.add(Pair.of(new int[] { 3, 0, 0, 0, 3 }, 9L));

		testDataList.add(Pair.of(new int[] { 3, 3, 3, 3 }, 0L));

		testDataList.add(Pair.of(new int[] { 3, 1, 2, 0, 3 }, 6L));

		testDataList.add(Pair.of(new int[] { 5, 3, 0, 2, 4 }, 7L));
		testDataList.add(Pair.of(new int[] { 4, 4, 2, 0, 5 }, 6L));

		testDataList.add(Pair.of(new int[] { 4, 0, 2, 1, 0, 3, 3 }, 9L));
		testDataList.add(Pair.of(new int[] { 3, 0, 2, 1, 0, 4, 4 }, 9L));
		testDataList.add(Pair.of(new int[] { 3, 0, 2, 1, 0, 4, 7 }, 9L));

		testDataList.add(Pair.of(new int[] { 8, 1, 1, 4, 2, 1, 1, 4, 4, 2, 7, 3, 2, 7, 2 }, 52L));
		testDataList.add(Pair.of(new int[] { 8, 1, 1, 4, 2, 1, 1, 4, 4, 2, 7, 3, 2, 7, 2, 8 }, 71L));
		testDataList.add(Pair.of(new int[] { 2, 4, 0, 2, 2, 4, 3, 3, 7, 4, 4, 5, 3, 2, 6, 3, 5, 5, 5, 8, 8, 8, 6, 1, 9, 9, 7, 1, 4, 4 }, 50L));
		testDataList.add(Pair.of(new int[] { 2, 4, 0, 2, 2, 4, 3, 3, 7, 4, 4, 5, 3, 2, 6, 3, 5, 5, 5, 8, 8, 8, 6, 1, 6, 6, 7, 1, 4, 4 }, 50L));
		testDataList.add(Pair.of(new int[] { 5, 3, 7, 2, 6, 4, 5, 9, 1, 2 }, 14L));
		testDataList.add(Pair.of(new int[] { 5, 3, 7, 2, 6, 4, 5, 9 }, 13L));
		testDataList.add(Pair.of(new int[] { 5, 3, 7, 2, 6, 4, 5 }, 7L));
		testDataList.add(Pair.of(new int[] { 5, 3, 7, 2, 6, 4 }, 6L));
		testDataList.add(Pair.of(new int[] { 5, 3, 7, 2, 6 }, 6L));

		testDataList.add(Pair.of(new int[] { 2, 3, 4, 4, 3, 2, 3, 4, 5 }, 4L));
		testDataList.add(Pair.of(new int[] { 2, 3, 4, 4, 3, 2, 3, 4, 5, 4 }, 4L));
		testDataList.add(Pair.of(new int[] { 2, 3, 4, 4, 3, 2, 3, 4, 5, 5 }, 4L));
		testDataList.add(Pair.of(new int[] { 2, 3, 4, 4, 3, 2, 3, 4, 5, 8 }, 4L));
		testDataList.add(Pair.of(new int[] { 4, 1, 1, 0, 2, 3 }, 8L));

		testDataList.add(Pair.of(new int[] { 5, 3 }, 0L));
		testDataList.add(Pair.of(new int[] { 5, 3, 7 }, 2L));
		testDataList.add(Pair.of(new int[] { 5, 3, 4, 6 }, 3L));

		testDataList.add(Pair.of(new int[] { 3, 2, 1 }, 0L));
		testDataList.add(Pair.of(new int[] { 1, 2, 3 }, 0L));
		testDataList.add(Pair.of(new int[] { 1, 2, 1 }, 0L));

		return testDataList;
	}
}