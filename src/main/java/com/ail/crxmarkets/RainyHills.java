package com.ail.crxmarkets;

public class RainyHills {

	// Рассматриваем участки с водой как двумерные сосуды с кривым дном,
	// у которых есть левая и правая стенки (l, r)
	// и стенки могут быть разной высоты.
	// У левой стенки вода стекает вправо, а у правой стенки - влево.
	public int calcWaterVolumeOnSurface(int[] surface) {
		int result = 0;

		int l, r = 0;
		int cur = 0;

		// пробегаемся по поверхности слева направо
		while (cur < surface.length - 1) {
			// Если вода может стечь вправо
			if (surface[cur] > surface[cur + 1]) {
				// то это левая стенка сосуда
				l = cur;
				// от левой стенки движемся по поверхности дальше направо и ищем правую стенку сосуда
				while (++cur < surface.length - 1) {
					// Если вода может стечь влево и не может стечь дальше вправо
					if (surface[cur] >= surface[cur - 1] && surface[cur] < surface[cur + 1]) {
						// то это правая стенка сосуда
						r = cur + 1;
						break;
					}
				}
				// считаем объем воды в сосуде
				if (r > l) {
					result += calcVesselVolume(surface, l, r);
					l = r;
				}
			}
			cur++;
		}

		return result;
	}

	public int calcVesselVolume(int[] surface, int l, int r) {
		int result = 0;

		int waterLevel = Math.min(surface[l], surface[r]);

		int cur = l;

		while (++cur < r) {
			result += waterLevel - surface[cur];
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
