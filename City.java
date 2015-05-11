/**
 * City Class constructs a city on the board andhas multple 
 * mutator methods
 * 
 * Anna, Mary, Ryan, Jordan and Paul
 * Ticket to Ride: Legendary Asia
 */
public class City
{

    public String name;
    public int x,y;
    public boolean visited;
    public int cityVal;
    /**
     * Constrcts a city
     * 
     * @param n, the string name for the city
     * @param xCor, sets the x coordinate
     * @param yCor, sets the y coordinate
     */
    public City(String n, int xCor, int yCor,int v)
    {
        name = n;
        x = xCor;
        y = yCor;
        cityVal = v;
        visited = false;
    }

    /**
     * Accessor method to retrieve the city's name
     * @return, returns the city's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Accessor method to retrieve the city's x
     * coordinate
     * @return, returns the city's x
     */
    public int getX(){
        return x;
    }

    /**
     * Accessor method to retrieve the city's y
     * coordinate
     * @return, returns the city's y
     */
    public int getY(){
        return y;
    }

    /**
     * Gets the value of the city
     * 
     * @return the value of the city 
     */
    public int getCityVal(){
        return cityVal;
    }

    /**
     * Boolean to chack and see if the city has been
     * selected on the map
     * 
     * @param eX, the x coordiante of the city
     * @patam eY, the y coordinate of the city
     * 
     * @return, returns true if the city is selected
     * and false otherwise
     */
    public boolean isSelected(int eX, int eY)
    {
        if((eX >= x && eX < x+21) &&
            (eY >= y && eY < y +21))
        {
            return true;
        }
        return false;

    }

}