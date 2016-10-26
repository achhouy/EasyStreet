/*
 * TCSS 305
 * Assignment 3 - EasyStreet
 */

package model;

import java.util.Map;

/**
 * The car class for the easy street program.
 * 
 * @author Arrunn Chhouy
 * @version 1.0
 */
public class Car extends AbstractVehicle {
    
    /**
     * Death time to revive.
     */
    protected static final int DEATH_TIME = 5;

    /**
     * 
     * @param theX is the x-coordinate.
     * @param theY is the y-coordinate.
     * @param theDirection is the direction.
     */
    public Car(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection, DEATH_TIME, "car");
    }
    
    /**
     * 
     * @param theX is the x-coordinate.
     * @param theY is the y-coordinate.
     * @param theDirection is the direction.
     * @param theDeathTime is the death time.
     * @param theName is the name of the vehicle.
     */
    public Car(final int theX, final int theY, final Direction theDirection,
               final int theDeathTime, final String theName) {
        super(theX, theY, theDirection, theDeathTime, theName);
    }


    /**
     * Checks to see if the vehicle can pass a specific terrain or light.
     * 
     * @param theTerrain is the Terrain of the city
     * @param theLight is the light of the city
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean passResult;
        if (theTerrain == Terrain.STREET) {
            passResult = true;
        } else if (theTerrain == Terrain.LIGHT && theLight == Light.YELLOW) {
            passResult = true;
        } else if (theTerrain == Terrain.LIGHT && theLight == Light.GREEN) {
            passResult = true;
        } else {
            passResult = false;
        }
        return passResult;
    }

    /**
     * Vehicle decides on which direction to take. 
     * 
     * @param theNeighbors is the surrounding terrain in a map
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        final Direction current = getDirection();
        Direction go = null;
        if (theNeighbors.get(current) == Terrain.STREET 
                        || theNeighbors.get(current) == Terrain.LIGHT) {
            go = getDirection(); 
        } else if (theNeighbors.get(current.left()) == Terrain.STREET 
                        || theNeighbors.get(current.left()) == Terrain.LIGHT) {
            go = current.left();
        } else if (theNeighbors.get(current.right()) == Terrain.STREET 
                        || theNeighbors.get(current.right()) == Terrain.LIGHT) {
            go = current.right();
        } else {
            go = current.reverse();
        }
        
        return go;
    }
}
