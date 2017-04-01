package com.ail.crxmarkets.model.waterfill;

/**
 * Определяет интерфейс алгоритма для рассчёта воды на поверхности,
 * определённой массивом
 */
public interface WaterFillMethod {

	/**
	 * Рассчитывает количество воды над каждым элементом поверхности,
	 * определённой массивом surface, и возвращает в виде другого массива
	 *
	 * @param surface     массив поверхности
	 * @param water       массив воды, имеющейся на поверхности
	 * @param waterToFill массив колличества воды, который падает на поверхность
	 * @return массив количества воды над каждым элементом поверхности
	 */
	int[] calcWaterOnSurface(int[] surface, int[] water, int[] waterToFill);

}
