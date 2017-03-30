package com.ail.crxmarkets.mb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.ail.crxmarkets.ejb.RainyHillsEjbLocal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "mbRainyHills")
@RequestScoped
public class MbRainyHills {

	public static final String PAGE_NAME = "rainyHills";

	private static final Logger log = LoggerFactory.getLogger(MbMain.class);

	@EJB
	private RainyHillsEjbLocal rainyHillsEjbLocal;

	@PostConstruct
	public void init() {
		log.debug("Init MBean {} success", this.getClass().getName());
	}

	public String calculate() {
		log.debug("{}.calculate() invoked", this.getClass().getName());
		return MbRainyHills.PAGE_NAME;
	}
}
