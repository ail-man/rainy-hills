package com.ail.crxmarkets.ejb;

import javax.ejb.Stateless;

import com.ail.crxmarkets.model.Surface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Stateless
public class RainyHillsEjb implements RainyHillsEjbLocal {

	private static final Logger log = LoggerFactory.getLogger(RainyHillsEjb.class);

	@Override
	public Surface getRandomSurface(int length, int minHeight, int maxHeight) {
		log.info("Generate new surface");
		return Surface.random(length, minHeight, maxHeight);
	}

}
