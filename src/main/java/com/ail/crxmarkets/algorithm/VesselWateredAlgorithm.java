package com.ail.crxmarkets.algorithm;

public class VesselWateredAlgorithm {

	public int[] calcWaterOnSurface(int[] surface) {
		System.out.println("===INITIAL-SURFACE===============================================================");
		printSurface(surface);

		int[] water = new int[surface.length];

		if (surface.length < 3) {
			System.out.println("===WATERED-SURFACE===============================================================");
			printSurface(surface);
			for (int i = 0; i < surface.length; i++) {
				water[i] = 0;
			}
			return water;
		}

		System.out.println("===WATERED-SURFACE===============================================================");

		int left;
		int current = 0;

		while (current < surface.length - 1) {
			if (surface[current] > surface[current + 1]) {
				left = current;
				printSurfaceWithoutWater(left, surface[left], water);

				int right = getRight(surface, left, current);

				if (right > left) {
					calcVesselVolume(surface, water, left, right);
					current = right;
				} else {
					current++;
				}
			} else {
				printSurfaceWithoutWater(current, surface[current], water);
				current++;
			}
		}

		printSurfaceWithoutWater(current, surface[current], water);
		return water;
	}

	private int getRight(int[] surface, int left, int current) {
		int right = 0;

		current = current + 2;
		while (current < surface.length) {
			if (surface[current] > surface[current - 1]) {
				right = current;
				break;
			} else {
				current++;
			}
		}

		while (++current < surface.length) {
			if (surface[current] > surface[right]) {
				if (surface[right] > surface[left] && surface[current] > surface[left]) {
					break;
				} else {
					right = current;
				}
			}
		}

		return right;
	}

	private long calcVesselVolume(int[] surface, int[] water, int left, int right) {
		long result = 0;

		int waterLevel = Math.min(surface[left], surface[right]);

		int cur = left;

		while (++cur < right) {
			if (surface[cur] < waterLevel) {
				result += waterLevel - surface[cur];
				printSurfaceWithWater(surface[cur], waterLevel);
				water[cur] = waterLevel;
			} else {
				printSurfaceWithoutWater(cur, surface[cur], water);
			}
		}

		return result;
	}

	private void printSurface(int[] surface) {
		for (int height : surface) {
			System.out.print("|");
			for (int i = 0; i < height; i++) {
				System.out.print("X");
			}
			System.out.println();
		}
	}

	private void printSurfaceWithoutWater(int index, int surfaceHeight, int[] water) {
		System.out.print("|");
		for (int i = 0; i < surfaceHeight; i++) {
			System.out.print("X");
			water[index] = 0;
		}
		System.out.println();
	}

	private void printSurfaceWithWater(int surfaceHeight, int waterLevel) {
		System.out.print("|");
		for (int i = 0; i < surfaceHeight; i++) {
			System.out.print("X");
		}
		for (int i = 0; i < waterLevel - surfaceHeight; i++) {
			System.out.print("~");
		}
		System.out.println();
	}
}
