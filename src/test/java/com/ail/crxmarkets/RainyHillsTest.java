package com.ail.crxmarkets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;

public class RainyHillsTest {

	@Test
	public void testPrintHills() throws Exception {
		RainyHills rainyHills = new RainyHills();
		rainyHills.printSurface(new int[] { 3, 2, 4, 1, 2 });
	}

	@Test
	public void testCalculateVolume() throws Exception {
		RainyHills rainyHills = new RainyHills();

		assertThat(rainyHills.calcVolume(new int[] { 0 }), equalTo(0));

		assertThat(rainyHills.calcVolume(new int[] { 3, 2, 4, 1, 2 }), equalTo(2));
	}

}
