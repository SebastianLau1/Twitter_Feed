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
 * This class models a reverse-chronological Twitter feed using a singly-linked list. By default,
 * new tweets are added at the head of the list. Note that while we CALL this "reverse
 * chronological" order, this is NOT enforced. You can add Tweets anywhere in the list relative to
 * each other, regardless of their respective timestamps.
 * 
 * @author sebas
 *
 */
public class TwitterFeed implements ListADT<Tweet>, Iterable<Tweet> {
  private TweetNode head;
  private TweetNode tail;
  private int size;
  private TimelineMode mode;
  private static double ratio = 0.5;

  /**
   * Default constructor; creates an empty TwitterFeed by setting all data fields to their default
   * values, and the timeline mode to CHRONOLOGICAL.
   */
  public TwitterFeed() {
    head = null;
    tail = null;
    size = 0;
    mode = TimelineMode.CHRONOLOGICAL;
  }

  /**
   * Accessor for the size of the feed
   * 
   * @return size of list
   */
  public int size() {
    return size;
  }

  /**
   * Determines whether this feed is empty.
   * 
   * @return true if list is empty
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Determines whether a given Tweet is present in the TwitterFeed.
   * 
   * @param findObject - the Tweet to search for
   * @return true if the Tweet is present, false otherwise
   */
  public boolean contains(Tweet findObject) {
    TweetNode current = head;
    while (current != null) {
      if (current.getTweet().equals(findObject)) {
        return true; // return true when tweet is found
      }
      current = current.getNext(); // gets next tweet in feed
    }
    return false;
  }

  /**
   * Accessor method for the index of a given Tweet in the TwitterFeed.
   * 
   * @param findObject - the Tweet to search for
   * @return the index of the Tweet in the TwitterFeed if present, -1 if not
   */
  public int indexOf(Tweet findObject) {
    TweetNode current = head;
    int index = 0;
    while (current != null) {
      if (current.getTweet().equals(findObject)) {
        return index; // return index when tweet is found
      }
      index++;
      current = current.getNext(); // gets next tweet in feed
    }
    return -1;
  }

  /**
   * Accessor method for the Tweet at a given index
   * 
   * @param index - the index of the Tweet in question
   * @return the Tweet object at that index
   * @throws IndexOutOfBoundsException -if index is out of bounds
   */
  public Tweet get(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("index not in list");
    }
    TweetNode current = head;
    for (int i = 0; i < index; i++) {
      current = current.getNext();
    }
    return current.getTweet();
  }

  /**
   * Accessor method for the first Tweet in the TwitterFeed
   * 
   * @return the Tweet object at the head of the linked list
   * @throws NoSuchElementException if list is empty
   */
  public Tweet getHead() throws NoSuchElementException {
    if (head == null) {
      throw new NoSuchElementException("head is null");
    }
    return head.getTweet();
  }

  /**
   * Accessor method for the last Tweet in the TwitterFeed
   * 
   * @return the Tweet object at the tail of the linked list
   * @throws NoSuchElementException if list is empty
   */
  public Tweet getTail() throws NoSuchElementException {

    if (tail == null) {
      throw new NoSuchElementException("tail is null");
    }
    return tail.getTweet();
  }

  /**
   * Adds the given Tweet to the tail of the linked list
   * 
   * @param newObject - the Tweet to add
   */
  public void addLast(Tweet newObject) {
    TweetNode newNode = new TweetNode(newObject);
    if (isEmpty()) {
      // if the list is empty
      head = newNode;
      tail = newNode;
    } else {
      // if the list has a node
      tail.setNext(newNode);
      tail = newNode;
    }
    size++;
  }

  /**
   * Adds the given Tweet to the head of the linked list
   * 
   * @param newObject - the Tweet to add
   */
  public void addFirst(Tweet newObject) {
    TweetNode newNode = new TweetNode(newObject);
    if (isEmpty()) {
      // if the list is empty
      head = newNode;
      tail = newNode;
    } else {
      // if the list has a node
      newNode.setNext(head);
      head = newNode;

    }
    size++;
  }

  /**
   * Adds the given Tweet to a specified position in the linked list
   * 
   * @param index     - the position at which to add the new Tweet
   * @param newObject - the Tweet to add
   * @throws IndexOutOfBoundsException - if the index is out of bounds
   */
  public void add(int index, Tweet newObject) throws IndexOutOfBoundsException {
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException("index is out of bounds");
    }
    if (index == 0) {
      addFirst(newObject); // adding at the head
    } else if (index == size) {
      addLast(newObject);// adding at the tail
    } else {
      TweetNode current = head;
      for (int i = 0; i < index - 1; i++) {
        current = current.getNext();
      }
      TweetNode newNode = new TweetNode(newObject, current.getNext());
      current.setNext(newNode);
      size++;
    }
  }

  /**
   * Removes and returns the Tweet at the given index
   * 
   * @param index - the position of the Tweet to remove
   * @return the Tweet object that was removed from the list
   * @throws IndexOutOfBoundsException - if the index is out of bounds
   */
  public Tweet delete(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException("Index out of range");
    }
    Tweet deleted;
    if (index == 0) { // if deleting head
      deleted = head.getTweet();
      if (size == 1) {
        tail = null;
      }
      head = head.getNext();

    } else if (index == size - 1) { // if deleting tail
      TweetNode current = head;
      for (int i = 0; i < index - 1; i++) {
        current = current.getNext();

      }
      deleted = tail.getTweet();
      tail = current;
      tail.setNext(null);
    } else {
      TweetNode current = head;
      for (int i = 0; i < index - 1; i++) {
        current = current.getNext();
      }
      deleted = current.getNext().getTweet();
      current.setNext(current.getNext().getNext());


    }
    size--;
    return deleted;
  }

  /**
   * Sets the iteration mode of this TwitterFeed
   * 
   * @param m
   */
  public void setMode(TimelineMode m) {
    this.mode = m;
  }

  /**
   * Creates and returns the correct type of iterator based on the current mode of this TwitterFeed
   * 
   * @return any of ChronoTwiterator, VerifiedTwiterator, or RatioTwiterator, initialized to the
   *         head of this TwitterFeed list
   */
  public Iterator<Tweet> iterator() {
    if (mode == TimelineMode.CHRONOLOGICAL) {
      return new ChronoTwiterator(this.head);
    } else if (mode == TimelineMode.VERIFIED_ONLY) {
      return new VerifiedTwiterator(this.head);
    } else if (mode == TimelineMode.LIKE_RATIO) {
      return new RatioTwiterator(this.head, ratio);
    } else {
      return null;
    }
  }

}
