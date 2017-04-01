package com.ail.crxmarkets.model.waterfill;

import com.ail.crxmarkets.Utils;
import com.ail.crxmarkets.draw.HorizontalConsoleSurfaceDrawer;
import com.ail.crxmarkets.draw.SurfaceDrawer;
import com.ail.crxmarkets.model.Surface;
import com.ail.crxmarkets.model.waterfill.impl.WFMFullTower;
import org.apache.commons.lang3.tuple.Pair;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;

public class WFMFullTowerTestHelper {

	private WaterFillMethod waterFillMethod = new WFMFullTower();
	private SurfaceDrawer surfaceDrawer = new HorizontalConsoleSurfaceDrawer();

	@Test
	public void testCalcWaterOnSurface() throws Exception {
		for (Pair<int[], Long> testData : WFMFullTestHelper.getTestData()) {
			Surface surface = new Surface(testData.getLeft());
			surfaceDrawer.drawSurface(surface);

			surface.fillWater(waterFillMethod, null);

			assertThat(surface.getWater().length, equalTo(surface.getSurface().length));
			surfaceDrawer.drawSurfaceWithWater(surface);

			long waterTotal = testData.getRight();
			assertThat(Utils.sum(surface.getWater()), equalTo(waterTotal));
		}
	}

}