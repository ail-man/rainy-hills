package com.ail.crxmarkets.algorithm;

import org.apache.commons.lang3.tuple.Pair;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Ignore;
import org.junit.Test;

public class VesselAlgorithmTest extends BaseTest {

	@Test
	@Ignore
	public void testCalcWaterVolumeOnSurface() throws Exception {
		VesselAlgorithm vesselAlgorithm = new VesselAlgorithm();

		for (Pair<int[], Long> testData : getTestData()) {
			int[] surface = testData.getLeft();
			long waterTotal = testData.getRight();
			assertThat(vesselAlgorithm.calcWaterVolumeOnSurface(surface), equalTo(waterTotal));
		}
	}

}
