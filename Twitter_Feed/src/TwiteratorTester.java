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
import java.util.Calendar;
import java.util.Iterator;

/**
 * tester class for twitter feed
 * 
 * @author sebas
 *
 */
public class TwiteratorTester {
  /**
   * checks user class
   * 
   * @return true if class works correctly
   */
  public static boolean testUser() {
    // check that user inputs work
    try {
      User user1 = new User(null);
      System.out.println(
          "Test failed: expected IllegalArgumentException to be thrown " + "for null username.");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("Test passed");
    }

    try {
      User user2 = new User("");
      System.out.println(
          "Test failed: expected IllegalArgumentException to be thrown for" + " empty username.");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("Test passed");
    }

    try {
      User user3 = new User("s*");
      System.out.println("Test failed: expected IllegalArgumentException to be thrown "
          + "for username with asterisk.");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("Test passed");
    }

    User user4 = new User("bob");
    if (!user4.getUsername().equals("bob")) {
      System.out.println("Test failed: username returned incorrect value.");
      return false;
    } else {
      System.out.println("Test passed");
    }
    if (user4.isVerified()) {
      System.out.println("Test failed: isVerified getter returned incorrect value.");
      return false;
    } else {
      System.out.println("Test passed");
    }
    return true;
  }

  /**
   * checks tweet class
   * 
   * @return true if class works correctly
   */
  public static boolean testTweet() {
    // create a calendar and set it as the date generator for tweets
    Calendar test = Calendar.getInstance();
    test.set(2012, Calendar.MAY, 22, 15, 46, 03);
    Tweet.setCalendar(test);


    // create some users and tweets
    User user1 = new User("Seb");
    User user2 = new User("Bob");

    user1.verify();
    Tweet tweet1 = null;
    Tweet tweet2 = null;

    // check that the tweet inputs are valid
    try {
      tweet1 = new Tweet(user1, "fire");
      tweet2 = new Tweet(user2, "pole");
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      return false;

    } catch (NullPointerException e) {
      System.out.println(e.getMessage());
      return false;
    } catch (IllegalStateException e) {
      System.out.println(e.getMessage());
      return false;
    }

    // check tweet text and total engagement
    String actual;
    String expected;
    actual = tweet1.getText();
    expected = "fire";
    if (actual != expected)
      return false;

    int numActual;
    int numExpected;
    numActual = tweet1.getTotalEngagement();
    numExpected = 0;

    if (numActual != numExpected)
      return false;
    // like and retweet a tweet
    tweet1.like();
    tweet1.retweet();
    numActual = tweet1.getTotalEngagement();
    numExpected = 2;
    if (numActual != numExpected)
      return false;
    // check user verification and likes ratio

    if (!tweet1.isUserVerified())
      return false;


    if (tweet1.getLikesRatio() != 0.5)
      return false;

    // check tweet equality
    Tweet tweet1Copy = new Tweet(user1, "fire");
    if (!tweet1.equals(tweet1Copy))
      return false;
    if (tweet1.equals(tweet2))
      return false;

    // check tweet string representation
    Calendar test1 = Calendar.getInstance();
    test1.set(2012, Calendar.MAY, 22, 15, 46, 03);
    Tweet.setCalendar(test1);

    actual = (tweet1.toString());
    expected = "tweet from @Seb* at Tue May 22 15:46:03 CDT 2012:\n-- fire"
        + "\n-- likes: 1\n-- retweets: 1";

    if (!actual.trim().equals(expected.trim()))
      return false;


    return true;
  }

  /**
   * checks tweetNode class
   * 
   * @return true if class works correctly
   */
  public static boolean testNode() {
    Calendar test = Calendar.getInstance();
    test.set(2012, Calendar.MAY, 22, 15, 46, 03);
    Tweet.setCalendar(test);

    User user1 = new User("Seb");
    User user2 = new User("Bob");

    Tweet tweet1 = new Tweet(user1, "fire");
    Tweet tweet2 = new Tweet(user2, "pole");

    TweetNode node1 = new TweetNode(tweet1);
    TweetNode node2 = new TweetNode(tweet2, node1);
    TweetNode node3 = new TweetNode(tweet1);

    if (node1.getTweet() != tweet1)
      return false; // check get tweet works
    node1.setNext(node3);

    if (node2.getNext() != node1)
      return false; // check second constructor works
    if (node1.getNext() != node3)
      return false; // check set next works

    return true;
  }

