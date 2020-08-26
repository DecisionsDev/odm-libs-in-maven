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

/**
 * Class representing an item type. There are four item types provided:
 * 
 * CD Book DVD Game
 * 
 * The main attribute of an item type is its name.
 */
public class ItemType implements Serializable {

	private static final long serialVersionUID = -2918751889610165688L;

	/** The static public instance representing the CD type */
	public static final ItemType CD = new ItemType("CD");

	/** The static public instance representing the Book type */
	public static final ItemType BOOK = new ItemType("Book");

	/** The static public instance representing the DVD type */
	public static final ItemType DVD = new ItemType("DVD");

	/** The static public instance representing the Game type */
	public static final ItemType VIDEOGAME = new ItemType("Game");

	private String name;

	/** Private constructor(Factory design pattern) */
	private ItemType(String arg) {
		name = arg;
	}

	/**
	 * Returns a string representation of the item type: its name.
	 */
	public String toString() {
		return name;
	}
	/**
	 * Returns its name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * An item is of the music kind if its type is CD or DVD
	 */
	public boolean isMusic() {
	    return this.toString().equals(CD.toString()) || this.toString().equals(DVD.toString());
	}
    /**
     * Defines the equals method for the Remote EJB execution: objects are copied
     */
    public boolean equals(Object obj){
	if ( this == obj ) return true;
	if ( !(obj instanceof ItemType) ) return false;
	ItemType itType = (ItemType)obj;
	if (itType.getName() == null)
	    return (getName() == null);
	return (itType.getName().equals(getName()));
    }
	
}
