package com.ail.crxmarkets.draw;

import com.ail.crxmarkets.model.Surface;

/**
 * Рисовальщик поверхности
 */
public interface SurfaceDrawer {

	/**
	 * Рисует поверхость без воды
	 *
	 * @param surface объект поверхности
	 */
	void drawSurface(Surface surface);

	/**
	 * Рисует поверхость с водой
	 *
	 * @param surface объект поверхности
	 */
	void drawSurfaceWithWater(Surface surface);

}
