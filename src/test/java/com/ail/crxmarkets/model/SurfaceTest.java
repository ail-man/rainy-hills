package com.ail.crxmarkets.model;

import com.ail.crxmarkets.draw.HorizontalConsoleSurfaceDrawer;
import com.ail.crxmarkets.draw.SurfaceDrawer;
import com.ail.crxmarkets.model.waterfill.WaterFillMethod;
import com.ail.crxmarkets.model.waterfill.impl.WFMFullVessel;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;

// TODO в SurfaceDrawer передавать объект Surface (вынести методы drawSurface в SurfaceDrawer)
// TODO для логирования использовать Spring (может не надо)
public class SurfaceTest {

	private static final int LENGTH = 50;
	private static final int MIN_HEIGHT = 0;
	private static final int MAX_HEIGHT = 10;

	private Surface surface;
	private SurfaceDrawer surfaceDrawer = new HorizontalConsoleSurfaceDrawer();
	private WaterFillMethod waterFillMethod = new WFMFullVessel();

	@Before
	public void setUp() throws Exception {
		System.out.println(">>> CREATE SURFACE <<<");
		surface = Surface.random(LENGTH, MIN_HEIGHT, MAX_HEIGHT);
		assertThatWaterIsAbsent();

		surfaceDrawer.drawSurface(surface);
	}

	@Test
	public void testFillWithWater() throws Exception {
		System.out.println(">>> FILL WITH WATER <<<");
		surface.fillWater(waterFillMethod, new int[] {});

		surfaceDrawer.drawSurfaceWithWater(surface);
	}

	@Test
	public void testWipeTheWater() throws Exception {
		testFillWithWater();

		System.out.println(">>> WIPE ALL THE WATER <<<");
		surface.wipeWater();
		surfaceDrawer.drawSurfaceWithWater(surface);

		assertThatWaterIsAbsent();
		assertThat(surface.getTotalWater(), is(0L));
	}

	private void assertThatWaterIsAbsent() {
		for (int w : surface.getWater()) {
			assertThat(w, is(0));
		}
	}

}