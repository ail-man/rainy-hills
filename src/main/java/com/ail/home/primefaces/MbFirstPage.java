package com.ail.home.primefaces;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.ail.home.common.User;

@ManagedBean(name = "mbFirstPage")
@ViewScoped
public class MbFirstPage {

	private User user;

	@PostConstruct
	private void init() {
		this.user = new User();
	}

	public User getUser() {
		return user;
	}
}
