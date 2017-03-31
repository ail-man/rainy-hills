package com.ail.crxmarkets.histogram;

public class HorizontalConsoleHistogram implements Histogram {

	@Override
	public void drawHistogram(int[] surface) {
		System.out.println("===INITIAL-SURFACE===============================================================");

		int var;

		int max = surface[0];

		for (int i = 0; i < surface.length; i++) {
			if (surface[i] > max) {
				max = surface[i];
			}
		}

		var = max;

		for (int y = 0; y < var; y++) {
			for (int x = 0; x < surface.length; x++) {
				if (surface[x] < max) {
					System.out.print(" ");
				} else {
					System.out.print("#");
				}
			}
			max--;
			System.out.println();
		}
	}

	@Override
	public void drawDoubleLayerHistogram(int[] surface, int[] water) {
		System.out.println("===WATERED-SURFACE===============================================================");

		int var, level;

		int max = surface[0] + water[0];

		for (int i = 0; i < surface.length; i++) {
			level = surface[i] + water[i];
			if (level > max) {
				max = surface[i];
			}
		}

		var = max;

		for (int y = 0; y < var; y++) {
			for (int x = 0; x < surface.length; x++) {
				level = surface[x] + water[x];
				if (level < max) {
					System.out.print(" ");
				} else {
					if (surface[x] < max) {
						System.out.print("~");
					} else {
						System.out.print("#");
					}
				}
			}
			max--;
			System.out.println();
		}
	}

}
