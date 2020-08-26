/*
* Licensed Materials - Property of IBM
* 5725-B69 5655-Y17 5655-Y31 5724-X98 5724-Y15 5655-V82 
* Copyright IBM Corp. 1987, 2016. All Rights Reserved.
*
* Note to U.S. Government Users Restricted Rights: 
* Use, duplication or disclosure restricted by GSA ADP Schedule 
* Contract with IBM Corp.
*/

package shopmodel;

import java.io.Serializable;

public class Announcement implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	StringBuffer sb = new StringBuffer ();

	/**
	 *
	 */
	public Announcement () {
		super ();
	}

	/**
	 * @param msg
	 */
	public void addMessage (String msg) {
		sb.append(msg);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString () {
		return sb.toString();
	}

	/**
	 * @return the sb
	 */
	public StringBuffer getBuffer() {
		return sb;
	}



}
