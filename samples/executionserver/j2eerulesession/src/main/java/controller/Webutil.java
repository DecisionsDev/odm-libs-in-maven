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

import java.text.NumberFormat;

/**
 * Utility class used in the web part.
 *
 */
public class Webutil {

	// 
	/**
	 * Utility function to correctly format shopping cart numbers in HTML.
	 * @param d Double to format.
	 * @return The String representation.
	 */
	public static String getFormattedValue(double d) {
		NumberFormat f = NumberFormat.getInstance();
		f.setMaximumFractionDigits(2);
		String temp = "";
		try {
			temp = f.format(d);
		} catch (IllegalArgumentException e) {
		}
		return temp;
	}

}
