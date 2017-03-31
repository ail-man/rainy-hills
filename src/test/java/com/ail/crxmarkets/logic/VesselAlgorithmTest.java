package com.ail.crxmarkets.logic;

import org.apache.commons.lang3.tuple.Pair;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;

public class VesselAlgorithmTest extends BaseTest {

	private VesselAlgorithm vesselAlgorithm = new VesselAlgorithm();
	private SurfaceDrawer surfaceDrawer = new HorizontalConsoleSurfaceDrawer();

	@Test
	public void testCalcWaterOnSurface() throws Exception {
		for (Pair<int[], Long> testData : getTestData()) {
			int[] surface = testData.getLeft();
			surfaceDrawer.drawSurface(surface);

			int[] water = vesselAlgorithm.calcWaterOnSurface(surface);
			assertThat(water.length, equalTo(surface.length));
			surfaceDrawer.drawSurfaceWithWater(surface, water);

			long waterTotal = testData.getRight();
			assertThat(sumWater(water), equalTo(waterTotal));
		}
	}

	private long sumWater(int[] water) {
		long result = 0;
		for (int w : water) {
			result += w;
		}
		return result;
	}

}