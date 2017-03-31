package com.ail.crxmarkets.ejb;

import javax.ejb.Local;

@Local
public interface RainyHillsEjbLocal {

	long calcWaterOnSurface(int[] surface, int method);

}
