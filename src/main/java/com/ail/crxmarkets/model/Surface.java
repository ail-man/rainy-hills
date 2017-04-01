package com.ail.crxmarkets.model;

import java.io.Serializable;

import com.ail.crxmarkets.Utils;
import com.ail.crxmarkets.draw.SurfaceDrawer;
import com.ail.crxmarkets.waterfill.WaterFillMethod;

/**
 * Модель поверхности, которая может быть заполнена водой.
 * Модель является потокобезопасной.
 */
public class Surface implements Serializable {

	private static final long serialVersionUID = -8564161090848789227L;

	private final int[] surface;
	private int[] water;

	private boolean filled;
	private long hashedTotalWater;

	@SuppressWarnings({ "WeakerAccess" })
	public Surface(int[] surface) {
		this.surface = surface.clone();
		this.water = new int[surface.length];
		this.filled = false;
	}

	public static Surface random(int length, int minHeight, int maxHeight) {
		return new Surface(Utils.randomArray(length, minHeight, maxHeight));
	}

	public synchronized void fillWithWater(WaterFillMethod waterFillMethod, int[] waterToFill) {
		if (!filled) {
			water = waterFillMethod.calcWaterOnSurface(surface, waterToFill);
			hashedTotalWater = Utils.sum(water);
			filled = true;
		}
	}

	@SuppressWarnings({ "WeakerAccess" })
	public synchronized void wipeTheWater() {
		if (filled) {
			water = new int[surface.length];
			filled = false;
		}
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
		if (filled) {
			return hashedTotalWater;
		}
		return 0;
	}

	public int[] getSurface() {
		return surface.clone();
	}

	public int[] getWater() {
		return water.clone();
	}

}
