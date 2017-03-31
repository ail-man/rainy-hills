package com.ail.crxmarkets.logic;

/**
 * Реализация алгоритма для рассчёта количества воды методом сосудов.
 * Рассматриваем участки с водой как двумерные сосуды с кривым дном,
 * у которых есть левая и правая стенки (left, right).
 * Стенки могут быть разной высоты.
 * Участки кривого дна сосуда не могут быть выше стенок сосуда.
 * Задача состоит в нахождении левой и правой стенок всех сосудов и суммирование их объемов.
 * <p>
 * Описание алгоритма:
 * <p>
 * 1. Бежим по поверхности слева-направо.
 * <p>
 * 2. В цикле проверяем:
 * - Если элемент поверхности выше следующего, то это левая стенка сосуда.
 * Фиксируем её как <left> и выходим из цикла.
 * - Иначе, проходим на 1 элемент вправо и продолжаем цикл.
 * <p>
 * Теперь ищем правую стенку сосуда.
 * Для этого перепрыгиваем один элемент после <left>,
 * т.к. вода не может скопиться на двух рядом стоящих стенках, и бежим дальше направо.
 * <p>
 * 3. В цикле проверяем:
 * - Если элемент поверхности выше предыдущего, то это ВОЗМОЖНО правая стенка сосуда
 * (но пока еще не факт!) - фиксируем как <right>, и выходим из данного цикла.
 * - Иначе, проходим на 1 элемент вправо и продолжаем цикл.
 * <p>
 * 4. Во втором цикле (продолжаем бежать дальше по поверхности) проверяем:
 * Если элемент выше <right>, то проверяем:
 * 1) если <right> и этот элемент оба сразу выше <left>,
 * то значит мы нашли правую стенку сосуда, (которая равна <right>), выходим из цикла;
 * 2) иначе, делаем <right> = этому элементу и продолжаем цикл.
 * <p>
 * Затем берём поверхность между <left> и <right>, считаем количество воды.
 * и продолжаем двигаться дальше по поверхности по тому же алгоритму.
 * <p>
 * Алгоритм предположительно имеет сложность O(N^2)
 */
public class VesselAlgorithm implements RainyHillsAlgorithm {

	@Override
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