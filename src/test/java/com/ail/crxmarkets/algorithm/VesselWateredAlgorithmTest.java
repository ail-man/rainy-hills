package com.ail.crxmarkets.algorithm;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

public class VesselWateredAlgorithmTest extends BaseTest {

	@Test
	public void calcWaterOnSurface() throws Exception {
		VesselWateredAlgorithm vesselWateredAlgorithm = new VesselWateredAlgorithm();

		for (Pair testData : getTestData()) {
			vesselWateredAlgorithm.calcWaterOnSurface((int[]) testData.getLeft());
		}
	}

}