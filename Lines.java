import java.awt.Color;
/**
 * This class will hold the coordinates of two given cities used
 * in the ticket to ride class
 * 
 * Anna, Mary, Ryan, Jordan and Paul
 * Ticket to Ride: Legendary Asia
 */
public class Lines
{
    // instance variables for the x and y coordinates
    private int X1;
    private int Y1;
    private int X2;
    private int Y2;
    //instance variables for the color of the user
    private Color c;
    /**
     * Constructor for objects of class Lines. This will
     * take in two X coordinates and two Y coordinates
     * depending on the cities choosen in the ticket
     * to ride class
     * 
     * @param   firstX   X coordinate of first city
     * @param   secondX  X coordinate of second city
     * @param   firstY   Y coordinate of first city
     * @param   secondY  Y coordinate of second city
     * @param   c        Color of the user
     */
    public Lines(int firstX, int firstY, int secondX, 
                int secondY, Color C)
    {
        X1 = firstX;
        X2 = secondX;
        Y1 = firstY;
        Y2 = secondY;
        c = C;
    }

    /**
     * This will get the first X coordinate of the
     * first city
     * 
     * @return    the first X coordinate of the first city
     */
    public int getFirstX(){
        return X1;
    }

    /**
     * This will get the second X coordinate of the
     * first city
     * 
     * @return    the second X coordinate of the first city
     */
    public int getSecondX(){
        return X2;
    }

    /**
     * This will get the first Y coordinate of the
     * first city
     * 
     * @return    the first Y coordinate of the first city
     */
    public int getFirstY(){
        return Y1;
    }

    /**
     * This will get the second Y coordinate of the
     * first city
     * 
     * @return    the second Y coordinate of the first city
     */
    public int getSecondY(){
        return Y2;
    }

    /**
     * This will set the second Y coordinate of the
     * first city
     * 
     * @param    the second Y coordinate of the first city
     *           to be set
     */
    public void setSecondY(int newY){
        X2 = newY;
    }

    /**
     * This will set the first Y coordinate of the
     * first city
     * 
     * @param    the first Y coordinate of the first city
     *           to be set
     */
    public void setFirstY(int newY){
        X1 = newY;
    }

    /**
     * This will set the second X coordinate of the
     * first city
     * 
     * @param    the second X coordinate of the first city
     *           to be set
     */
    public void setSecondX(int newX){
        X2 = newX;
    }

    /**
     * This will set the first X coordinate of the
     * first city
     * 
     * @param    the first X coordinate of the first city
     *           to be set
     */
    public void setFirstX(int newX){
        X1 = newX;
    }

    /**
     * Retrieves the color of the line
     * 
     * @return the color of the line
     */
    public Color getColor()
    {
        return c;
    }

}