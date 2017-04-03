package com.ail.crxmarkets.ejb;

import javax.ejb.Local;

import com.ail.crxmarkets.model.Surface;

@Local
public interface RainyHillsEjbLocal {

	Surface getRandomSurface(int length, int minHeight, int maxHeight);

}
