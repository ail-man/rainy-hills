package com.ail.crxmarkets.model.waterfill.impl;

import com.ail.crxmarkets.model.waterfill.WaterFillMethod;

/**
 * See
 * <a href="https://anonymouscoders.wordpress.com/2015/06/12/amount-of-rain-water-above-tower/">
 * https://anonymouscoders.wordpress.com/2015/06/12/amount-of-rain-water-above-tower/
 * </a>
 */
public class WFMFullTower implements WaterFillMethod {

	/**
	 * Рассчитывает максимальное количество воды, которое может поместиться
	 * над каждым элементом поверхности, и возвращает в виде массива
	 *
	 * @param surface     массив поверхности
	 * @param water       не используется
	 * @param waterToFill не используется
	 * @return массив количества воды над каждым элементом поверхности
	 */
	@Override
	public int[] calcWaterOnSurface(int[] surface, int[] water, int[] waterToFill) {
		int n = surface.length;
		int leftMax[] = new int[n];
		int rightMax[] = new int[n];

		//to calculate height highest tower to left of each tower
		int tempMax = surface[0];
		for (int i = 0; i < n; i++) {
			if (surface[i] > tempMax) {
				tempMax = surface[i];
				leftMax[i] = tempMax;
			} else {
				leftMax[i] = tempMax;
			}
		}

		//to calculate height highest tower to right of each tower
		tempMax = surface[n - 1];
		for (int j = n - 1; j >= 0; j--) {
			if (surface[j] > tempMax) {
				tempMax = surface[j];
				rightMax[j] = tempMax;
			} else {
				rightMax[j] = tempMax;
			}
		}

		// amount of water above each tower =
		//Minimum height between highest left and right tower - height of tower
		int result[] = new int[surface.length];
		for (int k = 0; k < n; k++) {
			result[k] = Math.min(leftMax[k], rightMax[k]) - surface[k];
		}
		return result;
	}

}
