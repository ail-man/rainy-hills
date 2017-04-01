package com.ail.crxmarkets.model;

import java.io.Serializable;

import com.ail.crxmarkets.Utils;
import com.ail.crxmarkets.model.waterfill.WaterFillMethod;

/**
 * Модель поверхности, которая может быть заполнена водой.
 * Модель является потокобезопасной.
 */
public class Surface implements Serializable {

	private static final long serialVersionUID = -8564161090848789227L;

	private final int[] surface;
	private int[] water;

	private boolean totalWaterCalculated;
	private long totalWaterHashed;

	public Surface(int[] surface) {
		this.surface = surface.clone();
		this.water = new int[surface.length];

		this.totalWaterHashed = 0;
		this.totalWaterCalculated = true;
	}

	public static Surface random(int length, int minHeight, int maxHeight) {
		return new Surface(Utils.randomArray(length, minHeight, maxHeight));
	}

	public synchronized void fillWater(WaterFillMethod waterFillMethod, int[] waterToFill) {
		water = waterFillMethod.calcWaterOnSurface(surface, water, waterToFill);

		totalWaterCalculated = false;
	}

	@SuppressWarnings("WeakerAccess")
	public synchronized void wipeWater() {
		water = new int[surface.length];

		totalWaterHashed = 0;
		totalWaterCalculated = true;
	}

	public synchronized long getTotalWater() {
		if (!totalWaterCalculated) {
			totalWaterHashed = Utils.sum(water);
			totalWaterCalculated = true;
		}
		return totalWaterHashed;
	}

	public int[] getSurface() {
		return surface.clone();
	}

	public synchronized int[] getWater() {
		return water.clone();
	}

}
