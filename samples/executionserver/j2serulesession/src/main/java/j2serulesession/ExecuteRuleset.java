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

package j2serulesession;

import ilog.rules.res.model.IlrPath;
import ilog.rules.res.session.*;
import loan.Borrower;
import loan.LoanRequest;
import loan.Report;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ExecuteRuleset {
	private IlrJ2SESessionFactory factory;
	private IlrStatelessSession session;
	private String rulesetPath = "/test_deployment/loan_validation_with_score_and_grade";

	
	public void testRulesetWithSession() throws Exception {
		try {
			init();
			// Create a session request object
			IlrSessionRequest sessionRequest = factory.createRequest();
			sessionRequest.setRulesetPath(IlrPath.parsePath(rulesetPath));
			// Ensure latest version of the ruleset is taken into account
			sessionRequest.setForceUptodate(true);
			// Set the input parameters for the execution of the rules
			Map<String, Object> inputParameters = new HashMap<String, Object>();
			inputParameters.put("borrower", getBorrower());
			inputParameters.put("loan", getLoan());
			sessionRequest.setInputParameters(inputParameters);
			// Execute rules
			IlrSessionResponse sessionResponse = this.session.execute(sessionRequest);
			// Display the report
			Report report = (Report)(sessionResponse.getOutputParameters().get("report"));
			System.out.println(report.toString());
		} catch (IlrSessionException rse) {
			rse.printStackTrace();
		}
	}

	// Utilities
	
	protected void init() throws IOException {
		// Instantiate a loggerPrinter.
		FileOutputStream outFile = new FileOutputStream("execution.log");
		PrintWriter loggerPrinter = new PrintWriter(outFile);
		try {
			// Build a J2SE rule session factory that uses the loggerPrinter
			this.factory = new IlrJ2SESessionFactory(loggerPrinter);
			// Create the stateless rule session.
			this.session = factory.createStatelessSession();
		} catch (IlrSessionCreationException e) {
			
		}
	}
	
	public static void main(String args[]) throws Exception {
		ExecuteRuleset test = new ExecuteRuleset();
		test.testRulesetWithSession();
	}
	
	private Borrower getBorrower() {
		java.util.Date birthDate = loan.DateUtil.makeDate(1950, 1, 1);
		loan.Borrower borrower = new loan.Borrower("Smith", "John", birthDate,
				"123121234");
		borrower.setZipCode("12345");
		borrower.setCreditScore(200);
		borrower.setYearlyIncome(20000);
		return borrower;
	}

	private LoanRequest getLoan() {
		java.util.Date loanDate = new java.util.Date();
		loan.LoanRequest loan = new loan.LoanRequest(loanDate, 48, 100000, 1.2);
		return loan;
	}
}
