package com.ail.crxmarkets.ejb;

import javax.ejb.Local;

@Local
public interface RainyHillsEjbLocal {

	int calcWaterVolumeOnSurface(int[] surface, int method);

}