  /**
   * check twitterfeed class test adding tweets
   * 
   * @return true if class works correctly
   */
  public static boolean testAddTweet() {
    TwitterFeed feed = new TwitterFeed();
    User user1 = new User("Seb");
    User user2 = new User("Bob");

    Tweet tweet1 = new Tweet(user1, "fire");
    Tweet tweet2 = new Tweet(user2, "pole");


    // test size and isEmpty on an empty list
    if (feed.size() != 0) {
      return false;
    }
    if (!feed.isEmpty()) {
      return false;
    }

    feed.addLast(tweet1);// check addlast
    if (feed.size() != 1)
      return false;// check size additon
    if (!feed.contains(tweet1))
      return false;// check contains
    if (!feed.get(0).equals(tweet1))
      return false;// check get

    feed.addFirst(tweet2);
    if (feed.size() != 2)
      return false;// check size again
    if (!feed.getTail().equals(tweet1))
      return false;// check tail
    if (!feed.getHead().equals(tweet2))
      return false;// check head

    return true;
  }

  /**
   * checks twitterfeed class test inserting tweets into feed
   * 
   * @return true if class works correctly
   */
  public static boolean testInsertTweet() {

    Calendar test = Calendar.getInstance();
    test.set(2012, Calendar.MAY, 22, 15, 46, 03);
    Tweet.setCalendar(test);

    TwitterFeed feed = new TwitterFeed();
    User user1 = new User("Seb");
    User user2 = new User("Bob");

    Tweet tweet1 = new Tweet(user1, "fire");
    Tweet tweet2 = new Tweet(user2, "pole");
    Tweet tweet3 = new Tweet(user1, "snow");
    Tweet tweet4 = new Tweet(user1, "four");

    feed.add(0, tweet1);
    feed.add(1, tweet2);
    feed.add(2, tweet3);


    if (!feed.get(0).equals(tweet1))
      return false;// check get works

    if (feed.size() != 3)
      return false;// check size
    feed.add(1, tweet4);
    if (!feed.get(2).equals(tweet2))
      return false;// check adding in middle
    if (!feed.getTail().equals(tweet3))
      return false;// check tail
    if (!feed.getHead().equals(tweet1))
      return false;// check head

    return true;
  }

  /**
   * checks twitterfeed class tests deleting tweets from feed
   * 
   * @return true if class works correctly
   */
  public static boolean testDeleteTweet() {
    Calendar test = Calendar.getInstance();
    test.set(2012, Calendar.MAY, 22, 15, 46, 03);
    Tweet.setCalendar(test);

    TwitterFeed feed = new TwitterFeed();
    User user1 = new User("Seb");
    User user2 = new User("Bob");

    Tweet tweet1 = new Tweet(user1, "fire");
    Tweet tweet2 = new Tweet(user2, "pole");
    Tweet tweet3 = new Tweet(user1, "snow");
    Tweet tweet4 = new Tweet(user1, "four");
    Tweet tweet5 = new Tweet(user1, "five");

    feed.add(0, tweet1);
    feed.add(1, tweet2);
    feed.add(2, tweet3);
    feed.add(3, tweet4);
    feed.add(4, tweet5);

    feed.delete(4);

    if (feed.size() != 4)
      return false;
    if (!feed.getTail().equals(tweet4))
      return false; // check size is correct and tail

    feed.delete(0);

    if (feed.size() != 3)
      return false;
    if (!feed.getHead().equals(tweet2))
      return false;// check head and size again

    if (!feed.delete(1).equals(tweet3))
      return false;// check delete returns correctly


    if (feed.size() != 2)
      return false;
    if (!feed.get(0).equals(tweet2))
      return false;// check get works



    return true;
  }

