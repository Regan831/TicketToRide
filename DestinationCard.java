/**
 * A destination card specifies a specific route 
 * that is worth a set amount of points. If the 
 * correct cities are connected, a player gains
 * the points on the destination card and if they
 * did not capture the specific set of routes a 
 * a player loses those points at the end of the 
 * game
 * 
 * Anna, Mary, Ryan, Jordan and Paul
 * Ticket to Ride: Legendary Asia
 */
public class DestinationCard
{
    String path;
    City cityA, cityB;
    boolean completed;
    int value;

    /**
     * Constructor for a Destination card
     * 
     * @param p - the path
     * @param v - the amount of points it's worth
     * @param a - first city connected
     * @param b - second city connected
     * 
     */
    public DestinationCard(String p, int v, City a, City b){
        path = p;
        cityA = a;
        cityB = b;
        value =v;
        completed = false;
    }

    /**
     * Checks to see if the playe successfully 
     * completed a destination card
     * 
     * @return true if the path was completed and
     * false if it wasn't
     */
    public boolean pathCompleted(){
        return completed;
    }

    /**
     * Retrieves the point value of a 
     * destination card
     * 
     * @return the value of the card
     */
    public int getValue(){
        return value;
    }

    /**
     * Sets a destination card to completed
     */
    public void completed(){
        completed = true;
    }

    /**
     * Retreives the path of the destination
     * card.
     * 
     * @return the String value of the path
     */
    public String getPath()
    {
        return path;
    }

    /**
     * Gets the first city
     * 
     * @return the first city
     */
    public City getFirstCity()
    {
        return cityA;
    }

    /**
     * Gets the second city
     * 
     * @return the second city
     */
    public City getSecondCity()
    {
        return cityB;
    }

}