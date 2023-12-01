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
 * This is an iterator that moves through tweets in reverse 
 * chronological order by verified users only
 * @author sebas
 *
 */
public class VerifiedTwiterator implements Iterator<Tweet> {

    private TweetNode next;
    /**
     * Constructs a new twiterator at the given starting node
     * @param startNode
     */
    public VerifiedTwiterator(TweetNode startNode) {
        this.next = startNode;
    }
    /**
     * Checks whether there is a next tweet to return
     *@return true if there is a next tweet, false if the value of next is null
     */
    public boolean hasNext() {
        return next != null;
    }

   /**
    *  Returns the next tweet in the iteration if one exists, and advances next to the next
    *   tweet by a verified user
    *  @return the next tweet
    *  @throws NoSuchElementException - if there is not a next tweet to return
    */
    public Tweet next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("next does not exist");
        }
        TweetNode current = next;
        while (current != null && !current.getTweet().isUserVerified()) {
            current = current.getNext();
        }
        next = current != null ? current.getNext() : null;
        return current.getTweet();
    }
}
