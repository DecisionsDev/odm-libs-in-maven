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
<% 
	String headerpage = "header.jsp";
	String version = (String)session.getAttribute("EJB_VERSION");
	System.out.println( "version: " + version);
	if ("TOMCAT".equalsIgnoreCase(version)) {
		headerpage = "header_tomcat.jsp";
	}
%> 

<jsp:include page="<%=headerpage %>" flush="true">
	<jsp:param name="show" value="ruleapp" />
</jsp:include>

<table id="loginContent" cellspacing="0" width="100%">

	<tbody>
		<tr>
			<td>
			<form id="loginForm" action="j_security_check" method="POST">
			</td>
		</tr>
		<tr>
			<td>
			<table class="center" width="100%">
				<tbody>
					<tr>
						<td>
						<table class="boxGrey" width="300">
							<thead>
								<tr>
									<th class="dialogHeader" colspan="1" scope="colgroup"><img
                                            src="images/login24.gif" alt="" /><span
										class="dialogTitle">Login</span></th>
								</tr>
							</thead>
							<tfoot>
								<tr>
									<td class="dialogFooter" colspan="1"><input id="okButton" type="submit"
										value="Ok" /></td>
								</tr>
							</tfoot>
							<tbody>

								<tr>
									<td class="centeredColumn"><span class="dialogMessage">Please
									provide your username/password for managing ruleapp.</span></td>
								</tr>
								<tr>
									<td class="centeredColumn">
									<table>
										<tbody>
											<tr>
												<td><label> Username</label></td>
												<td><input type="text" id="j_username" name="j_username" value="resAdmin" /></td>
												<td></td>
											</tr>
											<tr>
												<td><label> Password</label></td>

												<td><input type="password" id="j_password" name="j_password" value="resAdmin"/></td>
												<td></td>
											</tr>
										</tbody>
									</table>
									</td>
								</tr>
							</tbody>
						</table>
						</td>
					</tr>
				</tbody>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			</form>
			</td>

		</tr>
	</tbody>
</table>
<%@ include file="footer.jsp"%>
