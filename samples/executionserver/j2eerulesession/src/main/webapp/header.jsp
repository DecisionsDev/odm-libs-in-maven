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
<html>
<head>
<LINK href="css/ilog.css" rel="stylesheet" type="text/css">

<%// Ask the browser not to cache the page
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");

			%>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Java EE Rule Session Sample</title>
</head>
<body>
 
<table width="100%" height="95" cellspacing="0" cellpadding="0"
       background="images/bg_header.jpg" class="tab_group">
	<tbody>
		<tr>
		  <td nowrap="nowrap" background="images/bg_header.jpg">
			<table class="tab_group" cellspacing="0" cellpadding="0">
				<tr>

			<td valign="middle" class="site-title" nowrap="nowrap" background="images/title_left.png">Java EE Rule Session Sample<br />
			</td>
			<td><img src="images/title_right.jpg" /></td>
			<td align="right" width="100%"><img src="images/IBM_1px_white.gif" /></td>
				</tr>
			</table>
		    </td>

		   <td nowrap="nowrap" valign="middle" align="right"></td>
		</tr>

		<tr>
			<td colspan="3" align="right" height="25" valign="middle">&nbsp;</td>
		</tr>
		<tr>
			<td colspan="3">
			<table border="0" cellpadding="0" cellspacing="0" width="100%">
				<tbody>
					<tr>
						<td>
						<table class="tab_group" cellpadding="0" cellspacing="0"
							height="100%">
							<tbody>
								<% 
								String execution = "";
								String version = (String)session.getAttribute("EJB_VERSION");
								execution = "execution_tomcat.jsp";	
								if ( "3".equalsIgnoreCase (version) ) {
									execution = "execution.jsp";
								}
								String prefix = "";
			if (request.getParameter("show").equalsIgnoreCase("ruleapp") == false) {
				prefix = "un";
			}%>
								<tr class="tab_group">
									<td><img src="images/tab_start.jpg" alt=""></td>
									<td><img src="images/tab_<%=prefix %>selected_start.jpg" alt=""></td>
									<td class="tab_selected"
										background="images/tab_<%=prefix %>selected.jpg"><a
                                            href="index.jsp" class="tab_<%=prefix %>selected">Explorer
									</a></td>
									<%prefix = "";
			if (request.getParameter("show").equalsIgnoreCase("ruleapp") == true) {		
				%>
									<td><img src="images/tab_<%=prefix %>selected_left.jpg" alt=""></td>

									<%	prefix = "un"; } else {

			%>
									<td><img src="images/selected_between.jpg" alt="" /></td>
									<%}

			%>

									<td background="images/tab_<%=prefix %>selected.jpg"><a href="<%=execution%>"  id="executionTab" class="tab_<%=prefix %>selected">Execution</a></td>

									<td><img src="images/tab_<%=prefix %>selected_end.jpg" alt=""></td>
								</tr>

							</tbody>
						</table>
						</td>
						<td class="log-style" align="right">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="4" background="images/bg_white_bar.jpg" height="21">
						&nbsp;&nbsp;</td>

					</tr>
				</tbody>
			</table>
			</td>
		</tr>
	</tbody>
</table>
