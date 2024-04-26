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

import java.io.File;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;

/**
 * This class creates models a frog game with a frog with a tongue and different types of bugs
 */
public class FrogGame extends PApplet {
  private ArrayList<GameActor> gameActors; // array list of the gameActors in the game
  private int score; // the player's current score
  private PImage backgroundImg; // the image to use for the background
  private boolean isGameOver; // keeps track if the game is over, is true if the game is over
  private Random randGen; // random number generator
  private static final int BUG_COUNT = 5; // how many bugs should be on the screen at all times

  /**
   * This method is the main method of the Frog Game class
   * 
   * @param String[] args
   */
  public static void main(String[] args) {
    PApplet.main("FrogGame");
  }

  /**
   * This method calls PApplet's size() method setting it's width and height
   *
   */
  @Override
  public void settings() {
    // TODO #1 call PApplet's size() method giving it 800 as the width and 600 as
    // the height
    size(800, 600);
  }

  /**
   * This method calls PApplet's size() method setting it's width and height
   *
   */
  @Override
  public void setup() {
    // TODO #2 add PApplet method calls from write-up
    this.getSurface().setTitle("Froggie Feeding Frenzie"); // set title of window
    this.imageMode(PApplet.CENTER); // images when drawn will use x,y as their center
    this.rectMode(PApplet.CENTER); // rectangles when drawn will use x,y as their
    // center
    this.focused = true; // window is "active" upon start up
    this.textAlign(PApplet.CENTER); // text written to screen will have center
    // alignment
    this.textSize(30); // text is 30 pt

    // TODO #3 initialize randGen
    randGen = new Random();

    // TODO #4 load in and save the backgroundImg
    backgroundImg = loadImage("images/background.jpg");
    // TODO #5 initialize gameActors to an empty ArrayList
    gameActors = new ArrayList<GameActor>();
    // TODO #7 set the processing variable for all classes where necessary (update
    // this as needed)
    Hitbox.setProcessing(this);
    GameActor.setProcessing(this);
    Tongue.setProcessing(this);

    // adds a bug image GameActor to the arraylist

    gameActors.add(new GameActor(400, 300, "images/bug.png"));

    // adds a Bug object to the arraylist

    gameActors.add(new Bug(200, 300, 0));
    // TODO #16 call initGame()
    initGame();
  }

  /**
   * This method draws the objects of the gameActor ArrayList to the screen using casting
   * 
   */
  @Override
  public void draw() {
    // TODO #6 call PApplet's image() method to draw the backgroundImg at the center
    // of the screen


    // (note in the code logic this step to be performed takes place AFTER TODO #19)

    // if isGameOver is true, displays GAME OVER in middle of window

    if (this.isGameOver == true) {
      text("GAME OVER", width / 2, height / 2);
    } else {

      image(backgroundImg, width / 2, height / 2);

      // draws and moves every GameActor in the ArrayList to the screen

      for (int i = 0; i < gameActors.size(); i++) {
        gameActors.get(i).draw();
        if (gameActors.get(i) instanceof Moveable) {
          ((Moveable) gameActors.get(i)).move();
        }
      }


      runGameLogicChecks();

      // displays the health and score of the Frog object at the top left corner of window

      text("Score: " + score, 240, 40);
      text("Health: " + ((Frog) gameActors.get(getFrog())).getHealth(), 80, 40);

      // TODO #14 print "Health: " + frog's health at (80,40) and "Score: " + score at
      // (240,40)
      // to the screen


      // TODO #9 update the code you wrote for TODO #8 to have all Movable GameActors
      // move

      // TODO #19 run all the game logic checks

    }


    // TODO #20 update the code you wrote above to do the following:
    // (1) if the game is over, do NONE of the above steps. Instead print "GAME
    // OVER" to
    // the center of the screen.
    // (2) otherwise do the above steps

    // CHECKPOINT 1 - PASSED
    // Hitbox testHitbox = new Hitbox(backgroundImg.width / 2, backgroundImg.height
    // / 2, 50, 50);
    // testHitbox.visualizeHitbox();
  }

