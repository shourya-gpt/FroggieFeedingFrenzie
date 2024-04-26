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

import java.lang.Math;

/**
 * A subclass of Bug that is Movable and moves in a circle. When drawn to the screen they also have
 * a tint applied to the image
 */
public class CirclingBug extends Bug implements Moveable {
  private float[] circleCenter;
  private static final int POINTS = 200;
  private double radius;
  private double ticks;
  private int[] tintColor;

  /**
   * Creates a new CirclingBug object using the provided parameters. By default the number of ticks
   * for a new CirclingBug should be 0.0. The x,y-coordinates for the initial position of the Bug
   * must be calculated by using the equations given in the write-up for ticks = 0.0.
   *
   * @param circleX - , the x-coordinate for the center of the circle path circleY - , the
   *                y-coordinate for the center of the circle path radius - , the radius of this
   *                CirclingBug's circle path tintColor - , an array containing the Red,Green, and
   *                Blue values for the tint color You can assume that this array is ALWAYS length
   *                3.
   */
  public CirclingBug(float circleX, float circleY, double radius, int[] tintColor) {
    super((float) (radius * Math.cos(0.0) + circleX), (float) (radius * Math.sin(0.0) + circleY),
        POINTS);
    ticks = 0.0;
    this.radius = radius;
    this.tintColor = new int[3];
    // initializes individual elements of the tintColor[] field
    for (int i = 0; i < 3; i++) {
      this.tintColor[i] = tintColor[i];
    }
    circleCenter = new float[2];
    circleCenter[0] = circleX;
    circleCenter[1] = circleY;

  }

  /**
   * Draws the image to the screen, tinting it with the tintColor before drawing it. After the image
   * is drawn to the screen the tinting effect will need to done undone by tinting it again with
   * white. (255, 255, 255)
   */
  @Override
  public void draw() {
    processing.tint(tintColor[0], tintColor[1], tintColor[2]);
    processing.image(image, this.getX(), this.getY());
    processing.tint(255, 255, 255);
  }

  /**
   * Draws the image to the screen, tinting it with the tintColor before drawing it. After the image
   * is drawn to the screen the tinting effect will need to done undone by tinting it again with
   * white. (255, 255, 255)
   */
  public void move() {
    // Checks if the CirclingBug should move
    if (shouldMove()) {
      ticks += 0.05;

      // uses equation provided in write-up

      float newX = (float) (radius * Math.cos(ticks) + circleCenter[0]);
      float newY = (float) (radius * Math.sin(ticks) + circleCenter[1]);

      /**
       * updates X and Y coordinates and move hitbox of CirclingBug object after every 0.05 ticks
       * according to above newX, newY assignment
       */

      this.setX(newX);
      this.setY(newY);
      moveHitbox(newX, newY);
    }
  }

  /**
   * Draws the image to the screen, tinting it with the tintColor before drawing it. After the image
   * is drawn to the screen the tinting effect will need to done undone by tinting it again with
   * white. (255, 255, 255)
   */
  public boolean shouldMove() {
    return true;
  }
}
