package com.ail.crxmarkets.algorithm;

public interface RainyHillsAlgorithm {

	/**
	 * Рассчитывает количество воды над каждым элементом поверхности,
	 * определённой массивом surface, и возвращает в виде другого массива
	 *
	 * @param surface массив поверхности
	 * @return массив количества воды над каждым элементом поверхности
	 */
	int[] calcWaterOnSurface(int[] surface);

}
