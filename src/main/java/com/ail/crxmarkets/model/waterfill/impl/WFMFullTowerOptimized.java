package com.ail.crxmarkets.model.waterfill.impl;

import com.ail.crxmarkets.Utils;
import com.ail.crxmarkets.model.waterfill.WaterFillMethod;

/**
 * See
 * <a href="http://javabypatel.blogspot.ru/2016/10/trapping-rain-water-between-towers.html">
 * http://javabypatel.blogspot.ru/2016/10/trapping-rain-water-between-towers.html
 * </a>
 */
public class WFMFullTowerOptimized implements WaterFillMethod {

	/**
	 * Calculates the maximum amount of water that can fit above the each
	 * point of the surface and returns in the form of an array
	 *
	 * @param surface     surface array
	 * @param water       not used
	 * @param waterToFill not used
	 * @return array of water amount above the each point of the surface
	 */
	@Override
	public int[] calcWaterOnSurface(int[] surface, int[] water, int[] waterToFill) {
		int[] maxSeenSoFarFromRight = new int[surface.length];

		//Populate maxSeenSoFarFromRight array.
		maxSeenSoFarFromRight[surface.length - 1] = surface[surface.length - 1];
		for (int i = surface.length - 2; i >= 0; i--) {
			maxSeenSoFarFromRight[i] = Math.max(maxSeenSoFarFromRight[i + 1], surface[i]);
		}

		int[] wat = new int[surface.length];

		int maxSeenSoFarFromLeft = Utils.min(surface);
		for (int i = 0; i < surface.length; i++) {
			if (maxSeenSoFarFromLeft < surface[i]) {
				maxSeenSoFarFromLeft = surface[i];
			}
			int minFromLeftRight = Math.min(maxSeenSoFarFromLeft, maxSeenSoFarFromRight[i]);
			wat[i] = (minFromLeftRight - surface[i]);
		}

		return wat;
	}

}
