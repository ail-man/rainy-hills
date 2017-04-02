package com.ail.crxmarkets.model.waterfill;

import java.util.ArrayList;
import java.util.List;

import com.ail.crxmarkets.Utils;
import com.ail.crxmarkets.draw.SurfaceDrawer;
import com.ail.crxmarkets.model.Surface;
import org.apache.commons.lang3.tuple.Pair;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Test helper class for {@link WaterFillMethod} algorithms testing
 */
public class WFMFullTestHelper {

	private static final int TEST_RUN_COUNT = 100000;
	private static final int SURFACE_LENGTH = 100000;
	private static final int SURFACE_MAX_HEIGHT = 100000;

	/**
	 * Test for check {@link WaterFillMethod} algorithm efficiency
	 *
	 * @param waterFillMethod algorithm to check efficiency
	 * @param methodName      just for print in console
	 */
	public void testWaterFillMethodEfficiency(WaterFillMethod waterFillMethod, String methodName) {
		Surface surface = Surface.random(SURFACE_LENGTH, 0, SURFACE_MAX_HEIGHT);
		int[] calculationTimeArr = new int[TEST_RUN_COUNT];
		for (int i = 0; i < TEST_RUN_COUNT; i++) {
			long startTime = System.nanoTime();
			surface.fillWater(waterFillMethod, null);
			calculationTimeArr[i] = (int) (System.nanoTime() - startTime);
		}

		System.out.println("Average calculation time of " + methodName + ": " + Utils.average(calculationTimeArr) + " ns");
	}

	/**
	 * Generates test arrays of surface with different curves
	 *
	 * @param waterFillMethod an {@link WaterFillMethod} object of water filling
	 *                        algorithm
	 * @param surfaceDrawer   an {@link SurfaceDrawer} object for the draw
	 */
	public void testWaterFillMethod(WaterFillMethod waterFillMethod, SurfaceDrawer surfaceDrawer) {
		for (Pair<int[], Long> testData : getTestData()) {
			Surface surface = new Surface(testData.getLeft());
			surfaceDrawer.draw(surface);

			surface.fillWater(waterFillMethod, null);

			assertThat(surface.getWater().length, equalTo(surface.getSurface().length));
			surfaceDrawer.drawWithWater(surface);

			long waterTotal = testData.getRight();
			assertThat(Utils.sum(surface.getWater()), equalTo(waterTotal));
		}
	}

	private List<Pair<int[], Long>> getTestData() {
		List<Pair<int[], Long>> testDataList = new ArrayList<>();

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

		testDataList.add(Pair.of(new int[] { -3, 0, 2, 1, 0, 3, 3 }, 3L));
		testDataList.add(Pair.of(new int[] { -1, 0, -2, -1, 0, 2, -4 }, 3L));
		testDataList.add(Pair.of(new int[] { 3, 0, 2, -1, 0, 4, -1 }, 11L));

		testDataList.add(Pair.of(new int[] { -5, 6, -1, 7, 3, 3, 5, 1, -3, -2, -2, 0, 2 }, 27L));
		testDataList.add(Pair.of(new int[] { 0, -1, -4, -1, -3, 0, -1, }, 9L));
		testDataList.add(Pair.of(new int[] { -2, -4, 0, -2, -2, -4, -3, -3, -7, -4, -4, -5, -3, -2, -6, -3, -5, -5, -5, -8, -8, -8, -6, -1, -9, -9, -7, -1, -4, -4 }, 97L));
		testDataList.add(Pair.of(new int[] { -2, -4, 0, -2, -2, -4, -3, -3, -7, -4, -4, -5, -3, -2, -6, -3, -5, -5, -5, -8, -8, -8, -6, -1, -6, -6, -7, -1, -4, -4 }, 91L));
		testDataList.add(Pair.of(new int[] { -10, -5, -9, -3, -10, 4, -2, -8, 0, 9, 7, -1, -6, 5, -1, -6, 2, 5, -3, -4, 6, 1, -6, -4, -10, -5, 7, -2, 9, 5 }, 210L));
		testDataList.add(Pair.of(new int[] { -5, -3, -7, -2, -6, -4, -5, -9, -1, -2 }, 20L));
		testDataList.add(Pair.of(new int[] { -5, -3, -7, -2, -6, -4, -5, -9 }, 6L));
		testDataList.add(Pair.of(new int[] { -5, -3, -7, -2, -6, -4, -5 }, 6L));
		testDataList.add(Pair.of(new int[] { -5, -3, -7, -2, -6, -4 }, 6L));
		testDataList.add(Pair.of(new int[] { -5, -3, -7, -2, -6 }, 4L));

		testDataList.add(Pair.of(new int[] { -7 }, 0L));

		testDataList.add(Pair.of(new int[] { -1, -1 }, 0L));
		testDataList.add(Pair.of(new int[] { 0, -1, 0 }, 1L));
		testDataList.add(Pair.of(new int[] { 0, 0, -1, 0 }, 1L));
		testDataList.add(Pair.of(new int[] { -1, 0, 0, -1 }, 0L));
		testDataList.add(Pair.of(new int[] { -1, 0, 0, 0, -1 }, 0L));

		testDataList.add(Pair.of(new int[] { -3, -3 }, 0L));
		testDataList.add(Pair.of(new int[] { 0, -3, 0 }, 3L));
		testDataList.add(Pair.of(new int[] { 0, -3, 0, -3 }, 3L));
		testDataList.add(Pair.of(new int[] { -3, 0, 0, -3 }, 0L));
		testDataList.add(Pair.of(new int[] { 0, -3, -3, -3, 0 }, 9L));

		testDataList.add(Pair.of(new int[] { -3, -5, -2 }, 2L));
		testDataList.add(Pair.of(new int[] { -2, -5, -3 }, 2L));

		testDataList.add(Pair.of(new int[] { -3, -4, -1 }, 1L));
		testDataList.add(Pair.of(new int[] { -1, -4, -3 }, 1L));

		return testDataList;
	}

}
