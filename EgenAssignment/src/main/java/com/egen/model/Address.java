package com.egen.model;

import java.io.Serializable;

public class Address implements Serializable{

private String street;
private String city;
private String zip;
private String state;
private String country;

/**
* 
* @return
* The street
*/
public String getStreet() {
return street;
}

/**
* 
* @param street
* The street
*/
public void setStreet(String street) {
this.street = street;
}

/**
* 
* @return
* The city
*/
public String getCity() {
return city;
}

/**
* 
* @param city
* The city
*/
public void setCity(String city) {
this.city = city;
}

/**
* 
* @return
* The zip
*/
public String getZip() {
return zip;
}

/**
* 
* @param zip
* The zip
*/
public void setZip(String zip) {
this.zip = zip;
}

/**
* 
* @return
* The state
*/
public String getState() {
return state;
}

/**
* 
* @param state
* The state
*/
public void setState(String state) {
this.state = state;
}

/**
* 
* @return
* The country
*/
public String getCountry() {
return country;
}

/**
* 
* @param country
* The country
*/
public void setCountry(String country) {
this.country = country;
}

}

