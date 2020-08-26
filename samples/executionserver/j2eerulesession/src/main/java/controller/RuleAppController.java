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

import ilog.rules.res.model.IlrAlreadyExistException;
import ilog.rules.res.model.IlrEngineType;
import ilog.rules.res.model.IlrFormatException;
import ilog.rules.res.model.IlrMutableRepository;
import ilog.rules.res.model.IlrMutableRuleAppInformation;
import ilog.rules.res.model.IlrMutableRulesetArchiveInformation;
import ilog.rules.res.model.IlrRepositoryFactory;
import ilog.rules.res.model.IlrVersion;
import ilog.rules.res.session.IlrPOJOSessionFactory;
import ilog.rules.res.session.IlrSessionFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 * This class is used to manage the ruleapp archive.
 */
public class RuleAppController implements RuleappManager,Serializable {

    private static final long serialVersionUID = -4882429548145412189L;

    public static final String RULEAPPNAME = "JEERulesessionSample";

    public static final String RULEAPPVER = "1.0";

    public static final String RULESETNAME = "JEERulesessionSampleRS";

    public static final String RULESETVER = "1.0";

    private transient IlrMutableRepository  REPOSITORY = null;
    private transient IlrRepositoryFactory  REPOSITORYFACTORY = null;

    public RuleAppController() {
    }

    /**
     * Get the repository for the Rule Engine Server management model. The model of
     * the Rule Engine Server allows you to create, list, and remove the various
     * management entities. It also provides some facilities for processing and
     * retrieving RuleApp Archives. IlrMutableRepository provides the API to the model.
     * 
     * @return Model repository.
     */
    private IlrMutableRepository getRepository() {
	if (REPOSITORY == null) 
	    createRepository();
	return REPOSITORY;
    }
    /**
     * Get repository for the Rule Engine Server management model. 
     *
     * @return Model repository factory.
     */
    private IlrRepositoryFactory getRepositoryFactory() {
	if (REPOSITORYFACTORY == null) 
	    createRepository();
	return REPOSITORYFACTORY;
    }
    /**
     * Create the repository factory and the repository,
     */
    private void createRepository() {
	try {
	    if (REPOSITORY == null) {
		// get one session factory
		IlrSessionFactory sessionFactory = new IlrPOJOSessionFactory();
		REPOSITORYFACTORY = sessionFactory.createManagementSession().getRepositoryFactory();
		REPOSITORY = REPOSITORYFACTORY.createRepository();
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    /**
     * Test if the rules application has been deployed.
     * 
     * @return True if deployed. False otherwise.
     */
    public boolean isAvailable() {
	try {
	    IlrMutableRuleAppInformation ruleApp =
		getRepository().getRuleApp(RULEAPPNAME,
					   IlrVersion.parseVersion(RULEAPPVER));
	    if (ruleApp != null) {
		IlrMutableRulesetArchiveInformation ruleset =
		    ruleApp.getRuleset(RULESETNAME,
				       IlrVersion.parseVersion(RULESETVER));
		if (ruleset != null)
		    return true;
	    }
	} catch (IlrFormatException e) {
	    e.printStackTrace();
	}
	return false;
    }

    /**
     * Create the ruleapp application if it isn't already deployed.
     */
    public void create() {
	try {
	    if (!isAvailable()) {
		IlrMutableRuleAppInformation ruleAppObjectName = null;
		try {
		    ruleAppObjectName = getRepositoryFactory().createRuleApp(RULEAPPNAME,IlrVersion.parseVersion(RULEAPPVER));
		} catch (IlrFormatException e) {
		    e.printStackTrace();
		}
		getRepository().addRuleApp(ruleAppObjectName);
	    }
	} catch (IlrAlreadyExistException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Update the ruleset archive.
     * 
     * @param rulesetArchive
     *            The ruleset archive.
     */
    public void setRuleset(byte[] rulesetArchiveAsByteArray) {
	try {
	    IlrMutableRuleAppInformation ruleApp =
		getRepository().getRuleApp(RULEAPPNAME, IlrVersion.parseVersion(RULEAPPVER));
	    if (ruleApp != null) {
		IlrMutableRulesetArchiveInformation ruleset =
		    ruleApp.getRuleset(RULESETNAME, IlrVersion.parseVersion(RULESETVER));
		ByteArrayInputStream inputStream =
		    new ByteArrayInputStream(rulesetArchiveAsByteArray);
		try {
		    boolean newRuleset = false;
		    if (ruleset == null) {
			newRuleset = true;
			// create the rule set in the rule app
			ruleset = getRepositoryFactory().createRuleset(RULESETNAME, IlrVersion.parseVersion(RULESETVER));
		    }
		    // associate the archive to it
		    ruleset.setRESRulesetArchive(IlrEngineType.CRE, inputStream);
		    if (newRuleset)
			ruleApp.addRuleset(ruleset);
		} catch (IlrAlreadyExistException e1) {
		    e1.printStackTrace();
		}
	    }
	}	catch (IlrFormatException e) {
	    e.printStackTrace();
	}
    }

	/**
	 * Utility target that get the ruleset archive stored in the war file.
	 * 
	 * @return The rulesset archive byte array.
	 */
	public byte[] getRulesetContents(InputStream ireader) {
	    ByteArrayOutputStream output = new ByteArrayOutputStream();
	    byte[] buffer = new byte[1024];
	    int i = 0;
	    try {
		while ((i = ireader.read(buffer)) != -1) {
		    output.write(buffer, 0, i);
		}
		ireader.close();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    return output.toByteArray();
	}

    }
