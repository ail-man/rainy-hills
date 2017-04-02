package com.ail.crxmarkets.model.waterfill.impl;

import com.ail.crxmarkets.draw.SurfaceDrawer;
import com.ail.crxmarkets.draw.impl.VerticalConsoleSurfaceDrawer;
import com.ail.crxmarkets.model.waterfill.WFMFullTestHelper;
import com.ail.crxmarkets.model.waterfill.WaterFillMethod;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests for {@link WFMFullTowerTest} water filling algorithm
 */
public class WFMFullTowerTest {

	private WFMFullTestHelper testHelper = new WFMFullTestHelper();
	private WaterFillMethod waterFillMethod = new WFMFullTower();

	@Test
	public void calcWaterOnSurface() throws Exception {
		SurfaceDrawer surfaceDrawer = new VerticalConsoleSurfaceDrawer();
		testHelper.testWaterFillMethod(waterFillMethod, surfaceDrawer);
	}

	@Test
	@Ignore
	public void testEfficiency() throws Exception {
		testHelper.testWaterFillMethodEfficiency(waterFillMethod, "Tower Method");
	}

}