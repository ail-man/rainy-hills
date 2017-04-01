package com.ail.crxmarkets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

public class UtilsTest {

	@Test
	public void max() throws Exception {
		assertThat(Utils.max(new int[] { 51, 467, 471, 715, 57858, 528, 2587, 6416 }), is(57858));
		assertThat(Utils.max(new int[] { 6116, 646, 4, 471, 751, 58, 28, 26, 8, 8 }), is(6116));
		assertThat(Utils.max(new int[] { 61, 71, 578, 628, 689, 97, 25, 6, 6, 852, 852 }), is(852));
	}

	@Test
	public void sum() throws Exception {
		assertThat(Utils.sum(new int[] {}), equalTo(0L));
		assertThat(Utils.sum(new int[] { 3 }), equalTo(3L));
		assertThat(Utils.sum(new int[] { 3, 6, 8 }), equalTo(17L));
	}

	@Test
	public void average() throws Exception {
		assertThat(Utils.average(new int[] {}), equalTo(0));
		assertThat(Utils.average(new int[] { 7 }), equalTo(7));
		assertThat(Utils.average(new int[] { 11, 31, 8, 65, 7 }), equalTo(24));
	}

	@Test
	public void parseIntArray() throws Exception {
		assertThat(Utils.parseIntArray("1, 6,17, 7,8, 56, 15, 8728,2"), equalTo(new int[] { 1, 6, 17, 7, 8, 56, 15, 8728, 2 }));
		assertThat(Utils.parseIntArray("16,614,7,71,8,85,2,9,69,93,17"), equalTo(new int[] { 16, 614, 7, 71, 8, 85, 2, 9, 69, 93, 17 }));
		assertThat(Utils.parseIntArray("16, 6 1   41  61464  , 14 416,6 416 14,6 "), equalTo(new int[] { 16, 614161464, 14416, 641614, 6 }));
	}

	@Test
	public void printAsText() throws Exception {
		assertThat(Utils.printAsText(new int[] { 1, 6, 17, 7, 8, 56, 15, 8728, 2 }), is("1,6,17,7,8,56,15,8728,2"));
		assertThat(Utils.printAsText(new int[] { 16, 614, 7, 71, 8, 85, 2, 9, 69, 93, 17 }), is("16,614,7,71,8,85,2,9,69,93,17"));
		assertThat(Utils.printAsText(new int[] { 16, 614161464, 14416, 641614, 6 }), is("16,614161464,14416,641614,6"));
	}

}