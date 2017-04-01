package com.ail.crxmarkets.model;

import com.ail.crxmarkets.draw.HorizontalConsoleSurfaceDrawer;
import com.ail.crxmarkets.draw.SurfaceDrawer;
import com.ail.crxmarkets.model.waterfill.WaterFillMethod;
import com.ail.crxmarkets.model.waterfill.impl.WFMFullVessel;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class SurfaceTest {

	private static final int LENGTH = 50;
	private static final int MIN_HEIGHT = 0;
	private static final int MAX_HEIGHT = 10;

	private Surface surface;
	private SurfaceDrawer surfaceDrawer = new HorizontalConsoleSurfaceDrawer();
	private WaterFillMethod waterFillMethod = new WFMFullVessel();

	@Before
	public void setUp() throws Exception {
		surface = Surface.random(LENGTH, MIN_HEIGHT, MAX_HEIGHT);
		assertThatWaterIsAbsent();

		surface.drawSurface(surfaceDrawer);
	}

	@Test
	public void testFillWithWater() throws Exception {
		surface.fillWithWater(waterFillMethod, new int[] {});

		surface.drawSurfaceWithWater(surfaceDrawer);
	}

	@Test
	public void testWipeTheWater() throws Exception {
		surface.wipeTheWater();
		assertThatWaterIsAbsent();
		assertThat(surface.getTotalWater(), is(0L));

		surface.drawSurfaceWithWater(surfaceDrawer);
	}

	private void assertThatWaterIsAbsent() {
		for (int w : surface.getWater()) {
			assertThat(w, is(0L));
		}
	}

}