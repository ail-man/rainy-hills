package com.ail.crxmarkets.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean(name = "mbMain")
@RequestScoped
public class MbMain implements Serializable {

	private static final long serialVersionUID = 554344292726493887L;
	private static final Logger log = LoggerFactory.getLogger(MbMain.class);

	@PostConstruct
	public void init() {
		log.debug("Init MBean {} success", this.getClass().getName());
	}

	public String go() {
		log.debug("{}.go() invoked", this.getClass().getName());
		return MbRainyHills.PAGE_NAME;
	}

}
