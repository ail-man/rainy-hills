package com.ail.crxmarkets.ejb;

import javax.ejb.Local;

@Local
public interface RainyHillsEjbLocal {

	long calcWaterVolumeOnSurface(int[] surface, int method);

}
