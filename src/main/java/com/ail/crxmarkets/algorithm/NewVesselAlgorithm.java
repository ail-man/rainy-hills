package com.ail.crxmarkets.algorithm;

/**
 * Алгоритм является усовершенствованной версией алгоритма VesselAlgorithm,
 * позволяющим возвращать массив количества воды над каждым элементом поверхности.
 * В основу взят тот же алгорим рассчёта методом сосудов.
 *
 * @see com.ail.crxmarkets.algorithm.VesselAlgorithm#calcWaterVolumeOnSurface(int[] surface)
 */
public class NewVesselAlgorithm {

	/**
	 * Рассчитывает количество воды над каждым элементом поверхности
	 * и возвращает в виде массива
	 * определённой массивом surface
	 *
	 * @param surface массив поверхности
	 * @return массив количества воды над каждым элементом поверхности
	 */
	public int[] calcWaterOnSurface(int[] surface) {
		int[] water = new int[surface.length];

		if (surface.length < 3) {
			for (int i = 0; i < surface.length; i++) {
				water[i] = 0;
			}
			return water;
		}

		int left;
		int current = 0;

		while (current < surface.length - 1) {
			if (surface[current] > surface[current + 1]) {
				left = current;
				water[left] = 0;

				int right = getRight(surface, left, current);

				if (right > left) {
					calcWaterInVessel(surface, water, left, right);
					current = right;
				} else {
					current++;
				}
			} else {
				water[current] = 0;
				current++;
			}
		}

		water[current] = 0;
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

	private void calcWaterInVessel(int[] surface, int[] water, int left, int right) {
		int waterLevel = Math.min(surface[left], surface[right]);

		int cur = left;

		while (++cur < right) {
			if (surface[cur] < waterLevel) {
				water[cur] = waterLevel - surface[cur];
			} else {
				water[cur] = 0;
			}
		}
	}

}
