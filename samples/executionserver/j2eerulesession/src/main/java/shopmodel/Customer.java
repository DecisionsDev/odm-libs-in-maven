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
 * Defines a Customer with the following attributes
 *
 * Age
 * Name
 * Country: the customer's home country
 * Category: can be "Silver, "Gold" or "Platinum"
 * PreviousBuy: last purchase value
 * isFirstTimeCustomer: identifies a new Customer or not
 * preferredItemType: preferred item type in the online shop
 * previousItemType: previous item type
 *
 */
public class Customer implements Serializable {

  
	private static final long serialVersionUID = -8434911380754283530L;
public int age;
  public String name="";
  public String category="Silver";
  public String country="";
  public double previousBuy;
  public boolean isFirstTimeCustomer;
  public ItemType preferredItemType;
  public ItemType previousItemType;

  /**
   * Creates a new customer
   */
  public Customer() {
  }

  /**
   * Sets the age of the customer
   */
  public void setAge(int a) {age = a;}

  /**
   * Gets the age of the customer
   */
  public int getAge(){return age;}

  /**
   * Sets the name of the customer
   */
  public void setName(String n) { name = n;}

  /**
   * Gets the name of the customer
   */
  public String getName(){return name;}

  /**
   * Sets the category of the customer
   */
  public void setCategory(String arg) {category = arg;}

  /**
   * Gets the category of the customer
   */
  public String getCategory() {return category;}

  /**
   * Sets the amount of the customer's previous purchase
   */
  public void setPreviousBuy(double d) {previousBuy = d;}

  /**
   * Gets the amount of the customer's previous purchase
   */
  public double getPreviousBuy() {return previousBuy;}

  /**
   * Specifies if the customer is older than a certain age
   */
  public boolean isOlderThan(int arg) {
    return age >= arg;
  }

  /**
   * Specifies if the customer is younger than a certain age
   */
  public boolean isYoungerThan(int arg) {
    return age <= arg;
  }

  /**
   * Checks if the customer lives in a specific country
   */
  public boolean livesIn(String arg) {
    return country.equals(arg);
  }

  /**
   * Sets the customer's preferred item type
   * @see shopmodel.ItemType
   */
  public void setPreferredItemType(ItemType t) {preferredItemType = t;}

  /**
   * Gets the customer's preferred item type
   * @see shopmodel.ItemType
   */
  public ItemType getPreferredItemType(){return preferredItemType;}

  /**
   * Sets the customer's home country
   */
  public void setCountry(String arg) {country = arg;}

  /**
   * Gets the customer's home country
   */
  public String getCountry() {return country;}

  /**
   * Sets the previous item type
   */
  public void setPreviousItemType (ItemType itemType) {
		this.previousItemType = itemType;
	}

  /**
   * Checks if the customer has already bought a specific item type
   */
  public boolean hasAlreadyBought(ItemType itemType) {
		return (itemType == previousItemType);
	}

}
