package com.ail.crxmarkets.model.waterfill.impl;

import com.ail.crxmarkets.model.waterfill.WaterFillMethod;

/**
 * Implementation of algorithm for water amount calculation on a surface with
 * vessel method.
 *
 * <p>Consider areas with a water as two-dimensional vessels with a curved
 * bottom which have left and right walls. Left and right walls can be
 * different heights. Curved bottom can't be higher than the smallest wall of
 * the vessel. The problem is to find the left and right walls of all vessels
 * and then sum their volumes.
 *
 * <p>Algorithm description:
 *
 * <p>1. Run over the surface from left to right.
 *
 * <p>2. Check in a loop:
 * <p>- If the point of the surface is above the following then it's the left
 * wall of the vessel. Record it as *left* and exit the loop.
 * <p>- Otherwise, we pass to the next point to the right and continue the cycle.
 *
 * <p>As soon as we found the left wall, we look for the right one. To do this,
 * we jump over the one point after the *left* (because the water can't
 * accumulate between two adjacent points) and run farther to the right.
 *
 * <p>3. Check in a loop:
 * <p>- If the point is higher than the previous then it is probably the right
 * wall of the vessel (but still not a fact!) - Record it as *right* and exit
 * the loop.
 * <p>- Otherwise, we pass to the next point to the right and continue the
 * cycle.
 *
 * <p>4. Now we need to make sure that *right* is really the right wall of the
 * vessel. To do this, we check in a second loop (we continue to run over the
 * surface from *(right + 1)*):
 * <p>If the point is higher than the *right*, then check:
 * <p>a) if the *right* and current point are both higher than the *left*, it
 * means that we found the right wall of the vessel (which is equal to
 * *right*), exit the loop;
 * <p>b) otherwise, assign *right* to this point and continue the cycle.
 *
 * <p>As soon as we found the left and right walls of the vessel, we can
 * calculate the water in it. The water level in the vessel equals the height
 * of the smallest wall. Hence we can calculate the amount of water above
 * each surface point.
 *
 * <p>Then continue to move further along the surface starting with the *
 * (right +
 * 1)* and look for the next vessel walls on the same algorithm.
 *
 * <p>The complexity of the algorithm depends on the curvature of the surface.
 * <p>The upper bound of the complexity of the algorithm equals Ω(N + (N - 2) + (N - 4) + ... + (N - N))
 * <p>The lower bound of the complexity of the algorithm equals O(N).
 *
 * @author Arthur Lomsadze (ailman1985@gmail.com)
 */
public class WFMFullVessel implements WaterFillMethod {

	/**
	 * Calculates the maximum amount of water that can fit above the each
	 * point of the surface and returns in the form of an array
	 *
	 * @param surface     surface array
	 * @param water       not used
	 * @param waterToFill not used
	 * @return array of water amount above the each point of the surface
	 */
	@Override
	public int[] calcWaterOnSurface(int[] surface, int[] water, int[] waterToFill) {
		int[] wat = new int[surface.length];

		if (surface.length < 3) {
			for (int i = 0; i < surface.length; i++) {
				wat[i] = 0;
			}
			return wat;
		}

		int left;
		int current = 0;

		while (current < surface.length - 1) {
			if (surface[current] > surface[current + 1]) {
				left = current;
				wat[left] = 0;

				int right = getRight(surface, left, current);

				if (right > left) {
					calcWaterInVessel(surface, wat, left, right);
					current = right;
				} else {
					current++;
				}
			} else {
				wat[current] = 0;
				current++;
			}
		}

		wat[current] = 0;
		return wat;
	}

	private int getRight(int[] surface, int left, int current) {
		int right = 0;

		current += 2;
		while (current < surface.length) {
			if (surface[current - 1] < surface[current]) {
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
