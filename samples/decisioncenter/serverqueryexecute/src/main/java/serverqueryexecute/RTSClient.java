/*
* Copyright 2020 IBM
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.IBM Confidential
*/
package serverqueryexecute;

import ilog.rules.teamserver.brm.IlrBrmPackage;
import ilog.rules.teamserver.brm.IlrRuleProject;
import ilog.rules.teamserver.brm.IlrBaseline;
import ilog.rules.teamserver.model.IlrDefaultSearchCriteria;
import ilog.rules.teamserver.model.IlrElementSummary;
import ilog.rules.teamserver.model.IlrSession;
import ilog.rules.teamserver.model.IlrSessionFactory;
import ilog.rules.teamserver.model.IlrSessionHelper;
import ilog.rules.teamserver.client.IlrRemoteSessionFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class RTSClient {
	/** Provider URL (http://hostname:9080/decisioncenter). */
	protected String serverUrl;
	/** Datasource name (jdbc/ilogDataSource, ...). */
	protected String datasource;
	/** User login. */
	protected String login;
	/** User password. */
	protected String password;
	/** Project name. */
	protected String project;

	/** Rule Team Server session. */
	protected IlrSession session;

	protected IlrBrmPackage brmPackage;

	public RTSClient(String serverUrl, String datasource, String login, String password, String project) {
		this.serverUrl = serverUrl;
		this.datasource = datasource;
		this.login = login;
		this.password = password;
		this.project = project;
	}

	/**
	 * Initializes the Decision Center connection and opens the project specified in the <code>RTSClient</code> constructor.
	*/
	protected void init() throws Exception {
		// Connect to Rule Team Server
		IlrSessionFactory factory = new IlrRemoteSessionFactory();
		factory.connect(login, password, serverUrl, datasource);
		session = factory.getSession();
		session.beginUsage();
		brmPackage = session.getModelInfo().getBrmPackage();
		// Get the project by name
		IlrRuleProject ruleProject = (IlrRuleProject) IlrSessionHelper.getProjectNamed(session, project);
		// Open the current baseline
		IlrBaseline currentBaseline = IlrSessionHelper.getCurrentBaseline(session, ruleProject);
		session.setWorkingBaseline(currentBaseline);
	}

	/**
	 * Reads and executes a query from a file.
	 *
	 * @param queryFile The path to the text file.
	 */
	public void query(String queryFile) {
		try {
			// Load the query in a string buffer
			BufferedReader reader = new BufferedReader(new FileReader(queryFile));
			StringBuffer query = new StringBuffer();
			String line = reader.readLine();
			while(line != null) {
				query.append(line);
				line = reader.readLine();
				if (line != null)
					query.append("\n");
			}
			reader.close();
			// Initialize the Decision Center connection
			init();
			System.out.println("Executing query:\n" + query.toString());
			// Execute the query
			IlrDefaultSearchCriteria criteria = new IlrDefaultSearchCriteria(query.toString());
			List<?> summaries = session.findElements(criteria);
			// Print the rules found
			System.out.println(summaries.size() + " results");
			if (summaries.size() == 0) {
				return;
			}
			for (int i = 0; i < summaries.size(); i++) {
				IlrElementSummary ruleSummary = (IlrElementSummary) summaries.get(i);
				String ruleName = ruleSummary.getName();
				System.out.println("\t" + ruleName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		    session.endUsage();
		}
	}

	public static void main(String args[]) {
		try {
			String serverUrl = args[0];
			String datasource = args[1];
			String login = args[2];
			String password = args[3];
			String project = args[4];
			String queryFile = args[5];
			RTSClient rtsClient = new RTSClient(serverUrl, datasource, login, password, project);
			rtsClient.query(queryFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
