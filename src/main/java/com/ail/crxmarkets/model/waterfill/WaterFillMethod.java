package com.ail.crxmarkets.model.waterfill;

/**
 * Defines an interface of algorithms for calculation of water above the
 * surface
 *
 * @author Arthur Lomsadze (ailman1985@gmail.com)
 */
public interface WaterFillMethod {

	/**
	 * Calculates the amount of water above the each point of the surface after
	 * pouring in water and returns as in the form of another array
	 *
	 * @param surface     surface array
	 * @param water       an array of water available on the surface
	 * @param waterToFill an array of the amount of water that falls on the
	 *                    surface
	 * @return array of water amount above the each point of the surface
	 */
	int[] calcWaterOnSurface(int[] surface, int[] water, int[] waterToFill);

}
