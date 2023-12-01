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
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is an iterator that moves through tweets in reverse chronological order by high like ratio
 * only
 * 
 * @author sebas
 *
 */
public class RatioTwiterator implements Iterator<Tweet> {

  private TweetNode next;
  private final double THRESHOLD;

  /**
   * Constructs a new twiterator at the given starting node
   * 
   * @param startNode the node to begin the iteration at
   * @param threshold he minimum threshold value for the ratio of likes to total engagement, assumed
   *                  to be between 0.0 and 1.0 thanks to range checking in Timeline
   */
  public RatioTwiterator(TweetNode startNode, double threshold) {
    this.next = startNode;
    this.THRESHOLD = threshold;
  }

  /**
   * Checks whether there is a next tweet to return
   * 
   * @return true if there is a next tweet, false if the value of next is null
   */
  public boolean hasNext() {
    return findNext() != null;
  }

  /**
   * Returns the next tweet in the iteration if one exists, and advances next to the next tweet with
   * a likes ratio in excess of the given threshold
   * 
   * @return the next tweet in iteration
   * @throws NoSuchElementException - if there is not a next tweet to return
   */
  public Tweet next() throws NoSuchElementException {
    TweetNode current = findNext();
    if (current == null) {
      throw new NoSuchElementException("next does not exist");
    }
    next = current.getNext();
    return current.getTweet();
  }

  /**
   * helper method for getting next high enough of ratio
   * 
   * @return
   */
  private TweetNode findNext() {
    while (next != null && next.getTweet().getLikesRatio() < THRESHOLD) {
      next = next.getNext();
    }
    return next;
  }
}
