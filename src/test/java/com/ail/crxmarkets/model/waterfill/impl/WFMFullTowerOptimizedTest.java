package com.ail.crxmarkets.model.waterfill.impl;

import com.ail.crxmarkets.draw.SurfaceDrawer;
import com.ail.crxmarkets.draw.impl.HorizontalConsoleSurfaceDrawer;
import com.ail.crxmarkets.model.waterfill.WFMFullTestHelper;
import com.ail.crxmarkets.model.waterfill.WaterFillMethod;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests for {@link WFMFullTowerOptimized} water filling algorithm
 */
public class WFMFullTowerOptimizedTest {

	private WaterFillMethod waterFillMethod = new WFMFullTowerOptimized();
	private WFMFullTestHelper testHelper = new WFMFullTestHelper();

	@Test
	public void calcWaterOnSurface() throws Exception {
		SurfaceDrawer surfaceDrawer = new HorizontalConsoleSurfaceDrawer();
		testHelper.testWaterFillMethod(waterFillMethod, surfaceDrawer);
	}

	@Test
	@Ignore
	public void testEfficiency() throws Exception {
		testHelper.testWaterFillMethodEfficiency(waterFillMethod, "Optimized Tower Method");
	}

}