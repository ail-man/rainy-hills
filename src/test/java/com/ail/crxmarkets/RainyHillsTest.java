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
		assertThat(rainyHills.calcVolume(new int[] { 7 }), equalTo(0));
		assertThat(rainyHills.calcVolume(new int[] { 0, 0 }), equalTo(0));

		assertThat(rainyHills.calcVolume(new int[] { 1, 1 }), equalTo(0));
		assertThat(rainyHills.calcVolume(new int[] { 1, 0, 1 }), equalTo(1));
		assertThat(rainyHills.calcVolume(new int[] { 1, 1, 0, 1 }), equalTo(1));
		assertThat(rainyHills.calcVolume(new int[] { 1, 0, 0, 1 }), equalTo(2));
		assertThat(rainyHills.calcVolume(new int[] { 1, 0, 0, 0, 1 }), equalTo(3));

		assertThat(rainyHills.calcVolume(new int[] { 3, 3 }), equalTo(0));
		assertThat(rainyHills.calcVolume(new int[] { 3, 0, 3 }), equalTo(3));
		assertThat(rainyHills.calcVolume(new int[] { 3, 3, 0, 3 }), equalTo(3));
		assertThat(rainyHills.calcVolume(new int[] { 3, 0, 0, 3 }), equalTo(6));
		assertThat(rainyHills.calcVolume(new int[] { 3, 0, 0, 0, 3 }), equalTo(9));

		assertThat(rainyHills.calcVolume(new int[] { 3, 0, 2 }), equalTo(2));
		assertThat(rainyHills.calcVolume(new int[] { 2, 0, 3 }), equalTo(2));

		assertThat(rainyHills.calcVolume(new int[] { 3, 0, 1 }), equalTo(1));
		assertThat(rainyHills.calcVolume(new int[] { 1, 0, 3 }), equalTo(1));

		assertThat(rainyHills.calcVolume(new int[] { 3, 3, 0, 3 }), equalTo(3));
		assertThat(rainyHills.calcVolume(new int[] { 3, 0, 0, 3 }), equalTo(6));
		assertThat(rainyHills.calcVolume(new int[] { 3, 0, 0, 0, 3 }), equalTo(9));

		assertThat(rainyHills.calcVolume(new int[] { 3, 3, 3, 3 }), equalTo(0));

		assertThat(rainyHills.calcVolume(new int[] { 3, 1, 2, 0, 3 }), equalTo(6));

		assertThat(rainyHills.calcVolume(new int[] { 5, 3, 0, 2, 4 }), equalTo(7));
		assertThat(rainyHills.calcVolume(new int[] { 4, 4, 2, 0, 5 }), equalTo(6));

		//		assertThat(rainyHills.calcVolume(new int[] { 3, 2, 4, 1, 2 }), equalTo(2));
	}

}
