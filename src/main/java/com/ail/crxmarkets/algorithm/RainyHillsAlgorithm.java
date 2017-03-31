package com.ail.crxmarkets.algorithm;

public interface RainyHillsAlgorithm {

	/**
	 * Рассчитывает количество воды над каждым элементом поверхности
	 * и возвращает в виде массива
	 * определённой массивом surface
	 *
	 * @param surface массив поверхности
	 * @return массив количества воды над каждым элементом поверхности
	 */
	int[] calcWaterOnSurface(int[] surface);

}
