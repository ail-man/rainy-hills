package com.ail.crxmarkets.draw.impl;

import com.ail.crxmarkets.Utils;
import com.ail.crxmarkets.draw.SurfaceDrawer;
import com.ail.crxmarkets.model.Surface;

/**
 * Draws surface in console vertically
 *
 * @author Arthur Lomsadze (ailman1985@gmail.com)
 * @see Surface
 */
public class VerticalConsoleSurfaceDrawer implements SurfaceDrawer {

	@Override
	public void draw(Surface surface) {
		System.out.println("===SURFACE===============================================================");
		int[] surf = surface.getSurface();
		for (int height : surf) {
			System.out.print("|");
			for (int i = Utils.min(surf); i < height; i++) {
				System.out.print("X");
			}
			System.out.println();
		}
		System.out.println("=================================================================================\n");
	}

	@Override
	public void drawWithWater(Surface surface) {
		System.out.println("===SURFACE-WITH-WATER===============================================================");
		int[] sur = surface.getSurface();
		int[] wat = surface.getWater();
		for (int i = 0; i < sur.length; i++) {
			System.out.print("|");
			for (int j = Utils.min(sur); j < sur[i]; j++) {
				System.out.print("X");
			}
			for (int j = 0; j < wat[i]; j++) {
				System.out.print("~");
			}
			System.out.println();
		}
		System.out.println("=================================================================================\n");
	}

}
