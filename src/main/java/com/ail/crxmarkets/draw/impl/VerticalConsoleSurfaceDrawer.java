package com.ail.crxmarkets.draw.impl;

import com.ail.crxmarkets.Utils;
import com.ail.crxmarkets.draw.SurfaceDrawer;
import com.ail.crxmarkets.model.Surface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Draws surface in console vertically
 *
 * @author Arthur Lomsadze (ailman1985@gmail.com)
 * @see Surface
 */
public class VerticalConsoleSurfaceDrawer implements SurfaceDrawer {

	private static final Logger log = LoggerFactory.getLogger(VerticalConsoleSurfaceDrawer.class);

	@Override
	public void draw(Surface surface) {
		StringBuilder sb = new StringBuilder();

		sb.append("\n\n===SURFACE===============================================================\n");
		int[] surf = surface.getSurface();
		for (int height : surf) {
			sb.append("|");
			for (int i = Utils.min(surf); i < height; i++) {
				sb.append("X");
			}
			sb.append("\n");
		}
		sb.append("=================================================================================\n");

		log.info(sb.toString());
	}

	@Override
	public void drawWithWater(Surface surface) {
		StringBuilder sb = new StringBuilder();

		sb.append("\n\n===SURFACE-WITH-WATER===============================================================\n");
		int[] sur = surface.getSurface();
		int[] wat = surface.getWater();
		for (int i = 0; i < sur.length; i++) {
			sb.append("|");
			for (int j = Utils.min(sur); j < sur[i]; j++) {
				sb.append("X");
			}
			for (int j = 0; j < wat[i]; j++) {
				sb.append("~");
			}
			sb.append("\n");
		}
		sb.append("=================================================================================\n");

		log.info(sb.toString());
	}

}
