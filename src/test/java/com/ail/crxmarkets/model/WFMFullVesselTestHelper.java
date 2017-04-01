package com.ail.crxmarkets.model;

import com.ail.crxmarkets.Utils;
import com.ail.crxmarkets.draw.HorizontalConsoleSurfaceDrawer;
import com.ail.crxmarkets.draw.SurfaceDrawer;
import com.ail.crxmarkets.model.waterfill.WaterFillMethod;
import com.ail.crxmarkets.model.waterfill.impl.WFMFullVessel;
import org.apache.commons.lang3.tuple.Pair;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;

public class WFMFullVesselTestHelper {

	private WaterFillMethod waterFillMethod = new WFMFullVessel();
	private SurfaceDrawer surfaceDrawer = new HorizontalConsoleSurfaceDrawer();

	@Test
	public void testCalcWaterOnSurface() throws Exception {
		for (Pair<int[], Long> testData : WFMFullTestHelper.getTestData()) {
			int[] surface = testData.getLeft();
			surfaceDrawer.drawSurface(surface);

			int[] water = waterFillMethod.calcWaterOnSurface(surface, null);
			assertThat(water.length, equalTo(surface.length));
			surfaceDrawer.drawSurfaceWithWater(surface, water);

			long waterTotal = testData.getRight();
			assertThat(Utils.sum(water), equalTo(waterTotal));
		}
	}

}