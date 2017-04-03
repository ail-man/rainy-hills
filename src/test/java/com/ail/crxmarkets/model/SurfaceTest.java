package com.ail.crxmarkets.model;

import com.ail.crxmarkets.draw.SurfaceDrawer;
import com.ail.crxmarkets.draw.impl.HorizontalConsoleSurfaceDrawer;
import com.ail.crxmarkets.model.waterfill.WaterFillMethod;
import com.ail.crxmarkets.model.waterfill.impl.WFMFullVessel;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test class for {@link Surface} testing
 */
public class SurfaceTest {

	private static final Logger log = LoggerFactory.getLogger(SurfaceTest.class);

	private static final int LENGTH = 50;
	private static final int MIN_HEIGHT = 0;
	private static final int MAX_HEIGHT = 10;

	private Surface surface;
	private SurfaceDrawer surfaceDrawer = new HorizontalConsoleSurfaceDrawer();
	private WaterFillMethod waterFillMethod = new WFMFullVessel();

	@Before
	public void setUp() throws Exception {
		log.info(">>> CREATE SURFACE <<<");
		surface = Surface.random(LENGTH, MIN_HEIGHT, MAX_HEIGHT);
		assertThatWaterIsAbsent();

		surfaceDrawer.draw(surface);
	}

	@Test
	public void testFillWithWater() throws Exception {
		log.info(">>> FILL WITH WATER <<<");
		surface.fillWater(waterFillMethod, new int[] {});

		surfaceDrawer.drawWithWater(surface);
	}

	@Test
	public void testWipeTheWater() throws Exception {
		testFillWithWater();

		log.info(">>> WIPE ALL THE WATER <<<");
		surface.wipeWater();
		surfaceDrawer.drawWithWater(surface);

		assertThatWaterIsAbsent();
		assertThat(surface.getTotalWater(), is(0L));
	}

	private void assertThatWaterIsAbsent() {
		for (int w : surface.getWater()) {
			assertThat(w, is(0));
		}
	}

}