package com.ail.crxmarkets.model.waterfill;

import com.ail.crxmarkets.draw.SurfaceDrawer;
import com.ail.crxmarkets.draw.VerticalConsoleSurfaceDrawer;
import com.ail.crxmarkets.model.waterfill.impl.WFMFullTower;
import org.junit.Test;

public class WFMFullTowerTest {

	@Test
	public void testCalcWaterOnSurface() throws Exception {
		WaterFillMethod waterFillMethod = new WFMFullTower();
		SurfaceDrawer surfaceDrawer = new VerticalConsoleSurfaceDrawer();

		WFMFullTestHelper testHelper = new WFMFullTestHelper();
		testHelper.testWaterFillMethod(waterFillMethod, surfaceDrawer);
	}

}