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
import java.util.Enumeration;
import java.util.Vector;

/**
 * Defines a Shopping Cart with the following attributes
 *
 * discount: the current percentage value of the discount that will be applied
 * contents: list of the items in the shopping cart
 *
 */
public class ShoppingCart implements Serializable {

	
	private static final long serialVersionUID = -5798838609910270002L;
	private Customer customer;
	private float discount = 0;
	private Vector<Item> contents = new Vector<Item>();


	public boolean isActive() {
		return true;
	}



   /**
    * Gets the discount applied to the shopping cart
    */
	public float getDiscount(){return discount;}

   /**
    * Returns the items of the shopping cart as a vector
    */
	public Vector<Item> getContents() {
		return contents;
	}

   /**
    * Adds an item to the shopping cart
    */
	public void addItem(Item item) {
		contents.addElement(item);
	}

   /**
    * Returns the shopping cart current value (discount included)
    */
	public double getValue() {
		Enumeration<Item> it = contents.elements();
		double value = 0;
		while (it.hasMoreElements()) {
			Item item = (Item)it.nextElement();
			value = value + item.price * (1 - discount/100);
		}
		return value;
	}

   /**
    * Returns the number of items in the shopping cart
    */
	public int numberOfItems() {
		return contents.size();
	}

   /**
    * Returns true if the number of items is between min and max
    */
	public boolean containsItemsInRange(int min, int max) {
		int nitems = numberOfItems();
		return (nitems >= min) && (nitems <= max);
	}

   /**
    * Returns true if the shopping cart value is above $10000
    */
	public boolean isFull() {
		return getValue() >= 10000;
	}

   /**
    * Applies a discount
    */
  public void applyDiscount(float arg) {
    discount += arg;
  }

   /**
    * Other function to  apply discount
    */
	public void applyDiscountFromRule(float arg, String name) {
		System.out.println("Applying a discount of " + arg
											 + "% from rule: " + name);
		discount += arg;
	}

   /**
    * Returns the value of items of a certain type in the shopping cart
    */
	public double itemPurchaseOf(ItemType itemType) {
		Enumeration<Item> it = contents.elements();
		double value = 0;
		while (it.hasMoreElements()) {
			Item item = (Item)it.nextElement();
			if (item.type == itemType) {
				value = value + item.price;
			}
		}
		return value;
	}

   /**
    * Detects purchase orders of a certain amount for a certain item type
    */
	public boolean containsMoreThan(ItemType item, double amount) {
		double itemAmount = itemPurchaseOf(item);
		return itemAmount > amount;
	}

   /**
    * Clears the shopping cart and removes all the items it contains
    */
	public void empty() {
		contents.clear();
	}

   /**
    * Checks if the shopping cart value belongs to a specific price interval [arg1;arg2]
    */
	public boolean valueIsBetween(double arg1, double arg2) {
		double value = getValue();
		return (value >= arg1) && (value <= arg2);
	}
/*
	public void displayContents(int type) {
		System.out.println("The contents is: ");
		Enumeration it = contents.elements();
		while (it.hasMoreElements()) {
			Item item = (Item)it.nextElement();
			switch (type) {
				case IMAGE: System.out.println(item.getImageFile());
										break;
				case TEXT: System.out.println(item.name);
									 break;
			}
		}
	}
*/
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Customer getCustomer() {
		return customer;
	}


 
}
