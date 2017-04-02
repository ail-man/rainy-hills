package com.ail.crxmarkets.draw.impl;

import com.ail.crxmarkets.Utils;
import com.ail.crxmarkets.draw.SurfaceDrawer;
import com.ail.crxmarkets.model.Surface;

/**
 * Draws surface in console horizontally
 *
 * @author Arthur Lomsadze (ailman1985@gmail.com)
 * @see Surface
 */
public class HorizontalConsoleSurfaceDrawer implements SurfaceDrawer {

	@Override
	public void draw(Surface surface) {
		System.out.println("SURFACE\n");

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
					System.out.print(" ");
				} else {
					System.out.print("#");
				}
			}
			max--;
			System.out.println();
		}

		printLine(surf.length);
	}

	@Override
	public void drawWithWater(Surface surface) {
		System.out.println("SURFACE WITH WATER. TOTAL WATER: " + Utils.sum(surface.getWater()) + "\n");

		int[] surf = surface.getSurface();
		int[] wat = surface.getWater();
		int var, level;

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
					System.out.print(" ");
				} else {
					if (surf[x] < max) {
						System.out.print("~");
					} else {
						System.out.print("#");
					}
				}
			}
			max--;
			System.out.println();
		}

		printLine(surf.length);
	}

	private void printLine(int lenght) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lenght; i++) {
			sb.append("-");
		}
		System.out.println(sb.toString() + "\n");
	}

}
