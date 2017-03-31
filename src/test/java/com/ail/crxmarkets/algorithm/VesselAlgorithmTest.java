package com.ail.crxmarkets.algorithm;

import com.ail.crxmarkets.histogram.Histogram;
import com.ail.crxmarkets.histogram.HorizontalConsoleHistogram;
import org.apache.commons.lang3.tuple.Pair;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;

public class VesselAlgorithmTest extends BaseTest {

	private Histogram histogram = new HorizontalConsoleHistogram();

	@Test
	public void testCalcWaterOnSurface() throws Exception {
		VesselAlgorithm vesselAlgorithm = new VesselAlgorithm();

		for (Pair<int[], Long> testData : getTestData()) {
			int[] surface = testData.getLeft();
			histogram.drawHistogram(surface);

			int[] water = vesselAlgorithm.calcWaterOnSurface(surface);
			assertThat(water.length, equalTo(surface.length));
			histogram.drawDoubleLayerHistogram(surface, water);

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