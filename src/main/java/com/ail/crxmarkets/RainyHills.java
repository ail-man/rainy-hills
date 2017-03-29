package com.ail.crxmarkets;

public class RainyHills {

	// Рассматриваем участки с водой как двумерные сосуды с кривым дном,
	// у которых есть левая и правая стенки (left, right).
	// Стенки могут быть разной высоты.
	// Участки кривого дна сосуда не могут быть выше стенок сосуда.
	// Задача будет состоять в нахождении левой и правой стенок всех сосудов
	// и суммирование их объемов.
	// Алгоритм имеет сложность O(N^2)
	public int calcWaterVolumeOnSurface(int[] surface) {
		if (surface.length < 3) {
			return 0;
		}

		int result = 0;

		int left;
		int current = 0;

		while (current < surface.length - 1) {
			if (surface[current] > surface[current + 1]) {
				left = current;

				int right = getRight(surface, left, current);

				if (right > left) {
					result += calcVesselVolume(surface, left, right);
					current = right;
				} else {
					current++;
				}
			} else {
				current++;
			}
		}

		return result;
	}

	private int getRight(int[] surface, int left, int current) {
		int right = 0;

		int ahead = current + 2;
		while (ahead < surface.length) {
			if (surface[ahead - 1] < surface[ahead]) {
				right = ahead;
				break;
			} else {
				ahead++;
			}
		}

		while (++ahead < surface.length) {
			if (surface[right] < surface[ahead]) {
				if (surface[right] > surface[left] && surface[ahead] > surface[left]) {
					break;
				} else {
					right = ahead;
				}
			}
		}

		return right;
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
