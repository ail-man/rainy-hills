package com.ail.crxmarkets.model;

import java.io.Serializable;

import com.ail.crxmarkets.Utils;
import com.ail.crxmarkets.model.waterfill.WaterFillMethod;

/**
 * A model of the surface that can be filled with water.
 * The model is thread safe.
 *
 * @author Arthur Lomsadze (ailman1985@gmail.com)
 * @see WaterFillMethod
 */
public class Surface implements Serializable {

	private static final long serialVersionUID = -8564161090848789227L;

	private final int[] surface;
	private int[] water;

	private Long totalWaterCashed;

	/**
	 * Constructs new {@link Surface} object from the array of surface height
	 * points. It clones the original array for the sake of safety
	 *
	 * @param surface the array of height points
	 */
	public Surface(int[] surface) {
		this.surface = surface.clone();
		this.water = new int[surface.length];

		this.totalWaterCashed = null;
	}

	/**
	 * Factory method for constructing random surface
	 *
	 * @param length    length of the surface
	 * @param minHeight minimum height of random surface
	 * @param maxHeight maximum height of random surface
	 * @return new {@link Surface} object
	 */
	public static Surface random(int length, int minHeight, int maxHeight) {
		return new Surface(Utils.randomArray(length, minHeight, maxHeight));
	}

	/**
	 * Fill water to surface
	 *
	 * @param waterFillMethod an object of {@link WaterFillMethod} algorithm for
	 *                        calculation
	 * @param waterToFill     an array of water to fill
	 */
	public synchronized void fillWater(WaterFillMethod waterFillMethod, int[] waterToFill) {
		water = waterFillMethod.calcWaterOnSurface(surface, water, waterToFill);

		this.totalWaterCashed = null;
	}

	/**
	 * Wipes all the water from surface
	 */
	@SuppressWarnings("WeakerAccess")
	public synchronized void wipeWater() {
		water = new int[surface.length];

		totalWaterCashed = 0L;
	}

	/**
	 * Calculates the total amount of water on surface
	 *
	 * @return total amount of water
	 */
	public synchronized long getTotalWater() {
		if (totalWaterCashed == null) {
			totalWaterCashed = Utils.sum(water);
		}
		return totalWaterCashed;
	}

	/**
	 * Gets an array of surface height points. It clones the original array for
	 * the sake of safety.
	 *
	 * @return array of surface height points
	 */
	public int[] getSurface() {
		return surface.clone();
	}

	/**
	 * Gets an array of water height points. It clones the original array for the
	 * sake of safety.
	 *
	 * @return array of water height points
	 */
	public synchronized int[] getWater() {
		return water.clone();
	}

}
