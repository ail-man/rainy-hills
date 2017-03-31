package com.ail.crxmarkets.algorithm;

import org.apache.commons.lang3.tuple.Pair;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;

public class NewVesselAlgorithmTest extends BaseTest {

	@Test
	public void testCalcWaterOnSurface() throws Exception {
		NewVesselAlgorithm newVesselAlgorithm = new NewVesselAlgorithm();

		for (Pair<int[], Long> testData : getTestData()) {
			int[] surface = testData.getLeft();
			printVerticalSurface(surface);

			int[] water = newVesselAlgorithm.calcWaterOnSurface(surface);
			assertThat(water.length, equalTo(surface.length));
			printVerticalSurfaceWithWater(surface, water);

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