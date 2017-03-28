package com.ail.crxmarkets;

public class RainyHills {

	// рассматриваем участки с водой как сосуд с кривым дном,
	// у которого есть левая и правая стенки (l, r)
	public int calcVolume(int[] surface) {
		return calcVesselVolume(surface, 0, surface.length - 1);
		//		int l, r;
		//
		//		int cur = 0;
		//
		//		// пробегаемся по поверхности слева направо
		//		while (cur < surface.length) {
		//			// Если вода может стечь вправо
		//			if (surface[cur] > surface[cur + 1]) {
		//				// то указываем левую стенку
		//				l = cur;
		//
		//				// от левой стенки пробегаемся по поверхности дальше направо
		//				while (cur < surface.length) {
		//					cur++;
		//					if (surface[cur] <= surface[l]) {
		//
		//					}
		//				}
		//			}
		//
		//			cur++;
		//		}
		//
		//		return 0;
	}

	private int calcVesselVolume(int[] surface, int l, int r) {
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
