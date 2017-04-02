package com.ail.crxmarkets.jsf;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtils {

	@SuppressWarnings("unused")
	public static void info(String message) {
		FacesContext.getCurrentInstance()
				.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, FacesUtils.getlocalizedString(ResourceBundles.GENERAL, "info"), message));
	}

	@SuppressWarnings("unused")
	public static void warn(String message) {
		FacesContext.getCurrentInstance()
				.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, FacesUtils.getlocalizedString(ResourceBundles.GENERAL, "warning") + "!", message));
	}

	public static void error(String message) {
		FacesContext.getCurrentInstance()
				.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, FacesUtils.getlocalizedString(ResourceBundles.GENERAL, "error") + "!", message));
	}

	@SuppressWarnings("unused")
	public static void fatal(String message) {
		FacesContext.getCurrentInstance()
				.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, FacesUtils.getlocalizedString(ResourceBundles.GENERAL, "info") + "fatal", message));
	}

	public static String getlocalizedString(String bundleName, String strKey) {
		ResourceBundle bundle = getResourceBundle(bundleName);
		return bundle.getString(strKey);
	}

	@SuppressWarnings("WeakerAccess")
	public static ResourceBundle getResourceBundle(String name) {
		FacesContext ctx = FacesContext.getCurrentInstance();
		Locale locale = ctx.getViewRoot().getLocale();
		return ResourceBundle.getBundle(name, locale);
	}
}
