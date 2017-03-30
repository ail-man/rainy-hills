package com.ail.crxmarkets.mb;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.ail.crxmarkets.ejb.RainyHillsEjbLocal;

@ManagedBean(name = "mbRainyHills")
@RequestScoped
public class MbRainyHills {

	@EJB
	private RainyHillsEjbLocal rainyHillsEjbLocal;

	@PostConstruct
	public void init() {

	}

}
