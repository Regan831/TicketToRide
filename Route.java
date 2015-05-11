import java.awt.*;
/**
 * Class that constructs a route and adds multiple capabilities
 * and values
 * 
 * Anna, Mary, Ryan, Jordan and Paul
 * Ticket to Ride: Legendary Asia
 */
public class Route
{
    // route cities
    City cityA, cityB;
    // route attributes
    int mountains,value,length;
    // route color
    String color;
    // ferry 
    boolean ferry;

    /**
     * Constructor for a route. A route takes in two cities
     * an integer length, an integer for the mountain count,
     * a String for color and a boolean for ferries.
     * 
     * @param a - the first city 
     * @param b - the second city
     * @param l - the length of the route
     * @param m - the number of mountains
     * @param c - the color of the route
     * @param f - true if the route has ferries and false
     * otherwise
     * 
     */
    public Route(City a, City b, int l, int m, String c, boolean f){
        cityA = a;
        cityB = b;
        mountains = m;
        length =l;
        color = c;
        ferry = f;
    }

    /**
     * Retrieves the length of the path plus any mountains
     * 
     * @return the length of the path
     */
    public int getLength(){
        return length + mountains;
    }

    /**
     * Retrieves the number of mountains a route has
     * 
     * @return the number of mountains
     */
    public int getMountains()
    {
        return mountains;
    }

    /**
     * Retrieves the score that coincides with a specific route
     * depending on its length, mountains and ferries. Does not
     * return the length of the route
     * 
     * @return an integer value of the score
     */
    public int getValue(){
        int m =0;
        if(mountains == 0) m = 0;
        if(mountains == 1) m = 2;
        if(mountains == 2) m = 4;
        if(length == 1) return 1 +m;
        if(length == 2) return 2+m;
        if(length == 3) return 4+m;
        if(length == 4) return 7+m; //not sure on these values.
        if(length == 5) return 10+m;
        if(length == 6) return 15+m;
        return 0;
    }

    /**
     * Retrieves the name of the first city
     * 
     * @return the string name of the city
     */
    public String getCityA (){
        return cityA.getName();
    }

    /**
     * Retrieves the name of the second city
     * 
     * @return the string name of the city
     */
    public String getCityB (){
        return cityB.getName();
    }

    /**
     * Retrieves the color of the first city
     * 
     * @return the color of the city in string form
     */
    public String getColor(){
        return color;
    }

    /** 
     * Checks to see if a route is a ferry route or not
     * 
     * @return - true if the route is a ferry route 
     * and false otherwise
     */
    public boolean isFerry(){
        return ferry;
    }

}