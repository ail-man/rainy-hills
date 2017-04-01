package com.ail.crxmarkets.model.waterfill.impl;

import com.ail.crxmarkets.model.waterfill.WaterFillMethod;

/**
 * Found on http://javabypatel.blogspot.ru/2016/10/trapping-rain-water-between-towers.html
 */
public class WFMFullTower implements WaterFillMethod {

	@Override
	public int[] calcWaterOnSurface(int[] surface, int[] water, int[] waterToFill) {
		int[] maxSeenSoFarFromRight = new int[surface.length];

		//Populate maxSeenSoFarFromRight array.
		maxSeenSoFarFromRight[surface.length - 1] = surface[surface.length - 1];
		for (int i = surface.length - 2; i >= 0; i--) {
			maxSeenSoFarFromRight[i] = Math.max(maxSeenSoFarFromRight[i + 1], surface[i]);
		}

		int[] wat = new int[surface.length];

		int maxSeenSoFarFromLeft = 0;
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
