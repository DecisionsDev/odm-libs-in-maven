<%
// Copyright 2020 IBM
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.IBM Confidential
%> 
<%@ page language="java"%>
<%@ page
	import="javax.naming.*,java.rmi.*,java.net.*,java.io.*,javax.rmi.*,java.util.*,controller.*"
	session="true"%>
<jsp:include page="header.jsp" flush="true">
	<jsp:param name="show" value="ruleapp" />
</jsp:include>
<form name="RuleSet" method="POST">
<p class="panelTitle"> Rulesets View </p>
<p class="panelText"> This sample uses the Rule Execution Server repository API to show and deploy rulesets.
</p>
<table class="greybox">
	<tr class="noborder">
		<th class="noborder" align="center">Title</th>
		<th class="noborder" colspan="2" align="center">Description</th>
	</tr>

	<tr class="noborder">
		<td class="noborder" valign="top"><strong>balSample</strong></td>
		<td class="noborder">15% discount on the shopping cart.<br>
		Platinum category when the purchase is over $1,500</td>
		<td class="noborder" align="right">
		<table class="noborder">
			<tr class="noborder" align="right">
				<td class="noborder"><a href="data/balsample.html"><img border="0"
                                                                        src="images/view_button.png" alt="View BAL Rules" title="View BAL Rules"></a></td>
			</tr>
			<tr class="noborder" align="right">
				<td class="noborder"><a href="index.jsp?update=balsample"><img
                        border="0" id="deployButton" src="images/deploy_button.png" alt="Deploy ruleset"
                        title="Deploy ruleset"></a></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr class="noborder">
		<td class="noborder" valign="top"><strong>balSample2</strong></td>
		<td class="noborder">25% discount for American customers<br>
		Platinum category when the purchase is over $1,200</td>
		<td class="noborder" align="right">
		<table class="noborder">
			<tr class="noborder" align="right">
				<td class="noborder"><a href="data/balsample2.html"><img border="0"
                                                                         src="images/view_button.png" alt="View BAL Rules"
                                                                         title="View BAL Rules"></a></td>
			</tr> 
			<tr class="noborder" align="right">
				<td class="noborder"><a href="index.jsp?update=balsample2"><img
                        border="0" src="images/deploy_button.png" alt="Deploy ruleset"
                        title="Deploy ruleset"></a></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
<!--  Deploy the ruleset selected -->
<%String rulesetFile = request.getParameter("update");
			// Update specified
			if (rulesetFile != null) {
				// Ruleset does not exist, so create it.
				RuleappManager controller = (RuleappManager) session
						.getAttribute(ComponentSessionListener.RULEAPPSESSIONNAME);
				// 1/2: get the rules as jar
				byte[] rulesetArchive = controller
						.getRulesetContents(getServletConfig()
								.getServletContext().getResourceAsStream(
										"/data/" + rulesetFile + ".jar"));
				if (!controller.isAvailable()) {
					controller.create();
					out.print("This sample is running for the first time: the RuleApp is being created and deployed.<br/>");
				}

				// 2/2: now store the ruleset in the database
				try {
					controller.setRuleset(rulesetArchive);
					out
							.print("The "
									+ rulesetFile
									+ " ruleset has been stored successfully! To see the BAL rules click <a href=\"data/"
									+ rulesetFile + ".html\">here</a>.");
				} catch (Exception e) {
					out.print("<b>Ruleset update failed: " + e.getMessage()
							+ "</b>");
					e.printStackTrace();
				}
			}

		%>
<%@ include file="footer.jsp"%>
