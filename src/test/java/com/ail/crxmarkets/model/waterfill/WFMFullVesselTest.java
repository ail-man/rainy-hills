package com.ail.crxmarkets.model.waterfill;

import com.ail.crxmarkets.draw.HorizontalConsoleSurfaceDrawer;
import com.ail.crxmarkets.draw.SurfaceDrawer;
import com.ail.crxmarkets.model.waterfill.impl.WFMFullVessel;
import org.junit.Test;

public class WFMFullVesselTest {

	@Test
	public void testCalcWaterOnSurface() throws Exception {
		WaterFillMethod waterFillMethod = new WFMFullVessel();
		SurfaceDrawer surfaceDrawer = new HorizontalConsoleSurfaceDrawer();

		WFMFullTestHelper testHelper = new WFMFullTestHelper();
		testHelper.testWaterFillMethod(waterFillMethod, surfaceDrawer);
	}

}