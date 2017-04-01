package com.ail.crxmarkets.model.waterfill.impl;

import com.ail.crxmarkets.draw.SurfaceDrawer;
import com.ail.crxmarkets.draw.VerticalConsoleSurfaceDrawer;
import com.ail.crxmarkets.model.waterfill.WFMFullTestHelper;
import com.ail.crxmarkets.model.waterfill.WaterFillMethod;
import org.junit.Ignore;
import org.junit.Test;

public class WFMFullTowerOptimizedTest {

	private WaterFillMethod waterFillMethod = new WFMFullTowerOptimized();
	private WFMFullTestHelper testHelper = new WFMFullTestHelper();

	@Test
	public void testCalcWaterOnSurface() throws Exception {
		SurfaceDrawer surfaceDrawer = new VerticalConsoleSurfaceDrawer();
		testHelper.testWaterFillMethod(waterFillMethod, surfaceDrawer);
	}

	@Test
	@Ignore
	public void testEfficiency() throws Exception {
		testHelper.testWaterFillMethodEfficiency(waterFillMethod, "Tower Method");
	}
}