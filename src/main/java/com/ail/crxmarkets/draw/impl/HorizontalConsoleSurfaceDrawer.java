package com.ail.crxmarkets.draw.impl;

import com.ail.crxmarkets.Utils;
import com.ail.crxmarkets.draw.SurfaceDrawer;
import com.ail.crxmarkets.model.Surface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Draws surface in console horizontally
 *
 * @author Arthur Lomsadze (ailman1985@gmail.com)
 * @see Surface
 */
public class HorizontalConsoleSurfaceDrawer implements SurfaceDrawer {

	private static final Logger log = LoggerFactory.getLogger(HorizontalConsoleSurfaceDrawer.class);

	@Override
	public void draw(Surface surface) {
		StringBuilder sb = new StringBuilder();

		sb.append("\n\nSURFACE\n\n");

		int[] surf = surface.getSurface();
		int max = surf[0];
		int var;

		for (int aSurf : surf) {
			if (aSurf > max) {
				max = aSurf;
			}
		}

		var = max;

		for (int y = Utils.min(surf); y < var; y++) {
			for (int aSurf : surf) {
				if (aSurf < max) {
					sb.append(" ");
				} else {
					sb.append("#");
				}
			}
			max--;
			sb.append("\n");
		}

		pringWithBottomLine(sb, surf.length);
	}

	@Override
	public void drawWithWater(Surface surface) {
		StringBuilder sb = new StringBuilder();

		sb.append("\n\nSURFACE WITH WATER. TOTAL WATER: ").append(Utils.sum(surface.getWater())).append("\n\n");

		int[] surf = surface.getSurface();
		int[] wat = surface.getWater();
		int var;
		int level;

		int max = surf[0] + wat[0];

		for (int i = 0; i < surf.length; i++) {
			level = surf[i] + wat[i];
			if (level > max) {
				max = surf[i];
			}
		}

		var = max;

		for (int y = Utils.min(surf); y < var; y++) {
			for (int x = 0; x < surf.length; x++) {
				level = surf[x] + wat[x];
				if (level < max) {
					sb.append(" ");
				} else {
					if (surf[x] < max) {
						sb.append("~");
					} else {
						sb.append("#");
					}
				}
			}
			max--;
			sb.append("\n");
		}

		pringWithBottomLine(sb, surf.length);
	}

	private void pringWithBottomLine(StringBuilder sb, int lenght) {
		for (int i = 0; i < lenght; i++) {
			sb.append("-");
		}
		log.info(sb.toString() + "\n");
	}

}
