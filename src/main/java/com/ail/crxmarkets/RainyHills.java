package com.ail.crxmarkets;

public class RainyHills {

	// Рассматриваем участки с водой как двумерные сосуды с кривым дном,
	// у которых есть левая и правая стенки (l, r)
	// и стенки могут быть разной высоты.
	// У левой стенки вода стекает вправо, а у правой стенки - влево.
	public int calcWaterVolumeOnSurface(int[] surface) {
		if (surface.length < 3) {
			return 0;
		}

		int result = 0;

		int left, right = 0, curRight = 0;
		int i = 0;

		while (i < surface.length - 1) {
			if (surface[i] > surface[i + 1]) {
				left = i;

				int j = i + 2;
				while (j < surface.length) {
					if (surface[j - 1] < surface[j]) {
						curRight = j;
						break;
					} else {
						j++;
					}
				}

				int k = j + 1;
				while (k < surface.length) {
					if (surface[k] > surface[left]) {
						curRight = k;
					}
					k++;
				}

				if (curRight > right) {
					right = curRight;
				}

				if (right > left) {
					result += calcVesselVolume(surface, left, right);
					i = right;
				} else {
					i = k;
				}
			} else {
				i++;
			}
		}

		return result;
	}

	public int calcVesselVolume(int[] surface, int left, int right) {
		int result = 0;

		int waterLevel = Math.min(surface[left], surface[right]);

		int cur = left;

		while (++cur < right) {
			if (surface[cur] < waterLevel) {
				result += waterLevel - surface[cur];
			}
		}

		return result;
	}

	// TODO vertical histogram
	public void printSurface(int[] surface) {
		for (int i = 0; i < surface.length; i++) {
			for (int j = 0; j < surface[i]; j++) {
				System.out.print("U");
			}
			System.out.println();
		}
	}
}
