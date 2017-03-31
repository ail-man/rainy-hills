package com.ail.crxmarkets.logic;

import org.apache.commons.lang3.RandomUtils;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Before;
import org.junit.Test;

public class SurfaceTest {

	private Surface surface;

	@Before
	public void setUp() throws Exception {
		int length = RandomUtils.nextInt(0, 100);
		int minHeight, maxHeight;
		do {
			minHeight = RandomUtils.nextInt(0, 100);
			maxHeight = RandomUtils.nextInt(0, 100);
		} while (minHeight > maxHeight);

		surface = Surface.random(length, minHeight, maxHeight);
		assertThat(surface.getSurface(), not(nullValue()));
	}

	@Test
	public void testFillWithWater() throws Exception {
		surface.fillWithWater(new VesselAlgorithm());
	}

	@Test
	public void testWipeTheWater() throws Exception {

	}

	@Test
	public void testImmutable() throws Exception {

	}

}