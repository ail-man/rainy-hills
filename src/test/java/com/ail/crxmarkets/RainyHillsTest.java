package com.ail.crxmarkets;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Ignore;
import org.junit.Test;

public class RainyHillsTest {

	@Test
	public void calcVesselVolume() throws Exception {
		RainyHills rainyHills = new RainyHills();

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

		for (TestData testData : testDataList) {
			assertThat(rainyHills.calcVesselVolume(testData.getSurface(), 0, testData.getSurface().length - 1), equalTo(testData.getWaterVolume()));
		}
	}

	@Test
	public void testPrintHills() throws Exception {
		RainyHills rainyHills = new RainyHills();
		rainyHills.printSurface(new int[] { 3, 2, 4, 1, 2 });
	}

	@Test
	@Ignore
	public void testCalcWaterVolumeOnSurface() throws Exception {
		RainyHills rainyHills = new RainyHills();

		List<TestData> testDataList = new ArrayList<>();
		testDataList.add(new TestData(new int[] { 2, 3, 4, 4, 3, 2, 3, 4, 5 }, 4));
		testDataList.add(new TestData(new int[] { 2, 3, 4, 4, 3, 2, 3, 4, 5, 4 }, 4));
		testDataList.add(new TestData(new int[] { 2, 3, 4, 4, 3, 2, 3, 4, 5, 5 }, 4));
		testDataList.add(new TestData(new int[] { 2, 3, 4, 4, 3, 2, 3, 4, 5, 8 }, 4));
		testDataList.add(new TestData(new int[] { 3, 2, 4, 1, 2 }, 2));

		for (TestData testData : testDataList) {
			assertThat(rainyHills.calcWaterVolumeOnSurface(testData.getSurface()), equalTo(testData.getWaterVolume()));
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
