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
 * A container for a Tweet in a singly-linked list
 * @author sebas
 *
 */
public class TweetNode {
  private Tweet tweet;
  private TweetNode nextTweet;
/**
 * Constructs a singly-linked node containing a tweet
 * @param tweet - the tweet to put in this node
 * @param next - the next tweet in the linked list
 */
  public TweetNode(Tweet tweet, TweetNode next) {
    this.tweet = tweet;
    this.nextTweet = next;

  }
/**
 * Constructs a singly-linked node containing a tweet, with no successor tweet
 * @param tweet- the tweet to put in this node
 */
  public TweetNode(Tweet tweet) {
    this.tweet = tweet;
    this.nextTweet = null;
  }
/**
 * Accesses the tweet in this node
 * @return the tweet in this node
 */
  public Tweet getTweet() {
    return tweet;
  }
/**
 * Accesses the next TweetNode in the list
 * @return the successor TweetNode
 */
  public TweetNode getNext() {
    return nextTweet;
  }
/**
 * Links this node to another node
 * @param next - the successor TweetNode (can be null)
 */
  public void setNext(TweetNode next) {
    this.nextTweet = next;
  }
}
