package com.ail.crxmarkets.model;

import com.ail.crxmarkets.algorithm.TowerAlgorithm;
import com.ail.crxmarkets.draw.HorizontalConsoleSurfaceDrawer;
import com.ail.crxmarkets.draw.SurfaceDrawer;
import org.apache.commons.lang3.tuple.Pair;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;

public class TowerAlgorithmTest extends WaterCalculatorAlgorithmTest {

	private TowerAlgorithm vesselAlgorithm = new TowerAlgorithm();
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
			assertThat(totalWater(water), equalTo(waterTotal));
		}

	}

}