  /**
   * This method adds a random type of Bug (or it's child classes) to the gameActors array
   * 
   */
  private void addNewBug() {
    // TODO #10 implement this method, see below for more details.
    // This creates a bug of a random type and adds it to the list of GameActors.
    // (1) generate a random number in the range [0,4)
    this.randGen = new Random();
    int bugNum = randGen.nextInt(4);
    // (2) generate a random x value in the range [0, windowWidth) for the bug
    int randomX = randGen.nextInt(width);
    // (3) generate a random y value in the range [0, windowHeight - 150) for the
    // bug
    int randomY = randGen.nextInt(height - 150);
    // (4) depending on the value generated in step (1)
    // create the following bug and add it to the arraylist


    // 0 -> a new regular Bug at (x,y) that is worth 25 points

    if (bugNum == 0) {
      gameActors.add(new Bug(randomX, randomY, 25));
    }

    // 1 -> a new BouncingBug at (x,y) that has a dx of 2 and a dy of 5
    if (bugNum == 1) {
      gameActors.add(new BouncingBug(randomX, randomY, 2, 5));
    }

    // 2 -> a new CirclingBug at (x,y) with a radius of 25 and a random set of RGB values [0,256)
    if (bugNum == 2) {
      gameActors.add(new CirclingBug(randomX, randomY, 25,
          new int[] {randGen.nextInt(256), randGen.nextInt(256), randGen.nextInt(256)}));
    }

    // 3 -> a new StrongBug at (x,y) with an initial health of 3
    if (bugNum == 3) {
      gameActors.add(new StrongBug(randomX, randomY, 3));
    }
  }

  /**
   * This method checks if the mouse coordinates coincide with the Frog's hitbox, and subsequently
   * calls mousePressed()
   * 
   */
  @Override
  public void mousePressed() {
    // if mouse is over the Frog call its mousePressed method
    for (int i = 0; i < gameActors.size(); i++) {
      // if the element reffered to is an instance of the Frog class
      if (gameActors.get(i) instanceof Frog) {
        Frog frogObj = (Frog) gameActors.get(i);
        // calls the mousePressed if frogObj isMouseOver
        if (frogObj.isMouseOver()) {
          frogObj.mousePressed();
        }
      }
    }
  }

  /**
   * This method iterates through the gameActor ArrayList and uses casting to call the
   * mouseReleased() method
   * 
   */
  @Override
  public void mouseReleased() {
    // call the Frog's mouseReleased method
    for (int i = 0; i < gameActors.size(); i++) {
      // if the element being referred to is an instance of the Frog class
      if (gameActors.get(i) instanceof Frog) {
        Frog frogObj = (Frog) gameActors.get(i);
        // calls the mouseReleased if frogObj isMouseOver
        if (frogObj.isMouseOver()) {
          frogObj.mouseReleased();
        }
      }
    }
  }

  /**
   * This method cheks if the space ' gameActor ArrayList and uses casting to call the startAttack()
   * method
   * 
   */
  @Override
  public void keyPressed() {
    // if the key is a space, have the frog starts attacking
    if (key == ' ') {
      // iterates through all the elements of gameActors
      for (int i = 0; i < gameActors.size(); i++) {
        // if the element being referred to is an instance of the Frog class
        if (gameActors.get(i) instanceof Frog) {
          Frog frogObj = (Frog) gameActors.get(i);
          frogObj.startAttack();
        }
      }
    }

    // if the key is a lowercase 'r', reset the game to its initial state
    if (key == 'r') {
      initGame();
    }
  }

