/*
 * TCSS 305
 * Assignment 3 - EasyStreet
 */

package model;

import java.util.Random;

/**
 * The AbstractVehicle class is the parent class
 * that have the same basic behavior among all the vehicles.
 * 
 * @author Arrunn Chhouy
 * @version 1.0
 */
public abstract class AbstractVehicle implements Vehicle {
    
    /**
     * A random object.
     */
    protected Random myRandom = new Random();
    
    /**
     * The terrain that the human starts on.
     */
    protected Terrain myTerrain;
    
    /**
     * The x-coordinate.
     */
    private int myX;
    
    /**
     * The y-coordinate.
     */
    private int myY;
    
    /**
     * The direction of the vehicle.
     */
    private Direction myDirection;

    /**
     * The initial x-coordinate.
     */
    private int myInitialX;
    
    /**
     * The initial y-coordinate.
     */
    private int myInitialY;
    
    /**
     * The initial direction of the vehicle.
     */
    private Direction myInitialDirection;
    
    /**
     * A counter to keep track of death time.
     */
    private int myDeathCounter;
    
    /**
     * A vehicle's death time.
     */
    private int myDeathTime;
    
    /**
     * The name of the vehicle.
     */
    private String myName;
    
    /**
     * The AbstractVehicle constructor is created and initializes
     * all the fields. 
     * 
     * @param theX is the x-coordinate.
     * @param theY is the y-coordinate.
     * @param theDirection is the direction of the vehicle.
     * @param theDeathTime is the death time.
     * @param theName is the name of vehicle.
     */
    protected AbstractVehicle(final int theX, final int theY, final Direction theDirection,
                              final int theDeathTime, final String theName) {
        myInitialX = theX;
        myInitialY = theY;
        
        myX = theX;
        myY = theY;
        
        myInitialDirection = theDirection;
        myDirection = theDirection;
        
        myDeathTime = theDeathTime;
        myDeathCounter = theDeathTime;
        myName = theName; 

    }
    
    /**
     * The AbstractVehicle constructor is created and initializes
     * all the fields. 
     *  
     * @param theX is the x-coordinate.
     * @param theY is the y-coordinate.
     * @param theDirection is the direction of the vehicle.
     * @param theDeathTime is the death time.
     * @param theName is the name of vehicle.
     * @param theTerrain is the Terrain.
     */
    protected AbstractVehicle(final int theX, final int theY, final Direction theDirection,
                              final Terrain theTerrain, final int theDeathTime, 
                              final String theName) {
        myInitialX = theX;
        myInitialY = theY;
        
        myX = theX;
        myY = theY;
        
        myInitialDirection = theDirection;
        myDirection = theDirection;
        
        myDeathTime = theDeathTime;
        myDeathCounter = theDeathTime;
        myName = theName;
        myTerrain = theTerrain;
        
    }
    
    /**
     * Checks to see which vehicle is dead and alive.
     * 
     * @param theOther is another Vehicle
     */
    @Override
    public void collide(final Vehicle theOther) {
        if (isAlive() && theOther.isAlive() && theOther.getDeathTime() < myDeathTime) {
            myDeathCounter = 0;
        }

    }

    /**
     * Get the death time of the vehicle.
     * 
     * @return an int of the death time
     */
    @Override
    public int getDeathTime() {
        return myDeathTime;
    }

    /**
     * Get vehicle image file.
     *
     * @return a string of the vehicle image file
     */
    @Override
    public String getImageFileName() {
        String imageFileName; 
        if (isAlive()) {
            imageFileName = toString() + ".gif";  
        } else {
            imageFileName = toString() + "_dead.gif";
        }
        return imageFileName;
    }

    /**
     * Get the direction of the vehicle.
     * 
     * @return a direction
     */
    @Override
    public Direction getDirection() {
        return myDirection;
    }

    /**
     * Get the x-coordinate of the vehicle.
     * 
     * @return an int of x 
     */
    @Override
    public int getX() {
        return myX;
    }

    /**
     * Get the y-coordinate of the vehicle.
     * 
     * @return an int of y
     */
    @Override
    public int getY() {
        return myY;
    }

    /**
     * Checks to see if the vehicle is alive.
     */
    @Override
    public boolean isAlive() {
        return myDeathTime == myDeathCounter;
    }

    /**
     * Keeps track of the death time.
     */
    @Override
    public void poke() {
        if (isAlive()) {
            Direction.random();
        } else {
            myDeathCounter++;
        }
    }

    /**
     * Resets the object to the initial state that it was instantiated.
     */
    @Override
    public void reset() {
        myX = myInitialX;
        myY = myInitialY;
        myDirection = myInitialDirection;
    }

    /**
     * Sets the direction of the vehicle.
     * 
     * @param theDir the Direction
     */
    @Override
    public void setDirection(final Direction theDir) {
        myDirection = theDir;

    }

    /**
     * Sets the x-coordinate.
     *
     * @param theX
     */
    @Override
    public void setX(final int theX) {
        myX = theX;

    }

    /**
     * Sets the y-coordinate.
     * 
     * @param theY
     */
    @Override
    public void setY(final int theY) {
        myY = theY;
    }
    
    
    /**
     * Returns a string of the name of the Vehicle.
     * 
     * @return a String 
     */
    @Override
    public String toString() {
        return myName;
    }
}
