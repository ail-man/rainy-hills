package com.ail.crxmarkets.draw;

/**
 * Рисует поверхность в консоли вертикально
 */
public class VerticalConsoleSurfaceDrawer implements SurfaceDrawer {

	@Override
	public void drawSurface(int[] surface) {
		System.out.println("===SURFACE===============================================================");
		for (int height : surface) {
			System.out.print("|");
			for (int i = 0; i < height; i++) {
				System.out.print("X");
			}
			System.out.println();
		}
		System.out.println("=================================================================================\n");
	}

	@Override
	public void drawSurfaceWithWater(int[] surface, int[] water) {
		System.out.println("===SURFACE-WITH-WATER===============================================================");
		for (int i = 0; i < surface.length; i++) {
			System.out.print("|");
			for (int j = 0; j < surface[i]; j++) {
				System.out.print("X");
			}
			for (int j = 0; j < water[i]; j++) {
				System.out.print("~");
			}
			System.out.println();
		}
		System.out.println("=================================================================================\n");
	}

}
