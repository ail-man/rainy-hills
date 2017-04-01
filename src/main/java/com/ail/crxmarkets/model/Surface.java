package com.ail.crxmarkets.model;

import java.io.Serializable;

import com.ail.crxmarkets.Utils;
import com.ail.crxmarkets.draw.SurfaceDrawer;
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

	@SuppressWarnings({ "WeakerAccess" })
	public Surface(int[] surface) {
		this.surface = surface.clone();
		this.water = new int[surface.length];

		this.totalWaterHashed = 0;
		this.totalWaterCalculated = true;
	}

	public static Surface random(int length, int minHeight, int maxHeight) {
		return new Surface(Utils.randomArray(length, minHeight, maxHeight));
	}

	@SuppressWarnings("UnusedDeclaration")
	public void fillWithWater(WaterFillMethod waterFillMethod, int[] waterToFill) {
		water = waterFillMethod.calcWaterOnSurface(surface, waterToFill);
		totalWaterCalculated = false;
	}

	@SuppressWarnings({ "WeakerAccess" })
	public void wipeTheWater() {
		water = new int[surface.length];
		totalWaterHashed = 0;
		totalWaterCalculated = true;
	}

	@SuppressWarnings({ "WeakerAccess" })
	public void drawSurface(SurfaceDrawer surfaceDrawer) {
		surfaceDrawer.drawSurface(surface);
	}

	@SuppressWarnings({ "WeakerAccess" })
	public void drawSurfaceWithWater(SurfaceDrawer surfaceDrawer) {
		surfaceDrawer.drawSurfaceWithWater(surface, water);
	}

	@SuppressWarnings({ "UnusedDeclaration" })
	public long getTotalWater() {
		if (!totalWaterCalculated) {
			totalWaterHashed = Utils.sum(water);
		}
		return totalWaterHashed;
	}

	public int[] getSurface() {
		return surface.clone();
	}

	public int[] getWater() {
		return water.clone();
	}

}
