/*
 * TCSS 305
 * Assignment 3 - EasyStreet
 */


package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import model.Direction;
import model.Light;
import model.Terrain;
import model.Truck;

import org.junit.Test;

/**
 * This is the truck test to make sure the truck class 
 * is behaving correctly.
 * 
 * @author Arrunn
 * @version 2.0
 */
public class TruckTest {
    /**
     * The number of times to repeat a test to have a high probability that all
     * random possibilities have been explored.
     */
    private static final int TRIES_FOR_RANDOMNESS = 100;
    

    /**
     * Test the constructor of the truck to make sure 
     * it is being properly instantiated.
     * 
     */
    @Test
    public void testTruck() {
        final Truck t = new Truck(7, 18, Direction.WEST);
        
        assertEquals("Truck x coordinate not initialized correctly!", 7, t.getX());
        assertEquals("Truck y coordinate not initialized correctly!", 18, t.getY());
        assertEquals("Truck direction not initialized correctly!",
                     Direction.WEST, t.getDirection());
        assertEquals("Human death time not initialized correctly!", 0, t.getDeathTime());
        assertTrue("Human isAlive() fails initially!", t.isAlive());
    }

    /**
     * The test checks to see in different scenarios that the truck can pass
     * with different terrain in all four directions.
     */
    @Test
    public void testCanPass() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        // testTerrain values: Grass, Street, Light, Trail, and Wall
        for (final Terrain testTerrain : Terrain.values()) {
            // As long as the test terrain is not a wall
            if (testTerrain != Terrain.WALL) { 
                for (final Direction testDirection : Direction.values()) {
                    final Truck truck = new Truck(0, 0, testDirection);
                    neighbors.put(testDirection, testTerrain);
                    // go to each terrain type
                    for (final Light testLight : Light.values()) {
                        if (testTerrain == Terrain.STREET || testTerrain == Terrain.LIGHT) {
                            assertTrue("Neighboring terrain is a street or a light", 
                                   truck.canPass(neighbors.get(testDirection), testLight));
                        } else {
                            assertFalse("Neighboring terrain is trail or grass", 
                                       truck.canPass(neighbors.get(testDirection), testLight));
                        }
                    } 
                    
                }
            }
        }
    }

    /**
     * The testChooseDirection should test the truck if all neighboring directions
     * are street or light. 
     */
    @Test
    public void testChooseDirection() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        
        // It should work with all street or all light or mix of both
//        neighbors.put(Direction.NORTH, Terrain.STREET);
//        neighbors.put(Direction.WEST, Terrain.STREET);
//        neighbors.put(Direction.SOUTH, Terrain.STREET);
//        neighbors.put(Direction.EAST, Terrain.STREET);
        
