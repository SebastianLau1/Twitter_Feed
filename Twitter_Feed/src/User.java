//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Twitter feed
// Course: CS 300 Spring 2023
//
// Author: Sebastian Lau
// Email: sglau@wisc.edu
// Lecturer: (Mouna Kacem 
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: (name of your pair programming partner)
// Partner Email: (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE (identify each by name and describe how they helped)
// Online Sources: NONE (identify each by URL and describe how it helped)
//
///////////////////////////////////////////////////////////////////////////////
/**
 * This data type models a Twitter user.
 * @author sebas
 *
 */
public class User {
  private boolean isVerified;
  private String username;

/**
 * Constructs a new User object with a given username. All Users are unverified by default.
 * @param username
 * @throws IllegalArgumentException
 */
  public User(String username) throws IllegalArgumentException{
    if(username==null || username==""||username.contains("*")) {
      throw new IllegalArgumentException("username cannot be empty, null, or contain *");
    }
    isVerified = false;
    this.username = username;

  }
  /**
   * Accesses the username of this User
   * @return the username this User tweets under
   */
  public String getUsername() {
    return username;
  }
  /**
   * Determines whether the User is a verified user or not
   * @return true if this User is verified
   */
  public boolean isVerified() {
    return isVerified;
  }
  /**
   * Gives this User an important-looking asterisk
   */
  public void verify() {
    isVerified =true;
  }
  /**
   * Takes this User's important-looking asterisk away
   */
  public void revokeVerification() {
    isVerified = false;
  }
  
  /**
   *Creates a String representation of this User for display. For example, if this User's username 
   *is "uwmadison" and they are verified, this method will return "@uwmadison*"; if this User's
   * username is "dril" and they are not verified, this method will return "@dril" 
   * (with no asterisk).
   * @return a String representation of this User as described above
   * @override 
   */
  public String toString() {
    if(isVerified) {
      return "@" + username + "*";
    }else return "@" + username;
  }
  }

