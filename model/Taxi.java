/*
 * TCSS 305
 * Assignment 3 - EasyStreet
 */

package model;

/**
 * This is the Taxi class for the easy street program.
 * 
 * @author Arrunn Chhouy
 * @version 1.0
 */
public class Taxi extends Car {
    /**
     * Death time to revive.
     */
    protected static final int DEATH_TIME = 5;
    
    /**
     * The wait time at stop light.
     */
    private static final int MY_RED_LIGHT_TIME = 3;
    
    /**
     * Keeps track of how long the taxi wait.
     */
    private int myCounter;
    
    


    /**
     * 
     * @param theX is the x-coordinate.
     * @param theY is the y-coordinate.
     * @param theDirection is the direction.
     */
    public Taxi(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection, DEATH_TIME, "taxi");
        myCounter = 0;
    }

    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean passResult = false;
        if (myCounter == MY_RED_LIGHT_TIME) {
            passResult = true;
            myCounter = 0;
        } else if (theTerrain == Terrain.LIGHT && theLight == Light.RED) {
            myCounter++;
            passResult = false;
        } else if (theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT 
                        && theLight == Light.YELLOW || theTerrain == Terrain.LIGHT 
                        && theLight == Light.GREEN) {
            passResult = true;
        } else {
            passResult = false;
        }
        return passResult;
    }

}
