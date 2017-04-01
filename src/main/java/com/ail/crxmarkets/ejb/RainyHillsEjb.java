package com.ail.crxmarkets.ejb;

import javax.ejb.Stateless;

import com.ail.crxmarkets.algorithm.TowerAlgorithm;
import com.ail.crxmarkets.algorithm.VesselAlgorithm;
import com.ail.crxmarkets.algorithm.WaterCalculatorAlgorithm;

@Stateless
public class RainyHillsEjb implements RainyHillsEjbLocal {

	@Override
	public long calcWaterOnSurface(int[] surface, int method) {
		WaterCalculatorAlgorithm algorithm;

		switch (method) {
		case 0:
			algorithm = new VesselAlgorithm();
			break;
		case 1:
			algorithm = new TowerAlgorithm();
			break;
		default:
			algorithm = new VesselAlgorithm();
			break;
		}

		int totalWater = 0;
		for (int water : algorithm.calcWaterOnSurface(surface)) {
			totalWater += water;
		}
		return totalWater;
	}

}
