/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

/**
 *
 * @author Cailean
 */
public abstract class User {
    
    private int userID;
    private String email;
    private String password;
    private int role;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private int gender;
    private String contactNumber;
  

    public int getUserID() {return userID;}
    public void setUserID(int userID) {this.userID = userID;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public int getRole() {return role;}
    public void setRole(int role) {this.role = role;}
    //public User_details getDetails() {return details;	}
    //public void setDetails(User_details details) {this.details = details;}
    public String getFirstName() {return firstName;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public String getDateOfBirth() {return dateOfBirth;}
    public void setDateOfBirth(String dateOfBirth) {this.dateOfBirth = dateOfBirth;}
    public int getGender() {return gender;}
    public void setGender(int gender) {this.gender = gender;}
    public String getContactNumber() {return contactNumber;}
    public void setContactNumber(String contactNumber) {this.contactNumber = contactNumber;}

    
   
}
