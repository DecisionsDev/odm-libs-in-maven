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
<%@ page import="controller.RuleExecutionController"%>
<%@ page import="controller.ComponentSessionListener"%>

<%@ page import="controller.Webutil"%>
<%@ page import="shopmodel.*"%>
<%@ page import="ilog.rules.res.session.*"%>
<%@ page import="ilog.rules.res.session.ruleset.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<jsp:include page="header.jsp" flush="true">
	<jsp:param name="show" value="execution" />
</jsp:include>
<table>
	<tr>

		<td valign="top">

		<table valign="top">
			<tr>
				<td><jsp:include page="parameters.jsp" flush="true" /></td>
			</tr>
			<tr>
				<td>
				<form method="post">
				<table width="100%" class="border">

					<tr class="border">
						<td colspan="2">
						<h2>2 - Rules execution</h2>
						</td>
					</tr>

					<tr class="noborder">
						<td class="noborder" align="center"><strong>Java Rule Session</strong></td>
					</tr>
					<tr class="noborder">
						<td class="noborder" align="center"><a
								href="execution.jsp?mode=java" id="java_mode"><img border="0"
																				   src="images/submit_button.gif" alt="Execute Ruleset in Java Mode"></a></td>
					</tr>
					<tr class="noborder">
						<td class="noborder" align="center"><strong>Execution Report</strong></td>
					</tr>


				</table>
				</form>
				<%
							String mode = request.getParameter("mode");
							if (mode != null) {

						// Initialize the ruleset parameters.
						// The customer
						Customer user = new Customer();
						user.setAge(30);
						user.setCountry("Usa");
						user.setPreferredItemType(ItemType.CD);
						user.setCategory("Gold");
						// The shopping cart
						ShoppingCart cart = new ShoppingCart();
						cart.addItem(new Item(ItemType.CD, 200, "Expensive CD"));
						cart.addItem(new Item(ItemType.BOOK, 150,
						"IBM Decision Server Best Practices"));
						cart.addItem(new Item(ItemType.DVD, 1000,
						"Very Costly Compilation"));
						double expectedValue = cart.getValue();

						
						IlrExecutionTrace trace = null;
						StringBuffer sresult = new StringBuffer();
						StringBuffer straceresult = new StringBuffer();
						RuleExecutionController controller = (RuleExecutionController) session.getAttribute(ComponentSessionListener.RULEEXECUTIONSESSIONNAME);
						 
						try {
							if (mode.equalsIgnoreCase("java")){
								// Pojo with execution report.
									IlrSessionResponse sessionResponse = controller.executeRulesOnPojoRuleSession(user,cart);
									trace = sessionResponse.getRulesetExecutionTrace();
									sresult = ((Announcement) sessionResponse.getOutputParameters().get("announcement")).getBuffer();
							} 
							if (trace!=null) {
								straceresult.append("<b>" + trace.getTotalRulesFired() + " rules fired </b>");
								List<String> rules = RuleExecutionController.getRulesExecuted(trace);
								if (rules!=null) {
									for (int i = 0; i < rules.size (); i++) {
										String rule = rules.get(i);
										straceresult.append("<li>" + rule + "</li>");
									}
								}
							}
						} catch (Exception e) {
							sresult.append("<b><font color='red'>An error occurred during rule execution.</font></b>"+e.toString());
						}
				%></td>
			</tr>
			<tr>
				<td>
				<table width="100%" class="border">
					<tr class="border">
						<td>
						<h2>3 - Get the results</h2>
						</td>
					</tr>
					<tr class="noborder">
						<td><font class="undercolor">Execution Mode :</font><b><%=mode.toUpperCase()%></b></td>
                                        </tr>
					<tr class="noborder">
						<td><font class="undercolor">Execution Results :</font>
						<ul>
							<li><b>Message:</b> <%=sresult.toString()%></li>
							<li><b>Normal Price: </b>$<%=Webutil.getFormattedValue(expectedValue)%></li>
							<li><b>Discount:</b> <%=Webutil.getFormattedValue(cart.getDiscount())%>
							%</li>
							<li><b>Final Price:<font color="red">$<%=Webutil.getFormattedValue(cart.getValue())%></font></b></li>
							<li><b>Execution Report :</b> <%=straceresult.toString()%></li>
							 
						</ul>
						</td>
					</tr>
 
						</ul>

						</ul>
						</td>
					</tr>
				</table>

				</td>
			</tr>
			<%}

		%>

		</table>

		</td>
	</tr>
</table>

<%@ include file="footer.jsp"%>
