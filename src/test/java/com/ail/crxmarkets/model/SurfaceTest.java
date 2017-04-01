package com.ail.crxmarkets.model;

import com.ail.crxmarkets.draw.HorizontalConsoleSurfaceDrawer;
import com.ail.crxmarkets.draw.SurfaceDrawer;
import com.ail.crxmarkets.model.waterfill.WaterFillMethod;
import com.ail.crxmarkets.model.waterfill.impl.WFMFullVessel;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class SurfaceTest {

	private static final int TEST_COUNT = 5;
	private static final int LENGTH = 50;
	private static final int MIN_HEIGHT = 0;
	private static final int MAX_HEIGHT = 10;

	private Surface surface;
	private SurfaceDrawer surfaceDrawer = new HorizontalConsoleSurfaceDrawer();
	private WaterFillMethod fillingAlgorythm = new WFMFullVessel();

	@Before
	public void setUp() throws Exception {
		surface = Surface.random(LENGTH, MIN_HEIGHT, MAX_HEIGHT);
		assertThat(surface.getSurface(), not(nullValue()));
		assertThat(surface.getWater(), not(nullValue()));
	}

	@Test
	public void testFillWithWater() throws Exception {
		for (int i = 0; i < TEST_COUNT; i++) {
			surface.fillWithWater(fillingAlgorythm, new int[] {});
			surface.drawSurface(surfaceDrawer);
			surface.drawSurfaceWithWater(surfaceDrawer);
		}
	}

	@Test
	public void testWipeTheWater() throws Exception {
		surface.wipeTheWater();
		surface.drawSurfaceWithWater(surfaceDrawer);
	}

	@Test
	public void testImmutable() throws Exception {
		surface.fillWithWater(fillingAlgorythm, new int[] {});
	}

}