  /**
   * This method sets FrogGame's score and isGameOver fields and adds a Frog object and a specific
   * number of Bug (or one of it's child classes) objects to the gameActor ArrayList
   * 
   */
  public void initGame() {
    // TODO #15 implement this method, see below for more details. This methods sets
    // the game to
    // its initial state before playing.
    // (1) set the score to 0
    // (2) make the game NOT over
    // (3) clear out the arraylist
    // (4) create and add Frog with 100 health to the list. Its x value should be
    // half the
    // width of the screen. Its y value should be the height of the screen minus 100
    // (5) add new bugs (of random varieties) to the list UP TO the BUG_COUNT
    this.randGen = new Random();
    this.score = 0;
    this.isGameOver = false;
    gameActors.clear();
    gameActors.add(new Frog(this.width / 2, this.height - 100, 100));
    int randomX = randGen.nextInt(this.width);
    int randomY = randGen.nextInt(this.height - 150);

    for (int i = 1; i <= BUG_COUNT; i++) {
      addNewBug();
    }

  }

  /**
   * This method implements and maintaints various logic consistencies in the game
   */
  private void runGameLogicChecks() {
    // TODO #18 implement this method, see below for details. This method runs all
    // nessisary
    // game logic checks. Feel free to decompose it into smaller helper methods.
    // (1) if the Frog's tongue hits the edge of the screen, then it stops attacking
    // (2) Check every bug to see if it has been hit by the Frog.
    // (a) if a non-StrongBug is hit do the following
    // (a1) stop the frog's attack
    // (a2) remove it from the game
    // (a3) update the score
    // (a4) add a new bug (of a random variety) to the game
    // (b) of a StrongBug is hit do the following
    // (b1) stop the frog's attack
    // (b2) the StrongBug takes damage and loses health
    // (b3) if the StrongBug is dead do steps a1 - a4
    // (3) check if the frog hits any of the bugs
    // (a) if it hit any of the bugs it takes damage and loses health
    // NOTE: it can be hit my multiple bugs at the same time loses health for each.
    // Ex. is hit by 2 different bugs simultanously then should take 2 damage.
    // (b) if the frog is dead then update the game so it is over

    // gets the Frog object from the private helper method getFrog()
    Frog frogObj = (Frog) gameActors.get(getFrog());

    if (frogObj.tongueHitBoundary()) {
      frogObj.stopAttack();
    }

    // iterates through all the elements of the gameActors array
    for (int i = 0; i < gameActors.size(); i++) {
      // if element being referred to is or derives from Bug class
      if (gameActors.get(i) instanceof Bug) {

        // checks if Bug object has collided with and been killed by the Frog object
        if (((Bug) gameActors.get(i)).isEatenBy(frogObj)) {

          // checks if the element being referred to is or derives from StrongBug
          if (!(gameActors.get(i) instanceof StrongBug)) {

            frogObj.stopAttack();

            // updates the score with the killed Bug's points

            score += ((Bug) gameActors.get(i)).getPoints();
            gameActors.remove(i);
            addNewBug();
            // if the element being referred to is or derives from the StrongBug class
          } else if (gameActors.get(i) instanceof StrongBug) {

            frogObj.stopAttack();

            // checks if the StrongBug object has been eaten by the Frog object
            ((StrongBug) gameActors.get(i)).isEatenBy(frogObj);
            // if dead, updates the score of Frog object with points of StrongBug
            if (((StrongBug) gameActors.get(i)).isDead()) {
              score += ((StrongBug) gameActors.get(i)).getPoints();
              gameActors.remove(i);
              addNewBug();
            }
          }
        }
      }
    }
    // iterates through all the elements of the gameActors array
    for (int i = 0; i < gameActors.size(); i++) {
      // checks if the object being referred to is or derives from the Bug class
      if (gameActors.get(i) instanceof Bug) {
        // checks if the Frog object has been hit by the Bug object
        if (frogObj.isHitBy((Bug) gameActors.get(i))) {
          frogObj.loseHealth();
          // ends the game if the Frog object is dead
          if (frogObj.isDead()) {
            isGameOver = true;
          }
        }
      }
    }
  }

  /**
   * Private helper method to return the Frog object for use in draw() and runGameLogicChecks()
   * 
   * @return the index of Frog in the gameActor array
   */
  private int getFrog() {
    for (int i = 0; i < this.gameActors.size(); i++) {
      if (this.gameActors.get(i) instanceof Frog) {
        return i;
      }
    }
    return -1;
  }

}
