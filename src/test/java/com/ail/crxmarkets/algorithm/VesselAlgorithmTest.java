package com.ail.crxmarkets.algorithm;

import org.apache.commons.lang3.tuple.Pair;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;

public class VesselAlgorithmTest extends BaseTest {

	@Test
	public void testCalcWaterVolumeOnSurface() throws Exception {
		VesselAlgorithm vesselAlgorithm = new VesselAlgorithm();

		for (Pair testData : getTestData()) {
			assertThat(vesselAlgorithm.calcWaterVolumeOnSurface((int[]) testData.getLeft()), equalTo(testData.getRight()));
		}
	}

}
