package com.ail.crxmarkets;

public class RainyHills {

	// Рассматриваем участки с водой как двумерные сосуды с кривым дном,
	// у которых есть левая и правая стенки (left, right).
	// Стенки могут быть разной высоты.
	// Участки кривого дна сосуда не могут быть выше стенок сосуда.
	// Задача будет состоять в нахождении левой и правой стенок всех сосудов
	// и суммирование их объемов.
	// Алгоритм имеет сложность O(2 * N) = O(N), т.к. осуществляется один полный проход
	// для поиска всех стенок сосудов и еще один проход по всем сосудам
	// для рассчёта их объемов.
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
					if (surface[curRight] < surface[k]) {
						if (surface[curRight] > surface[left] && surface[k] > surface[left]) {
							right = curRight;
							break;
						} else {
							curRight = k;
						}
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
