package com.ail.crxmarkets.algorithm;

public class VesselWateredAlgorithm {

	public long calcWaterVolumeOnSurface(int[] surface) {
		printAllSurface(surface);

		if (surface.length < 3) {
			printAllSurfaceWithouWater(surface);
			return 0;
		}

		System.out.println("===WATERED-SURFACE===============================================================");

		long result = 0;

		int left;
		int current = 0;

		while (current < surface.length - 1) {
			if (surface[current] > surface[current + 1]) {
				left = current;
				printSurfaceWithoutWater(surface[left]);

				int right = getRight(surface, left, current);

				if (right > left) {
					result += calcVesselVolume(surface, left, right);
					current = right;
				} else {
					current++;
				}
			} else {
				printSurfaceWithoutWater(surface[current]);
				current++;
			}
		}

		printSurfaceWithoutWater(surface[current]);
		return result;
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

	private long calcVesselVolume(int[] surface, int left, int right) {
		long result = 0;

		int waterLevel = Math.min(surface[left], surface[right]);

		int cur = left;

		while (++cur < right) {
			if (surface[cur] < waterLevel) {
				result += waterLevel - surface[cur];
				printSurfaceWithWater(surface[cur], waterLevel);
			} else {
				printSurfaceWithoutWater(surface[cur]);
			}
		}

		return result;
	}

	private void printAllSurface(int[] surface) {
		System.out.println("===INITIAL-SURFACE===============================================================");
		for (int aSurface : surface) {
			printSurfaceWithoutWater(aSurface);
		}
	}

	private void printAllSurfaceWithouWater(int[] surface) {
		System.out.println("===WATERED-SURFACE===============================================================");
		for (int aSurface : surface) {
			printSurfaceWithoutWater(aSurface);
		}
	}

	private void printSurfaceWithoutWater(int surfaceHeight) {
		System.out.print("|");
		for (int i = 0; i < surfaceHeight; i++) {
			System.out.print("X");
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
