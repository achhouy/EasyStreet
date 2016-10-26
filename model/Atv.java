/*
 * TCSS 305
 * Assignment 3 - EasyStreet
 */

package model;

import java.util.ArrayList;
import java.util.Map;

/**
 * The atv class for the easy street program.
 * 
 * @author Arrunn Chhouy
 * @version 1.0
 */
public class Atv extends AbstractVehicle {
    
    /**
     * Death time to revive. 
     */
    protected static final int DEATH_TIME = 15;

    /**
     * 
     * @param theX is the x-coordinate.
     * @param theY is the y-coordinate.
     * @param theDirection is the direction.
     */
    public Atv(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection, DEATH_TIME, "atv");
    }

    /**
     * Checks to see if the vehicle can pass a specific terrain or light.
     * 
     * @param theTerrain is the Terrain of the city
     * @param theLight is the light of the city
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        return !(theTerrain == Terrain.WALL);
    }

    /**
     * Vehicle decides on which direction to take. 
     * 
     * @param theNeighbors is the surrounding terrain in a map
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        int random;
        
        // Holds the current direction of the vehicle
        final Direction currentDirection = getDirection();
        
        // Temporary variable
        Direction go = null;
        
        // Array that holds the four different direction
        final Direction[] arrayDirection = {getDirection(), currentDirection.left(),
                        currentDirection.right()};
        
        // An ArrayList that will store the direction
        final ArrayList<Direction> hold = new ArrayList<Direction>();
        
        // Iterates through the direction
        for (int i = 0; i < arrayDirection.length; i++) {
            
            // Checks to see if the neighboring terrain from each direction is street or light
            if (canPass(theNeighbors.get(arrayDirection[i]), null)) {
                hold.add(arrayDirection[i]);
            }
        }        
        random  = myRandom.nextInt(hold.size());
        go = hold.get(random);
      
        return go;
    }
}
