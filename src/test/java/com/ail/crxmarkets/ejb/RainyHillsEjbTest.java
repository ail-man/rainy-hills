package com.ail.crxmarkets.ejb;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;

public class RainyHillsEjbTest {

	@Test
	public void testCalcWaterVolumeOnSurface() throws Exception {
		RainyHillsEjb rainyHillsEjb = new RainyHillsEjb();

		List<TestData> testDataList = new ArrayList<>();

		testDataList.add(new TestData(new int[] { 0 }, 0));

		testDataList.add(new TestData(new int[] { 7 }, 0));
		testDataList.add(new TestData(new int[] { 0, 0 }, 0));

		testDataList.add(new TestData(new int[] { 1, 1 }, 0));
		testDataList.add(new TestData(new int[] { 1, 0, 1 }, 1));
		testDataList.add(new TestData(new int[] { 1, 1, 0, 1 }, 1));
		testDataList.add(new TestData(new int[] { 1, 0, 0, 1 }, 2));
		testDataList.add(new TestData(new int[] { 1, 0, 0, 0, 1 }, 3));

		testDataList.add(new TestData(new int[] { 3, 3 }, 0));
		testDataList.add(new TestData(new int[] { 3, 0, 3 }, 3));
		testDataList.add(new TestData(new int[] { 3, 3, 0, 3 }, 3));
		testDataList.add(new TestData(new int[] { 3, 0, 0, 3 }, 6));
		testDataList.add(new TestData(new int[] { 3, 0, 0, 0, 3 }, 9));

		testDataList.add(new TestData(new int[] { 3, 0, 2 }, 2));
		testDataList.add(new TestData(new int[] { 2, 0, 3 }, 2));

		testDataList.add(new TestData(new int[] { 3, 0, 1 }, 1));
		testDataList.add(new TestData(new int[] { 1, 0, 3 }, 1));

		testDataList.add(new TestData(new int[] { 3, 3, 0, 3 }, 3));
		testDataList.add(new TestData(new int[] { 3, 0, 0, 3 }, 6));
		testDataList.add(new TestData(new int[] { 3, 0, 0, 0, 3 }, 9));

		testDataList.add(new TestData(new int[] { 3, 3, 3, 3 }, 0));

		testDataList.add(new TestData(new int[] { 3, 1, 2, 0, 3 }, 6));

		testDataList.add(new TestData(new int[] { 5, 3, 0, 2, 4 }, 7));
		testDataList.add(new TestData(new int[] { 4, 4, 2, 0, 5 }, 6));

		testDataList.add(new TestData(new int[] { 4, 0, 2, 1, 0, 3, 3 }, 9));
		testDataList.add(new TestData(new int[] { 3, 0, 2, 1, 0, 4, 4 }, 9));
		testDataList.add(new TestData(new int[] { 3, 0, 2, 1, 0, 4, 7 }, 9));

		testDataList.add(new TestData(new int[] { 8, 1, 1, 4, 2, 1, 1, 4, 4, 2, 7, 3, 2, 7, 2 }, 52));
		testDataList.add(new TestData(new int[] { 8, 1, 1, 4, 2, 1, 1, 4, 4, 2, 7, 3, 2, 7, 2, 8 }, 71));
		testDataList.add(new TestData(new int[] { 2, 4, 0, 2, 2, 4, 3, 3, 7, 4, 4, 5, 3, 2, 6, 3, 5, 5, 5, 8, 8, 8, 6, 1, 9, 9, 7, 1, 4, 4 }, 50));
		testDataList.add(new TestData(new int[] { 2, 4, 0, 2, 2, 4, 3, 3, 7, 4, 4, 5, 3, 2, 6, 3, 5, 5, 5, 8, 8, 8, 6, 1, 6, 6, 7, 1, 4, 4 }, 50));
		testDataList.add(new TestData(new int[] { 5, 3, 7, 2, 6, 4, 5, 9, 1, 2 }, 14));
		testDataList.add(new TestData(new int[] { 5, 3, 7, 2, 6, 4, 5, 9 }, 13));
		testDataList.add(new TestData(new int[] { 5, 3, 7, 2, 6, 4, 5 }, 7));
		testDataList.add(new TestData(new int[] { 5, 3, 7, 2, 6, 4 }, 6));
		testDataList.add(new TestData(new int[] { 5, 3, 7, 2, 6 }, 6));

		testDataList.add(new TestData(new int[] { 2, 3, 4, 4, 3, 2, 3, 4, 5 }, 4));
		testDataList.add(new TestData(new int[] { 2, 3, 4, 4, 3, 2, 3, 4, 5, 4 }, 4));
		testDataList.add(new TestData(new int[] { 2, 3, 4, 4, 3, 2, 3, 4, 5, 5 }, 4));
		testDataList.add(new TestData(new int[] { 2, 3, 4, 4, 3, 2, 3, 4, 5, 8 }, 4));
		testDataList.add(new TestData(new int[] { 4, 1, 1, 0, 2, 3 }, 8));

		testDataList.add(new TestData(new int[] { 5, 3 }, 0));
		testDataList.add(new TestData(new int[] { 5, 3, 7 }, 2));
		testDataList.add(new TestData(new int[] { 5, 3, 4, 6 }, 3));

		testDataList.add(new TestData(new int[] { 3, 2, 1 }, 0));
		testDataList.add(new TestData(new int[] { 1, 2, 3 }, 0));
		testDataList.add(new TestData(new int[] { 1, 2, 1 }, 0));

		for (TestData testData : testDataList) {
			assertThat(rainyHillsEjb.calcWaterVolumeOnSurface(testData.getSurface()), equalTo(testData.getWaterVolume()));
		}
	}

	private static class TestData {
		private final int[] surface;
		private final int waterVolume;

		TestData(int[] surface, int waterVolume) {
			this.surface = surface;
			this.waterVolume = waterVolume;
		}

		int[] getSurface() {
			return surface;
		}

		int getWaterVolume() {
			return waterVolume;
		}
	}

}
