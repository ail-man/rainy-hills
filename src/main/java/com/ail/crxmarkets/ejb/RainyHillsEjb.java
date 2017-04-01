package com.ail.crxmarkets.ejb;

import javax.ejb.Stateless;

import com.ail.crxmarkets.model.waterfill.WaterFillMethod;
import com.ail.crxmarkets.model.waterfill.impl.WFMFullTower;
import com.ail.crxmarkets.model.waterfill.impl.WFMFullVessel;

@Stateless
public class RainyHillsEjb implements RainyHillsEjbLocal {

	@Override
	public long calcWaterOnSurface(int[] surface, int method) {
		WaterFillMethod algorithm;

		switch (method) {
		case 0:
			algorithm = new WFMFullVessel();
			break;
		case 1:
			algorithm = new WFMFullTower();
			break;
		default:
			algorithm = new WFMFullVessel();
			break;
		}

		int totalWater = 0;
		for (int water : algorithm.calcWaterOnSurface(surface, null, null)) {
			totalWater += water;
		}
		return totalWater;
	}

}
