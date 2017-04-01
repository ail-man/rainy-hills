package com.ail.crxmarkets.draw.impl;

import com.ail.crxmarkets.draw.SurfaceDrawer;
import com.ail.crxmarkets.model.Surface;

/**
 * Рисует поверхность в консоли вертикально
 */
public class VerticalConsoleSurfaceDrawer implements SurfaceDrawer {

	@Override
	public void drawSurface(Surface surface) {
		System.out.println("===SURFACE===============================================================");
		int[] surf = surface.getSurface();
		for (int height : surf) {
			System.out.print("|");
			for (int i = 0; i < height; i++) {
				System.out.print("X");
			}
			System.out.println();
		}
		System.out.println("=================================================================================\n");
	}

	@Override
	public void drawSurfaceWithWater(Surface surface) {
		System.out.println("===SURFACE-WITH-WATER===============================================================");
		int[] sur = surface.getSurface();
		int[] wat = surface.getWater();
		for (int i = 0; i < sur.length; i++) {
			System.out.print("|");
			for (int j = 0; j < sur[i]; j++) {
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
