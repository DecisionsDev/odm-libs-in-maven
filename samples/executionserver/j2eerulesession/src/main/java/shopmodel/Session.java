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

public class Session implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Display a message
	 * @param msg
	 */
	public static void displayMessage (String msg) {
		System.out.println(msg);
		System.out.flush();
	}

	/**
	 * @return True if the current day is Sunday
	 */
	public static boolean isItSunday () {
		java.util.Calendar now = java.util.Calendar.getInstance();
		return now.get(java.util.Calendar.DAY_OF_WEEK)==java.util.Calendar.SUNDAY;
	}
}
