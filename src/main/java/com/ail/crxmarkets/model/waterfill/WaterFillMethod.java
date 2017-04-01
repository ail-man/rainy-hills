package com.ail.crxmarkets.model.waterfill;

/**
 * Определяет интерфейс для алгоритмов для рассчёта воды на поверхности
 */
public interface WaterFillMethod {

	/**
	 * Рассчитывает количество воды над каждым элементом поверхности
	 * после заливания воды и возвращает в виде другого массива
	 *
	 * @param surface     массив поверхности
	 * @param water       массив воды, имеющейся на поверхности
	 * @param waterToFill массив колличества воды, который падает на поверхность
	 * @return массив количества воды над каждым элементом поверхности
	 */
	int[] calcWaterOnSurface(int[] surface, int[] water, int[] waterToFill);

}
