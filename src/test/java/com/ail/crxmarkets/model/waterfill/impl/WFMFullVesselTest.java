package com.ail.crxmarkets.model.waterfill.impl;

import com.ail.crxmarkets.draw.HorizontalConsoleSurfaceDrawer;
import com.ail.crxmarkets.draw.SurfaceDrawer;
import com.ail.crxmarkets.model.waterfill.WFMFullTestHelper;
import com.ail.crxmarkets.model.waterfill.WaterFillMethod;
import org.junit.Ignore;
import org.junit.Test;

public class WFMFullVesselTest {

	private WFMFullTestHelper testHelper = new WFMFullTestHelper();
	private WaterFillMethod waterFillMethod = new WFMFullVessel();

	@Test
	public void testCalcWaterOnSurface() throws Exception {
		SurfaceDrawer surfaceDrawer = new HorizontalConsoleSurfaceDrawer();
		testHelper.testWaterFillMethod(waterFillMethod, surfaceDrawer);
	}

	@Test
	@Ignore
	public void testEfficiency() throws Exception {
		testHelper.testWaterFillMethodEfficiency(waterFillMethod, "Vessel Method");
	}
}