package com.ail.crxmarkets.mb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ail.crxmarkets.ejb.RainyHillsEjbLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "mbRainyHills")
@ViewScoped
public class MbRainyHills {

	public static final String PAGE_NAME = "rainyHills";

	private static final Logger log = LoggerFactory.getLogger(MbMain.class);

	private long totalWater;
	private String calculationTime;

	private int algorithm;
	private String field;

	@EJB
	private RainyHillsEjbLocal rainyHillsEjbLocal;

	@PostConstruct
	public void init() {
		log.debug("Init MBean {} success", this.getClass().getName());
	}

	public String calculate() {
		log.debug("{}.calculate() invoked", this.getClass().getName());
		rainyHillsEjbLocal.calcWaterOnSurface(new int[] { 1 }, 2);
		return MbRainyHills.PAGE_NAME;
	}

	public long getTotalWater() {
		return totalWater;
	}

	public String getCalculationTime() {
		return calculationTime;
	}

	public int getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(int algorithm) {
		this.algorithm = algorithm;
	}
}
