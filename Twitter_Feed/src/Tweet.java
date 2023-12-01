//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Twitter feed
// Course: CS 300 Spring 2023
//
// Author: Sebastian Lau
// Email: sglau@wisc.edu
// Lecturer: Mouna Kacem 
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
import java.util.Date;
import java.util.Calendar;

/**
 * This data type models a tweet - a short text post made on the social media service Twitter
 * 
 * @author sebas
 *
 */
public class Tweet {
  private static Calendar dateGenerator;
  private static final int MAX_LENGTH = 280;
  private User user;
  private String text;
  private int numLikes;
  private int numRetweets;
  private Date timestamp;

  /**
   * Creates a fresh, new tweet by the given user. This tweet has no likes or retweets yet, and its
   * timestamp should be set to the current time.
   * 
   * @param user
   * @param text
   * @throws IllegalArgumentException
   * @throws NullPointerException
   * @throws IllegalStateException
   */
  public Tweet(User user, String text)
      throws IllegalArgumentException, NullPointerException, IllegalStateException {
    if (text.length() > MAX_LENGTH) {
      throw new IllegalArgumentException("tweet text is exceeds max length");
    }
    if (text == null || user == null) {
      throw new NullPointerException("tweet and username cannot be null");
    }
    if (dateGenerator == null) {
      throw new IllegalStateException("date has not been set");
    }
    this.user = user;
    this.text = text;
    this.numLikes = 0;
    this.numRetweets = 0;
    this.timestamp = dateGenerator.getTime();

  }

  /**
   * Initializes the dateGenerator static field to the provided Calendar object.
   * 
   * @param c- the Calendar to use for date generation for this run of the program
   */
  public static void setCalendar(Calendar c) {
    dateGenerator = c;
  }

  /**
   * Accesses the text of this tweet
   * 
   * @return the text of this tweet
   */
  public String getText() {
    return text;
  }

  /**
   * Gets the total engagement numbers (likes + retweets) of this tweet
   * 
   * @return the total engagement of this tweet
   */
  public int getTotalEngagement() {
    return numLikes + numRetweets;
  }

  /**
   * Checks whether the author of this tweet is a verified user
   * 
   * @return true if this tweet's User is verified, false otherwise
   */
  public boolean isUserVerified() {
    return user.isVerified();
  }

  /**
   * Returns the proportion of the total engagement that is composed of "likes". We only do
   * positive, uplifting ratios around here.
   * 
   * @return the ratio of likes to total engagement , as a value between 0.0 and 1.0, or -1 if the
   *         total engagement is 0.
   */
  public double getLikesRatio() {
    if (getTotalEngagement() == 0) {
      return -1;
    } else {
      return (double) numLikes / getTotalEngagement();
    }
  }

  /**
   * Add one (1) to the number of likes for this tweet
   */
  public void like() {
    numLikes += 1;
  }

  /**
   * Add one (1) to the number of retweets for this tweet
   */
  public void retweet() {
    numRetweets += 1;

  }

  /**
   * Compares the contents of this tweet to the provided object. If the provided object is a Tweet
   * that contains the same text posted at the same time by the same User.
   * 
   * @param o- the object to compare this Tweet to
   * @return true if this Tweet is equivalent to the provided object, false otherwise
   * @Override
   */
  public boolean equals(Object o) {
    if (!(o instanceof Tweet)) {
      return false; // checks if obj is tweet
    } else {
      Tweet oTweet = (Tweet) o; // checks if theyre equal
      if (this.user.toString().equals(oTweet.user.toString()) && this.text.equals(oTweet.text)
          && this.timestamp.equals(oTweet.timestamp)) {
        return true;
      } else
        return false;
    }
  }

  /**
   * A string representation of this tweet.
   * 
   * @return a formatted string representation of this tweet
   * @Override
   */
  public String toString() {
    return "tweet from " + user.toString() + " at " + timestamp + ":\n" + "-- " + text
        + "\n-- likes: " + numLikes + "\n-- retweets: " + numRetweets;
  }
}
