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
 * Class representing a shopping cart item.
 * The main attributes are:
 *
 * type: Type of the object
 * name: Description of the object
 * price: price of the object
 *
 */
public class Item implements Serializable {

	
	private static final long serialVersionUID = 8959055624611113074L;
	public ItemType type;
	public double price;
	public String name;

	/**
	 * Creates a new Item from its type, price and description.
	 */
	public Item(ItemType arg1, double arg2,String arg3) {
		type = arg1;
		price = arg2;
		name = arg3;
	}

   /**
    * Gets the item type.
    * @see shopmodel.ItemType
    */
	public ItemType getType(){return type;}

   /**
    * Gets the item price.
	*/
	public double getPrice() {return price;}

   /**
    * Gets the item description.
	*/
	public String getName(){return name;}


   /**
    * Arbitrary value to specify whether an item is expensive or not.
    * By default, an item is expensive if it costs more than $100.
	*/
	public boolean isExpensive() {
		return (price > 100);
	}

}
