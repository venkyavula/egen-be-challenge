package com.egen.model;

import java.io.Serializable;

public class Company implements Serializable{

private String name;
private String website;

/**
* 
* @return
* The name
*/
public String getName() {
return name;
}

/**
* 
* @param name
* The name
*/
public void setName(String name) {
this.name = name;
}

/**
* 
* @return
* The website
*/
public String getWebsite() {
return website;
}

/**
* 
* @param website
* The website
*/
public void setWebsite(String website) {
this.website = website;
}

}

