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

import processing.core.PApplet;

/**
 * An instantiable class for all game actors in the Froggie Feeding Frenzie game. Game actors are
 * images that are drawn the screen that also have hitboxes.
 */
public class GameActor {

  private float[] coordinates;
  private Hitbox hitbox;
  protected processing.core.PImage image;
  protected static processing.core.PApplet processing;

  /**
   * Constructor for a new GameActor object by setting the coordinates, loading the image, and
   * creating the hitbox.
   *
   * @param x - , the x-coordinate for the center of this object and its hitbox y - , the
   *          y-coordinate for the center of this object and its hitbox imgPath - the path to the
   *          image that will be loaded in
   * 
   * @throws IllegalStateException - with a descriptive message if processing is null
   */
  public GameActor(float x, float y, String imgPath) throws IllegalStateException {

    coordinates = new float[2];
    coordinates[0] = x;
    coordinates[1] = y;

    if (processing == null) {
      throw new IllegalStateException("Processing is null");
    }

    // loads the CirclingBug using loadImamge method of processing class
    image = processing.loadImage(imgPath);

    // creates a new hitbox for GameActor class
    hitbox = new Hitbox(x, y, 75, 75);
  }

  /**
   * Draws the image of the GameActor to the screen. (OPTIONAL)Visualize the Hitbox to help with
   * debugging.
   */
  public void draw() {
    processing.image(image, this.coordinates[0], this.coordinates[1]);
  }

  /**
   * Getter for the Hitbox.
   *
   * @return the Hitbox of this GameActor
   */
  public Hitbox getHitbox() {
    return hitbox;
  }

  /**
   * Getter for the x-coordinate.
   *
   * @return , the x-coordinate of center of this GameActor
   */
  public float getX() {
    return coordinates[0];
  }

  /**
   * Getter for the y-coordinate.
   *
   * @return , the y-coordinate of center of this GameActor
   */
  public float getY() {
    return coordinates[1];
  }

  /**
   * Setter for the x-coordinate.
   *
   * @param newX - , the new x-coordinate for the center of this GameActor
   */
  public void setX(float newX) {
    this.coordinates[0] = newX;
  }

  /**
   * Setter for the y-coordinate.
   *
   * @param newY - , the new y-coordinate for the center of this GameActor
   */
  public void setY(float newY) {
    this.coordinates[1] = newY;
  }

  /**
   * Moves this GameActors Hitbox to the provided x,y-coordinates
   *
   * @param x - , the new x-coordinate for the center of the GameActor's hitbox y - , the new
   *          y-coordinate for the center of the GameActor's hitbox
   */
  public void moveHitbox(float x, float y) {
    hitbox.setPosition(x, y);
  }

  /**
   * Sets the processing for all GamActors
   *
   * @param processing - , the instance of a PApplet to draw onto
   */
  public static void setProcessing(processing.core.PApplet processing) {
    GameActor.processing = processing;

  }
}
