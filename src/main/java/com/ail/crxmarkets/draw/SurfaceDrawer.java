package com.ail.crxmarkets.draw;

import com.ail.crxmarkets.model.Surface;

/**
 * Surface drawer interface
 *
 * @author Arthur Lomsadze (ailman1985@gmail.com)
 * @see Surface
 */
public interface SurfaceDrawer {

	/**
	 * Draws surface without water
	 *
	 * @param surface {@link Surface} object
	 */
	void draw(Surface surface);

	/**
	 * Draws surface with water
	 *
	 * @param surface {@link Surface} object
	 */
	void drawWithWater(Surface surface);

}
