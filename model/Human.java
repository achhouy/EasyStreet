/*
 * TCSS 305
 * Assignment 3 - EasyStreet
 */

package model;

import java.util.ArrayList;
import java.util.Map;

/**
 * The human class for the easy street program.
 * 
 * @author Arrunn Chhouy
 * @version 1.0
 */
public class Human extends AbstractVehicle {
    
    /**
     * Death time to revive.
     */
    protected static final int DEATH_TIME = 45;
    
    /**
     * 
     * @param theX is the x-coordinate.
     * @param theY is the y-coordinate.
     * @param theDirection is the direction of the vehicle.
     * @param theTerrain is the terrain of the human.
     */
    public Human(final int theX, final int theY, final Direction theDirection,
                 final Terrain theTerrain) {
        super(theX, theY, theDirection, theTerrain, DEATH_TIME, "human");
    }

    /**
     * Checks to see if the vehicle can pass a specific terrain or light.
     * 
     * @param theTerrain is the Terrain of the city
     * @param theLight is the light of the city
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean pass = false;
        if (myTerrain == Terrain.STREET || myTerrain == Terrain.LIGHT) {
            pass = theTerrain == Terrain.LIGHT || theTerrain == Terrain.STREET;
        } else {
            pass = theTerrain == myTerrain; 
        }
        return pass;
    }

    /**
     * Vehicle decides on which direction to take. 
     * 
     * @param theNeighbors is the surrounding terrain in a map
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        int random;
        
        // Temporary variable
        Direction go = null;
        
        // Array that holds the four different direction
        final Direction[] arrayDirection = {Direction.valueOf('N'), Direction.valueOf('S'),
                        Direction.valueOf('W'), Direction.valueOf('E')};
      
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