  /**
   * checks ChronoTwiterator class
   * 
   * @return true if class works correctly
   */
  public static boolean testChronoTwiterator() {
    Calendar test = Calendar.getInstance();
    test.set(2012, Calendar.MAY, 22, 15, 46, 03);
    Tweet.setCalendar(test);

    TwitterFeed feed = new TwitterFeed();
    User user1 = new User("Seb");
    User user2 = new User("Bob");

    Tweet tweet1 = new Tweet(user1, "fire");
    Tweet tweet2 = new Tweet(user2, "pole");
    Tweet tweet3 = new Tweet(user1, "snow");


    feed.add(0, tweet1);
    feed.add(1, tweet2);
    feed.add(2, tweet3);

    feed.setMode(TimelineMode.CHRONOLOGICAL);

    Iterator<Tweet> twiterator = feed.iterator();
    int i = 0;
    for (Tweet tweet : feed) {
      if (tweet != twiterator.next()) {
        // checks that the correct tweet is being iterated
        return false;
      }
      i++;
    }
    if (i != 3) {
      // checks that the amount of tweets is right
      return false;
    }


    return true;


  }

  /**
   * checks VerifiedTwiterator class
   * 
   * @return true if class works correctly
   */
  public static boolean testVerifiedTwiterator() {
    Calendar test = Calendar.getInstance();
    test.set(2012, Calendar.MAY, 22, 15, 46, 03);
    Tweet.setCalendar(test);

    TwitterFeed feed = new TwitterFeed();
    User user1 = new User("Seb");
    User user2 = new User("Bob");

    Tweet tweet1 = new Tweet(user1, "fire");
    Tweet tweet2 = new Tweet(user2, "pole");
    Tweet tweet3 = new Tweet(user1, "snow");

    user1.verify();

    feed.add(0, tweet1);
    feed.add(1, tweet2);
    feed.add(2, tweet3);

    feed.setMode(TimelineMode.VERIFIED_ONLY);

    Iterator<Tweet> twiterator = feed.iterator();
    int i = 0;
    for (Tweet tweet : feed) {
      if (tweet != twiterator.next()) {
        // checks that the correct tweet is being iterated
        return false;
      }
      i++;
    }
    if (i != 2) {
      // checks that the amount of tweets is right
      return false;
    }


    return true;
  }

  /**
   * checks RatioTwiterator class
   * 
   * @return true if class works correctly
   */
  public static boolean testRatioTwiterator() {
    Calendar test = Calendar.getInstance();
    test.set(2012, Calendar.MAY, 22, 15, 46, 03);
    Tweet.setCalendar(test);

    TwitterFeed feed = new TwitterFeed();
    User user1 = new User("Seb");
    User user2 = new User("Bob");

    Tweet tweet1 = new Tweet(user1, "fire");
    Tweet tweet2 = new Tweet(user2, "pole");
    Tweet tweet3 = new Tweet(user1, "snow");

    user1.verify();

    feed.add(0, tweet1);
    feed.add(1, tweet2);
    feed.add(2, tweet3);

    tweet2.retweet();
    tweet2.retweet();
    tweet2.like();

    tweet1.like();
    tweet1.retweet();

    feed.setMode(TimelineMode.LIKE_RATIO);

    Iterator<Tweet> twiterator = feed.iterator();
    int i = 0;
    for (Tweet tweet : feed) {
      if (tweet != twiterator.next()) {
        // checks that the correct tweet is being iterated
        return false;
      }
      i++;
    }

    if (i != 1) {
      // checks that the amount of tweets is right
      return false;
    }
    return true;
  }


  public static void main(String[] args) {
    System.out.println("testUser: " + testUser());
    System.out.println("testTweet: " + testTweet());
    System.out.println("testNode: " + testNode());
    System.out.println("testAddTweet: " + testAddTweet());
    System.out.println("testInsertTweet: " + testInsertTweet());
    System.out.println("testDeleteTweet: " + testDeleteTweet());
    System.out.println("testChronoTwiterator: " + testChronoTwiterator());
    System.out.println("testVerifiedTwiterator: " + testVerifiedTwiterator());
    System.out.println("testRatioTwiterator: " + testRatioTwiterator());
  }
}
