package com.ail.crxmarkets.model.waterfill;

import com.ail.crxmarkets.draw.SurfaceDrawer;
import com.ail.crxmarkets.draw.VerticalConsoleSurfaceDrawer;
import com.ail.crxmarkets.model.waterfill.impl.WFMFullTowerOptimized;
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