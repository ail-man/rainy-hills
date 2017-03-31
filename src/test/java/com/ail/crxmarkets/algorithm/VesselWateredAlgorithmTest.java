package com.ail.crxmarkets.algorithm;

import org.apache.commons.lang3.tuple.Pair;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;

public class VesselWateredAlgorithmTest extends BaseTest {

	@Test
	public void calcWaterOnSurface() throws Exception {
		VesselWateredAlgorithm vesselWateredAlgorithm = new VesselWateredAlgorithm();

		for (Pair<int[], Long> testData : getTestData()) {
			int[] surface = testData.getLeft();
			long waterTotal = testData.getRight();
			assertThat(sumWater(vesselWateredAlgorithm.calcWaterOnSurface(surface)), equalTo(waterTotal));
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