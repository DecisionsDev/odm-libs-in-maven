/*
 * Licensed Materials - Property of IBM
 * 5725-B69 5655-Y17 5655-Y31 5724-X98 5724-Y15 5655-V82
 * Copyright IBM Corp. 1987, 2016. All Rights Reserved.
 *
 * Note to U.S. Government Users Restricted Rights:
 * Use, duplication or disclosure restricted by GSA ADP Schedule
 * Contract with IBM Corp.
 */

package controller;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class ComponentSessionListener implements HttpSessionListener {
    public static final String RULEEXECUTIONSESSIONNAME = "RuleExecutionController";
    public static final String RULEAPPSESSIONNAME = "RuleAppController";
    public static final String EJB_VERSION = "EJB_VERSION";

    /*
     * Create a controller that manage the ruleapp.
     * @see javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http.HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent evt) {
        HttpSession session = evt.getSession();
        String version = evt.getSession().getServletContext().getInitParameter("EJB_VERSION");

        if ("3".equalsIgnoreCase(version)) {
            session.setAttribute(RULEEXECUTIONSESSIONNAME, new RuleExecutionController());
            session.setAttribute(RULEAPPSESSIONNAME, new RuleAppController());
        } else {
            throw new IllegalStateException("Unknown ejb version");
        }
        session.setAttribute(EJB_VERSION, version);
    }

    /*
     * Nothing to do.
     * @see javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet.http.HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent evt) {
    }

}