//        neighbors.put(Direction.NORTH, Terrain.LIGHT);
//        neighbors.put(Direction.WEST, Terrain.LIGHT);
//        neighbors.put(Direction.SOUTH, Terrain.LIGHT);
//        neighbors.put(Direction.EAST, Terrain.LIGHT);
        
        neighbors.put(Direction.NORTH, Terrain.LIGHT);
        neighbors.put(Direction.WEST, Terrain.STREET);
        neighbors.put(Direction.SOUTH, Terrain.STREET);
        neighbors.put(Direction.EAST, Terrain.LIGHT);
        
        final Truck truck = new Truck(23, 56, Direction.NORTH);
        
        int tries = 0;
        boolean seenNorth = false;
        boolean seenWest = false;
        boolean seenEast = false;
        boolean seenSouth = false;
        
        while (tries < TRIES_FOR_RANDOMNESS) {
            tries = tries + 1;
            final Direction dir = truck.chooseDirection(neighbors);
            assertTrue("Truck cannot go that way", 
                               dir == Direction.NORTH 
                            || dir == Direction.WEST 
                            || dir == Direction.EAST);
            
            seenNorth = seenNorth || dir == Direction.NORTH;
            seenWest = seenWest || dir == Direction.WEST;
            seenEast = seenEast || dir == Direction.EAST;
            seenSouth = seenSouth || dir == Direction.SOUTH;
            
        }
        
        assertTrue("Truck randomness", seenNorth && seenWest && seenEast);
        assertFalse("Cannot reverse", seenSouth);

    }
    
    /**
     * The testChooseDirection2 test to see if the truck will move in two direction
     * if the North and South direction are wall.
     */
    @Test
    public void testChooseDirection2() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.NORTH, Terrain.WALL);
        neighbors.put(Direction.WEST, Terrain.STREET);
        neighbors.put(Direction.SOUTH, Terrain.STREET);
        neighbors.put(Direction.EAST, Terrain.LIGHT);
        
        final Truck truck = new Truck(10, 10, Direction.NORTH);
        
        int tries = 0;
        
        boolean seenNorth = false;
        boolean seenWest = false;
        boolean seenEast = false;
        boolean seenSouth = false;
        
        while (tries < TRIES_FOR_RANDOMNESS) {
            tries = tries + 1;
            final Direction dir = truck.chooseDirection(neighbors);
            assertTrue("truck did not choose a valid direction", dir == Direction.WEST 
                            || dir == Direction.EAST);
            
            seenWest = seenWest || dir == Direction.WEST;
            seenEast = seenEast || dir == Direction.EAST;
            seenNorth = seenNorth || dir == Direction.NORTH; //should never happen
            seenSouth = seenSouth || dir == Direction.SOUTH; //should never happen
        }
        
        assertTrue("Truck randomness", seenWest && seenEast);
        assertFalse("Blocked North", seenNorth);
        assertFalse("Cannot reverse", seenSouth);   
    }
    
    /**
     * The testChooseDirection3 should test to see if the truck will move in one direction
     * since N and W are all walls.
     */
    @Test
    public void testChooseDirection3() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.NORTH, Terrain.WALL);
        neighbors.put(Direction.WEST, Terrain.WALL);
        neighbors.put(Direction.SOUTH, Terrain.STREET);
        neighbors.put(Direction.EAST, Terrain.LIGHT);
        
        final Truck truck = new Truck(34, 7, Direction.NORTH);
        
        int tries = 0;
        
        boolean seenNorth = false;
        boolean seenWest = false;
        boolean seenEast = false;
        boolean seenSouth = false;
        
        while (tries < TRIES_FOR_RANDOMNESS) {
            tries = tries + 1;
            final Direction dir = truck.chooseDirection(neighbors);
            assertTrue("truck did not choose a valid direction", dir == Direction.EAST);
            
            seenEast = seenEast || dir == Direction.EAST;
            seenWest = seenWest || dir == Direction.WEST; // Should not occur
            seenNorth = seenNorth || dir == Direction.NORTH; //should not occur
            seenSouth = seenSouth || dir == Direction.SOUTH; //should not occur
        }
        
        assertTrue("Truck randomness", seenEast);
        assertFalse("Blocked North", seenNorth);
        assertFalse("Blocked West", seenWest);
        assertFalse("Cannot reverse", seenSouth);   
            
    }
    
    /**
     * The testChooseDirection4 should test to see if the truck will reverse
     * since N, W, and E are all walls.
     */
    @Test
    public void testChooseDirection4() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        // Test each different terrain value: GRASS, TRAIL, STREET, LIGHT, WALL
        for (final Terrain testTerrain : Terrain.values()) {
            if (testTerrain != Terrain.WALL) {
                continue;
            }
            
            // Creates a new truck object and it starts facing north
            final Truck truck = new Truck(0, 0, Direction.NORTH);  
            
            /* 
             * Testing that the truck reverses since the truck is facing North
             * and all neighboring direction except south has a wall 
             */
            neighbors.put(Direction.NORTH, Terrain.WALL);
            neighbors.put(Direction.WEST, Terrain.WALL);
            neighbors.put(Direction.EAST, Terrain.WALL);
            neighbors.put(Direction.SOUTH, testTerrain);
            /*   W
             * W ? W
             *   ?
             */            
            final Direction dir1 = truck.chooseDirection(neighbors);
            if (testTerrain == Terrain.STREET || testTerrain == Terrain.LIGHT) {
                assertSame("Truck should not go any other way " + dir1,
                           dir1 == Direction.SOUTH);
            } else {
                assertNotSame("Truck should not go " + dir1, dir1 == Direction.SOUTH);
            }
            
        }
    }
}
