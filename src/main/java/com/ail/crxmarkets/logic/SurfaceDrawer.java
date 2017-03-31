package com.ail.crxmarkets.logic;

/**
 * Рисовальщик поверхности с водой и без воды
 */
public interface SurfaceDrawer {

	/**
	 * Рисует поверхость
	 *
	 * @param surface массив поверхности
	 */
	void drawSurface(int[] surface);

	/**
	 * Рисует поверхость с водой
	 *
	 * @param surface массив поверхности
	 * @param water   массив воды на поверхности
	 */
	void drawSurfaceWithWater(int[] surface, int[] water);

}
