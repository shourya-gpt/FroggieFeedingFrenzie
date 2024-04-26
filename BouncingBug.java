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

import java.util.Random;

/**
 * Creates a new Bouncing Bug object using the given parameters. Randomly determines if the bug will
 * initially move left or right. The same applies to if the bug will move up or down.
 */
public class BouncingBug extends Bug implements Moveable {

  private int[] speedNums;
  private Random randGen;
  private boolean goLeft;
  private boolean goDown;
  private static final int POINTS = 100;

  /**
   * Creates a new Bouncing Bug object using the given parameters. Randomly determines if the bug
   * will initially move left or right. The same applies to if the bug will move up or down.
   * 
   * @param x  the x-coordinate for the center of this BouncingBug
   * @param y  the y-coordinate for the center of this BouncingBug
   * @param dx the number of pixels to move horizontally
   * @param dy the number of pixels to move vertically
   */
  public BouncingBug(float x, float y, int dx, int dy) {
    super(x, y, POINTS);
    randGen = new Random();
    int randInt1 = randGen.nextInt(2);
    int randInt2 = randGen.nextInt(2);
    speedNums = new int[2];
    speedNums[0] = dx;
    speedNums[1] = dy;

    if (randInt1 == 0) {
      goLeft = true;
    }
    if (randInt1 != 0) {
      goLeft = false;
    }
    if (randInt2 == 0) {
      goDown = true;
    }
    if (randInt2 != 0) {
      goDown = false;
    }
  }

  /**
   * Moves this BouncingBug dx pixels left or right (depending on the current horizontal direction)
   * and dy pixels up or down (depending on the current vertical direction). The Bug's Hitbox should
   * also move with the BouncingBug. The bug only changes its xy-coordinates if it should move. If
   * the center of the Bouncing Bug hits an end of the window it will switch directions. Ex. The Bug
   * is going down and left and hits the left side of the screen then the Bug will be going down and
   * right.
   */
  public void move() {
    if (shouldMove()) {
      int dx = speedNums[0];
      int dy = speedNums[1];

      // Go left
      if (goLeft == true) {
        if (this.getX() + (-1 * dx) <= 0) {
          this.setX(0);
          goLeft = false;
          moveHitbox(this.getX(), this.getY());
        } else {
          this.setX(this.getX() - (1 * dx));
          moveHitbox(this.getX(), this.getY());
        }
      }

      if (goLeft != true) {
        if (this.getX() + dx >= 800) {
          this.setX(800);
          goLeft = true;
          moveHitbox(this.getX(), this.getY());
        } else {
          this.setX(this.getX() + dx);
          moveHitbox(this.getX(), this.getY());
        }
      }

      // Go down
      if (goDown == true) {
        if (this.getY() + dy >= 600) {
          this.setY(600);
          goDown = false;
          moveHitbox(this.getX(), this.getY());
        } else {
          this.setY(this.getY() + dy);
          moveHitbox(this.getX(), this.getY());
        }
      }
      if (goDown == false) {
        if (goDown == false && this.getY() - dy <= 0) {
          this.setY(0);
          goDown = true;
          moveHitbox(this.getX(), this.getY());
        } else {
          this.setY(this.getY() - dy);
          moveHitbox(this.getX(), this.getY());
        }
      }
    }
  }

  /**
   * Reports if the BouncingBug needs to move.
   * 
   * @return if the BouncingBug should move
   */
  public boolean shouldMove() {
    return true;
  }
}
