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
