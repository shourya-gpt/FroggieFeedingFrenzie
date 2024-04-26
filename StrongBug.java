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
 * A subclass of Bug that is Movable. These bugs only move when they are not at max health. When hit
 * they become smaller and start moving horizontally across the screen.
 */
public class StrongBug extends Bug implements Moveable {
  private int currentHealth;
  private final int MAX_HEALTH;
  private static final int POINTS = 500;

  /**
   * Creates a new StrongBug object at full health using the provided parameters.
   * 
   * @param x      the x-coordinate for the center of this StrongBug
   * @param y      the y-coordinate for the center of this StrongBug
   * @param health the max health for this StrongBug
   * @throws IllegalArgumentException
   */
  public StrongBug(float x, float y, int health) throws IllegalArgumentException {
    super(x, y, POINTS);
    // Checks if the StrogBug's health is less than one
    if (health < 1)
      throw new IllegalArgumentException("Health of strong bug is below 1.");
    MAX_HEALTH = health;
    currentHealth = health;
  }

  /**
   * Reports if this StrongBug is dead.
   * 
   * @return
   */
  public boolean isDead() {
    // Checks is the StrongBug's health is currently less than zero
    if (currentHealth <= 0) {
      return true;
    }
    return false;
  }

  /**
   * Determines whether or not this bug has been eaten by the Frog. If the Bug has been hit by the
   * frog: 1.decrease the StrongBug's health 2.resize the image to 75% of its current height and 75%
   * of it's current width 3.change the dimensions of the hitbox to match the new image dimensions.
   */
  @Override
  public boolean isEatenBy(Frog f) {
    // Checks if the StrongBug is eaten by the Frog
    if (super.isEatenBy(f)) {
      loseHealth();
      image.resize((int) (0.75 * image.width), (int) (0.75 * image.height)); // resize the image
      getHitbox().changeDimensions(((int) (0.75 * image.width)), ((int) (0.75 * image.height)));
      return true;
    }
    return false;
  }

  /**
   * Reports if the StrongBug needs to move.
   */
  public boolean shouldMove() {
    // Checks if the current health of strong bug is less than the max health
    if (currentHealth < MAX_HEALTH) {
      return true;
    }
    return false;
  }

  /**
   * Decreases the health of this StrongBug by 1.
   */
  public void loseHealth() {
    currentHealth -= 1;
  }

  /**
   * Moves this StrongBug 3 units to the right, wrapping around the screen when the center hits the
   * edge of the window. The Hitbox should move with the StrongBug. The x,y-coordinate of only
   * changes if the StrongBug should move.
   */
  public void move() {
    if (shouldMove()) {
      // Checks if the StrongBug moves off the screen
      if (getX() + 3 >= 800) {
        setX(getX() + 3 - 800);
      }
      setX(getX() + 3);
      moveHitbox(getX(), getY());
    }
  }
}
