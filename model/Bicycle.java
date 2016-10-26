/*
 * TCSS 305
 * Assignment 3 - EasyStreet
 */

package model;

import java.util.Map;

/**
 * The bicycle class for the easy street program.
 * 
 * @author Arrunn Chhouy
 * @version 1.0
 */
public class Bicycle extends AbstractVehicle {
    
    /**
     * Death time to revive. 
     */
    protected static final int DEATH_TIME = 25;
    
    /**
     * 
     * @param theX is the x-coordinate.
     * @param theY is the y-coordinate.
     * @param theDirection is the direction.
     */
    public Bicycle(final int theX, final int theY, final Direction theDirection) {
        super(theX, theY, theDirection, DEATH_TIME, "bicycle");
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
        if (theTerrain == Terrain.STREET || theTerrain == Terrain.TRAIL) {
            pass = true;
        } else if (theTerrain == Terrain.LIGHT && theLight == Light.GREEN) {
            pass = true;
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
        final Direction current = getDirection();
        Direction go;
        // If a trail is in front of the bike
        if (theNeighbors.get(current) == Terrain.TRAIL) {            
            go = current; 
        // If a trail is to the left of the bike    
        } else if (theNeighbors.get(current.left()) == Terrain.TRAIL) {
            go = current.left();
        // If a trail is to the right of the bike    
        } else if (theNeighbors.get(current.right()) == Terrain.TRAIL) {
            go = current.right();
        } else if (theNeighbors.get(current) == Terrain.STREET 
                        || theNeighbors.get(current) == Terrain.LIGHT) {
            go = current;
        } else if (theNeighbors.get(current.left()) == Terrain.STREET 
                        || theNeighbors.get(current.left()) == Terrain.LIGHT) {
            go = current.left();
        } else  if (theNeighbors.get(current.right()) == Terrain.STREET 
                        || theNeighbors.get(current.right()) == Terrain.LIGHT) {
            go = current.right();
        } else {
            go = current.reverse();
        }
        return go;
    }
}
