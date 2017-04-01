package com.ail.crxmarkets.logic;

import org.apache.commons.lang3.RandomUtils;

public class Surface {
	private final int[] surface;
	private int[] water;

	public Surface(int[] surface) {
		this.surface = surface.clone();
		this.water = new int[surface.length];
	}

	public static Surface random(int length, int minHeight, int maxHeight) {
		int[] surface = new int[length];
		for (int i = 0; i < length; i++) {
			surface[i] = RandomUtils.nextInt(minHeight, maxHeight);
		}
		return new Surface(surface);
	}

	public void fillWithWater(WaterCalculatorAlgorithm algorithm) {
		water = algorithm.calcWaterOnSurface(surface);
	}

	public void wipeTheWater() {
		water = new int[surface.length];
	}

	public void drawSurface(SurfaceDrawer surfaceDrawer) {
		surfaceDrawer.drawSurface(surface);
	}

	public void drawSurfaceWithWater(SurfaceDrawer surfaceDrawer) {
		surfaceDrawer.drawSurfaceWithWater(surface, water);
	}

	public int[] getSurface() {
		return surface.clone();
	}

	public int[] getWater() {
		return water.clone();
	}

}
