//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P05 Froggie Feeding Frenzie
// Course:   CS 300 Fall 2023
//
// Author: Trevor Beesley
// Email: tbeesley@wisc.edu
// Lecturer: Hobbes Legault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Shourya Gupta
// Partner Email: shourya.gupta326@wisc.edu
// Partner Lecturer's Name: Mark Mansi
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//    X Write-up states that pair programming is allowed for this assignment.
//    X We have both read and understand the course Pair Programming Policy.
//    X We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * An instantiable class maintains data about a Bug in the Froggie Feeding Frenzie game. These bugs
 * do not move, can be drawn to the screen, and detect if it has been hit (eaten) by a Frog.
 */
public class Bug extends GameActor {
  private static final String IMG_PATH = "images/bug.png";
  private int points;

  /**
   * Creates a new Bug object with the provided information.
   * 
   * @param x      the x-coordinate for the center of this bug
   * @param y      the y-coordinate for the center of this bug
   * @param points the number of points the Bug is worth
   */
  public Bug(float x, float y, int points) {
    super(x, y, IMG_PATH);
    this.points = points;

  }

  /**
   * Creates a new Bug object with the provided information.
   * 
   * @return the points of Bug
   */
  public int getPoints() {
    return points;
  }

  /**
   * Determines whether or not this bug has been eaten by the Frog.
   * 
   * @param f the frog that has possibly eaten this bug
   * @return true if this Bug's Hitbox overlaps that Frog's Tongue's Hitbox, false otherwise note
   *         this method should also return false if the tongue is inactive
   */
  public boolean isEatenBy(Frog f) {
    // Ensures that the tongue exists
    try {
      f.getTongueHitbox();
    } catch (IllegalStateException e) {
      return false;
    }
    // Gets the hitbox of the tongue of the frog and sees if it collides with the hitbox of Bug
    if (this.getHitbox().doesCollide(f.getTongueHitbox())) {
      return true;
    }
    return false;
  }
}
