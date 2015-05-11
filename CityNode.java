/**
 * Creates a node version of the city locations
 * to be used for checking the connected routes
 * for the destination cards
 * 
 * Anna, Mary, Ryan, Jordan and Paul
 * Ticket to Ride: Legendary Asia
 */
public class CityNode {
    // edge's destination vertex 
    private int dest; 
    // link to next node in adjacency list	
    private CityNode next; // link to next node in adjacency list
    //were private 
    // accessor and modification methods for the class
    /**
     * Creates a city node with a destination and the next
     * city in line
     * 
     * @param dest - the destination
     * @param next - the next city 
     * 
     * @return a new city node
     */
    public CityNode( int dest, CityNode next ) { 
        this.dest = dest; 
        this.next = next; 
    }

    /**
     * Creates a city node with a destination 
     * 
     * @param dest - the destination
     *
     * @return a new city node
     */
    public CityNode( int dest ) { 
        this.dest = dest; 
        this.next = null;
    }

    /**
     * Retrieves the destination of a city node
     * 
     * @return the destination
     */
    public int getDest() { 
        return dest; 
    }

    /**
     * Sets the destination of the city node
     * 
     * @param dest - the int value of the destination
     */
    public void setDest( int dest ) {
        this.dest = dest;
    }

    /**
     * Sets the next city node
     * 
     * @param e - the city node that will follow
     * the specified cirty node
     */
    public void setNext( CityNode e ) { 
        next = e; 
    }

    /**
     * Retrieves the next city node in line
     * 
     * @return the city node thats next in line
     */
    public CityNode getNext( ) { 
        return next; 
    }

}