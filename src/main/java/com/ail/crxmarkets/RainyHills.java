package com.ail.crxmarkets;

public class RainyHills {

	// Рассматриваем участки с водой как двумерные сосуды с кривым дном,
	// у которых есть левая и правая стенки (l, r)
	// и стенки могут быть разной высоты.
	// У левой стенки вода стекает вправо, а у правой стенки - влево.
	public int calcWaterVolumeOnSurface(int[] surface) {
		int result = 0;

		int left, right = 0, tmpRight = 0;
		int i = 0;

		// пробегаемся по поверхности слева направо
		while (i < surface.length - 1) {
			// Если поверхность правее текущей точки является ниже текущей точки
			if (surface[i] > surface[i + 1]) {
				// то это левая стенка сосуда
				left = i;

				// от левой стенки движемся по поверхности дальше направо и ищем правую стенку сосуда
				while (++i < surface.length) {

					// Если поверхность выше предыдущей точки
					if (surface[i] >= surface[i - 1]) {
						// то это временная правая стенка сосуда
						tmpRight = i;
						break;
					}
				}

				// вторым циклом пробегаемся от следующей точки
				for (int j = i + 1; j < surface.length; j++) {
					// если поверхность выше или равна временной правой стенки сосуда
					if (surface[j] >= surface[tmpRight]) {
						// то это временная правая стенка сосуда
						tmpRight = j;
					}
				}

				// и самую высокую поверхность делаем правой стенкой сосуда
				if (tmpRight > right) {
					right = tmpRight;
				}

				// затем считаем объем воды в найденном сосуде
				if (right > left) {
					result += calcVesselVolume(surface, left, right);
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
