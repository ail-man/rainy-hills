package com.ail.crxmarkets.algorithm;

/**
 * Found on http://javabypatel.blogspot.ru/2016/10/trapping-rain-water-between-towers.html
 */
public class TowerAlgorithm implements WaterCalculatorAlgorithm {

	@Override
	public int[] calcWaterOnSurface(int[] surface) {
		int[] maxSeenSoFarFromRight = new int[surface.length];

		//Populate maxSeenSoFarFromRight array.
		maxSeenSoFarFromRight[surface.length - 1] = surface[surface.length - 1];
		for (int i = surface.length - 2; i >= 0; i--) {
			maxSeenSoFarFromRight[i] = Math.max(maxSeenSoFarFromRight[i + 1], surface[i]);
		}

		int[] water = new int[surface.length];

		int maxSeenSoFarFromLeft = 0;
		for (int i = 0; i < surface.length; i++) {
			if (maxSeenSoFarFromLeft < surface[i]) {
				maxSeenSoFarFromLeft = surface[i];
			}
			int minFromLeftRight = Math.min(maxSeenSoFarFromLeft, maxSeenSoFarFromRight[i]);
			water[i] = (minFromLeftRight - surface[i]);
		}

		return water;
	}

}
