package com.ail.crxmarkets.model.waterfill.impl;

import com.ail.crxmarkets.draw.SurfaceDrawer;
import com.ail.crxmarkets.draw.impl.HorizontalConsoleSurfaceDrawer;
import com.ail.crxmarkets.model.waterfill.WFMFullTestHelper;
import com.ail.crxmarkets.model.waterfill.WaterFillMethod;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Tests for {@link WFMFullVessel} water filling algorithm
 */
public class WFMFullVesselTest {

	private WFMFullTestHelper testHelper = new WFMFullTestHelper();
	private WaterFillMethod waterFillMethod = new WFMFullVessel();

	@Test
	public void calcWaterOnSurface() throws Exception {
		SurfaceDrawer surfaceDrawer = new HorizontalConsoleSurfaceDrawer();
		testHelper.testWaterFillMethod(waterFillMethod, surfaceDrawer);
	}

	@Test
	@Ignore
	public void testEfficiency() throws Exception {
		testHelper.testWaterFillMethodEfficiency(waterFillMethod, "Vessel Method");
	}

	@Test
	public void test() throws Exception {
		int N = 1000;
		double summ = N;

		for (int sub = 0; sub < N; sub += 2) {
			summ = summ + (N - sub);
		}

		double result = summ / N;

		System.out.println(result);
	}
}