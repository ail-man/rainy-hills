package com.ail.crxmarkets.model.waterfill;

import com.ail.crxmarkets.draw.SurfaceDrawer;
import com.ail.crxmarkets.draw.VerticalConsoleSurfaceDrawer;
import com.ail.crxmarkets.model.waterfill.impl.WFMFullTower;
import org.junit.Test;

public class WFMFullTowerTest {

	private WaterFillMethod waterFillMethod = new WFMFullTower();
	private WFMFullTestHelper testHelper = new WFMFullTestHelper();

	@Test
	public void testCalcWaterOnSurface() throws Exception {
		SurfaceDrawer surfaceDrawer = new VerticalConsoleSurfaceDrawer();
		testHelper.testWaterFillMethod(waterFillMethod, surfaceDrawer);
	}

	@Test
	public void testEfficiency() throws Exception {
		testHelper.testWaterFillMethodEfficiency(waterFillMethod, "Tower Method");
	}
}