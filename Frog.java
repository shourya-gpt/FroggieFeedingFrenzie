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

import processing.core.PImage;
import processing.core.PApplet;

/**
 * An instantiable class maintains data about a Frog in the Froggie Feeding Frenzie game. They an be
 * drawn to the screen, dragged around by the mouse, and attack Bugs with its Tongue.
 */
public class Frog extends GameActor implements Moveable {
  private int health;
  private static final String IMG_PATH = "images/frog.png";
  private boolean isDragging;
  private float oldMouseX;
  private float oldMouseY;
  private Tongue tongue;

  /**
   * Constructor for a new Frog object using the provided parameters. The Frog is NOT dragging by
   * default.
   *
   * @param x - , the x-coordinate for the center of the Frog and starting point of the tongue y - ,
   *          the y-coordinate for the center of the Frog and starting point of the tongue health -
   *          , the initial health of this Frog
   * 
   * @throws IllegalArgumentException - with a descriptive message if health is less than 1
   */
  public Frog(float x, float y, int health) throws IllegalArgumentException {
    super(x, y, IMG_PATH);
    this.isDragging = false;
    if (health < 1) {
      throw new IllegalArgumentException("Helth is less than 1.");
    }
    this.health = health;
    tongue = new Tongue(x, y);
    // initializes the oldMouseX and the oldMouseY fields
    oldMouseX = x;
    oldMouseY = y;
    // oldMouseX = processing.mouseX;
    // oldMouseY = processing.mouseY;
    // System.out.println("constructor: oldMouseX, oldMouseY " + oldMouseX +
    // oldMouseY);
  }

  /**
   * Draws the image of the Frog to the screen. If the Frog's tongue is active: (1)draw the tongue
   * and (2) extend the tongue by moving it's x-coordinate to the Frog's x-coordinate and up 2
   * pixels.
   */
  @Override
  public void draw() {
    PImage frogImg = processing.loadImage(IMG_PATH);
    if (tongue.isActive() == true) {
      tongue.draw();
      tongue.extend(getX(), -2);

    }
    processing.image(frogImg, getX(), getY());

  }

  /**
   * Gets the current health of the Frog
   * 
   * @return the current health of this Frog
   */
  public int getHealth() {
    return this.health;
  }

  /**
   * Gets the Hitbox for this Frog's tongue.
   * 
   * @return this Frog's tongue's hitbox
   * @throws IllegalStateException - if the tongue is currently inactive
   */
  public Hitbox getTongueHitbox() throws IllegalStateException {
    if (!(tongue.isActive())) {
      throw new IllegalStateException("Tongue is currently inactive.");
    }
    return tongue.getHitbox();
  }

  /**
   * Determines if this frog is dead.
   * 
   * @return true if this Frog's health is 0 or lower, false otherwise
   */
  public boolean isDead() {
    if (health <= 0) {
      return true;
    }
    return false;
  }

  /**
   * Determines whether or not this Frog has run into a Bug.
   *
   * @param b - , the Bug to check that if it collides with the Frog
   * 
   * @return true if the Bug's Hitbox and Frog's Hitbox overlap, false otherwise
   */
  public boolean isHitBy(Bug b) {
    // gets the hitbox for the frog and checks if it collides with the hitbox of the bug
    if (this.getHitbox().doesCollide(b.getHitbox())) {
      return true;
    }
    return false;
  }

  /**
   * Determines if the mouse is over the Frog's image
   *
   * @return true, if the mouse is inside the Frog's bounding box of the image, false otherwise
   */
  public boolean isMouseOver() {

    // gets the x and y coordinates of current mouse position

    float mouseX = processing.mouseX;
    float mouseY = processing.mouseY;

    // gets the x and y coordinates of the frog object

    float frogX = getX();
    float frogY = getY();

    /**
     * if mouse x coordinate b/w left and right side of frog and mouse y coordinate b/w top and
     * bottom side of frog
     */
    if (mouseX < frogX + (image.width / 2) && mouseX > frogX - (image.width / 2)
        && mouseY < frogY + (image.height / 2) && mouseY > frogY - (image.height / 2)) {

      return true;

    }
    return false;
  }

  /**
   * Decreases the health of this Frog by 1.
   */
  public void loseHealth() {
    this.health -= 1;
  }

  /**
   * Changes this Frog so it is now being dragged. This method should only be called externally when
   * the mouse is over this frog and has been clicked.
   */
  public void mousePressed() {
    if (isMouseOver()) {
      this.isDragging = true;
    }
  }

  /**
   * Changes this Frog so it is no longer being dragged. This method should only be called
   * externally when the mouse has been released.
   */
  public void mouseReleased() {
    this.isDragging = false;

  }

  /**
   * Moves the Frog by dragging it by the mouse, if it should be dragging. The starting point of the
   * tongue and the Hitbox need to move along with the Frog. If the Frog's tongue is NOT active,
   * move the tongue's endpoint along with the Frog as well. The Frog only moves if it should move.
   * 
   */
  public void move() {

    // gets the x and the y coordinates of the current mouse position

    float mouseX = processing.mouseX;
    float mouseY = processing.mouseY;

    // gets the difference of the coordinates between the current and the old mouse positions

    float xDiff = mouseX - oldMouseX;
    float yDiff = mouseY - oldMouseY;

    /**
     * initializes current x and y coordinates to the current position of mouse added to difference
     * calculated above
     */


    float currX = this.getX() + xDiff;
    float currY = this.getY() + yDiff;

    // only updates the position of the frog if the frog is supposed to be moving

    if (shouldMove()) {

      this.setX(currX);
      this.setY(currY);

      this.moveHitbox(currX, currY);

      tongue.updateStartPoint(currX, currY);


      // if the tongue isn't active, updates the end trajectory of the tongue object as well


      if (!tongue.isActive()) {
        tongue.updateEndPoint(currX, currY);
      }

    }

    // finally, updates old mouse positions to new coordinates of mouse

    oldMouseX = processing.mouseX;
    oldMouseY = processing.mouseY;

  }

  /**
   * Reports if the Frog needs to move on the screen.
   * 
   * @return true if the Frog is being dragged, false otherwise
   */
  public boolean shouldMove() {
    if (isDragging)
      return true;
    return false;
  }

  /**
   * Starts an attack by resetting the tongue to it's default state and activating the tongue.
   */
  public void startAttack() {
    tongue.reset();
    tongue.activate();
  }

  /**
   * Stops the attack by deactivating the tongue.
   */
  public void stopAttack() {
    tongue.deactivate();

  }

  /**
   * Reports if this Frog's tongue has hit the top of the screen.
   *
   * @return true if the tongue has hit the top of the screen, false otherwise
   */
  public boolean tongueHitBoundary() {
    return tongue.hitScreenBoundary();

  }
}
