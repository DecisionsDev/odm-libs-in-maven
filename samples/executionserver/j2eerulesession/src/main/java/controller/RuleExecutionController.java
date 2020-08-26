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

import ilog.rules.res.model.IlrFormatException;
import ilog.rules.res.model.IlrPath;
import ilog.rules.res.session.*;
import ilog.rules.res.session.ruleset.IlrBusinessExecutionTrace;
import ilog.rules.res.session.ruleset.IlrExecutionTrace;
import shopmodel.Announcement;
import shopmodel.Customer;
import shopmodel.ShoppingCart;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * This class provides convenient methods to execute rules using the JRules vendor api.
 */
public class RuleExecutionController implements Serializable {
	private static final long serialVersionUID = -4882429548145412189L;
	private static final String rulesetPath = new String("/"+ RuleAppController.RULEAPPNAME + "/"+ RuleAppController.RULESETNAME);

	public RuleExecutionController() {
	}

	/**
	 * @param user
	 * @param cart
	 * @return
	 * @throws IlrFormatException
	 * @throws IlrSessionException
	 */
	public IlrSessionResponse executeRulesOnPojoRuleSession(Customer user, ShoppingCart cart) throws IlrFormatException, IlrSessionException  {
		IlrSessionFactory  pojoFactory = new IlrPOJOSessionFactory();
		IlrSessionRequest sessionRequest = pojoFactory.createRequest();
	    sessionRequest.setRulesetPath(IlrPath.parsePath(rulesetPath));
	    sessionRequest.setForceUptodate(true);
	    // Enable trace to retrieve infos on executed rules
	    sessionRequest.setTraceEnabled(true);
	    sessionRequest.getTraceFilter().setInfoAllFilters(true);
	    // Set the input parameters for the execution of the rules
	    Map<String,Object> inputParameters = sessionRequest.getInputParameters();
		 Announcement announcement =  new Announcement();
		 inputParameters.put("announcement", announcement);
	    inputParameters.put("customer", user);
	    inputParameters.put("shoppingCart", cart);
	    // Create a stateless rule session
	    IlrStatelessSession statelessSession = pojoFactory.createStatelessSession();
	    // Execute the rules and returns the response
	    return statelessSession.execute(sessionRequest);
	}


	/**
	 *
	 * @param trace
	 * @return the list of rules executed
	 */
	public static List<String> getRulesExecuted (IlrExecutionTrace trace) {
		IlrBusinessExecutionTrace execResult = new IlrBusinessExecutionTrace(trace);
		return execResult.getRuleFiredBusinessNames();
	}
}
