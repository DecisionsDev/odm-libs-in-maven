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

import java.io.InputStream;

public interface RuleappManager {
	public byte[] getRulesetContents(InputStream ireader);
	public void create() ;
	public boolean isAvailable() ;
	public void setRuleset(byte[] rulesetArchiveAsByteArray);
}
