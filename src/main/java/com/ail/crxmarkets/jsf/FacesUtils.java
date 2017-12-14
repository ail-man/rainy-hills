package com.ail.crxmarkets.jsf;

import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Helper for JSF
 */
public final class FacesUtils {

    private FacesUtils() {
    }

    /**
     * Adds info message to JSF context
     *
     * @param message message to add
     */
    @SuppressWarnings("unused")
    public static void info(String message) {
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO,
                                FacesUtils.getlocalizedString(ResourceBundles.GENERAL, "info"),
                                message));
    }

    /**
     * Gets localized string from resource bundles by key
     *
     * @param bundleName resource bundle name
     * @param strKey     key of property in resource bundle
     * @return localized string
     */
    public static String getlocalizedString(String bundleName, String strKey) {
        ResourceBundle bundle = getResourceBundle(bundleName);
        return bundle.getString(strKey);
    }

    /**
     * Gets {@link ResourceBundle} object
     *
     * @param name resource bundle name
     * @return {@link ResourceBundle} object
     */
    @SuppressWarnings("WeakerAccess")
    public static ResourceBundle getResourceBundle(String name) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        Locale locale = ctx.getViewRoot().getLocale();
        return ResourceBundle.getBundle(name, locale);
    }

    /**
     * Adds warning message to JSF context
     *
     * @param message message to add
     */
    @SuppressWarnings("unused")
    public static void warn(String message) {
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                FacesUtils.getlocalizedString(ResourceBundles.GENERAL, "warning") + "!",
                                message));
    }

    /**
     * Adds error message to JSF context
     *
     * @param message message to add
     */
    public static void error(String message) {
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                FacesUtils.getlocalizedString(ResourceBundles.GENERAL, "error") + "!",
                                message));
    }

    /**
     * Adds fatal error message to JSF context
     *
     * @param message message to add
     */
    @SuppressWarnings("unused")
    public static void fatal(String message) {
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_FATAL,
                                FacesUtils.getlocalizedString(ResourceBundles.GENERAL, "fatal") + "!",
                                message));
    }
}
