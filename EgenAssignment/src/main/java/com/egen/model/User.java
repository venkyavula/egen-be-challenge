package com.egen.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
import com.mongodb.BasicDBObject;
/**
 * This is Model class for given input.		
 * @author VENKY
 */


@XmlRootElement(name = "userdata")
public class User extends BasicDBObject implements Serializable {
 
private static final long serialVersionUID = -7589576152951744708L;
private String id;
private String firstName;
private String lastName;
private String email;
private Address address;
private String dateCreated;
private Company company;
private String profilePic;

/**
* 
* @return
* The id
*/
public String getId() {
return id;
}

/**
* 
* @param id
* The id
*/
public void setId(String id) {
this.id = id;
}

/**
* 
* @return
* The firstName
*/
public String getFirstName() {
return firstName;
}

/**
* 
* @param firstName
* The firstName
*/
public void setFirstName(String firstName) {
this.firstName = firstName;
}

/**
* 
* @return
* The lastName
*/
public String getLastName() {
return lastName;
}

/**
* 
* @param lastName
* The lastName
*/
public void setLastName(String lastName) {
this.lastName = lastName;
}

/**
* 
* @return
* The email
*/
public String getEmail() {
return email;
}

/**
* 
* @param email
* The email
*/
public void setEmail(String email) {
this.email = email;
}

/**
* 
* @return
* The address
*/
public Address getAddress() {
return address;
}

/**
* 
* @param address
* The address
*/
public void setAddress(Address address) {
this.address = address;
}

/**
* 
* @return
* The dateCreated
*/
public String getDateCreated() {
return dateCreated;
}

/**
* 
* @param dateCreated
* The dateCreated
*/
public void setDateCreated(String dateCreated) {
this.dateCreated = dateCreated;
}

/**
* 
* @return
* The company
*/
public Company getCompany() {
return company;
}

/**
* 
* @param company
* The company
*/
public void setCompany(Company company) {
this.company = company;
}

/**
* 
* @return
* The profilePic
*/
public String getProfilePic() {
return profilePic;
}

/**
* 
* @param profilePic
* The profilePic
*/
public void setProfilePic(String profilePic) {
this.profilePic = profilePic;
}

}