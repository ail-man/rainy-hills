package com.ail.crxmarkets.model;

import java.io.Serializable;

import com.ail.crxmarkets.algorithm.WaterCalculatorAlgorithm;
import com.ail.crxmarkets.draw.SurfaceDrawer;
import org.apache.commons.lang3.RandomUtils;

/**
 * Модель поверхности, которая может быть заполнена водой
 */
public class Surface implements Serializable {

	private static final long serialVersionUID = -8564161090848789227L;

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

	public long totalWater() {
		long result = 0;
		for (int w : water) {
			result += w;
		}
		return result;
	}

}
