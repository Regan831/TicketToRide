import java.awt.image.*;
/**
 *The train card class constructs a card with nine possible
 *colors. Each card can be used to capture certain routes 
 *depending on their color. Gray routes can be captured 
 *by any color card and Locomotive cards are wild cards.
 *
 * Anna, Mary, Ryan, Jordan and Paul
 * Ticket to Ride: Legendary Asia
 */
public class TrainCard
{
    // Picture of the card
    public BufferedImage image;
    // Color of the card in a string
    public String color;
    
    /**
     * Constructs a train card giving it an image and a color.
     * The color is a string because there are some cards with 
     * colors that do not exist in java (brown, loco, purple, etc.)
     */
    public TrainCard(BufferedImage image1, String color1)
    {
        image = image1;
        color = color1; 
    }

    /**
     * Gets the color of the train
     * 
     * @return the color in String form
     */
    public String getColor(){
        return color;
    }

    /**
     * Gets the pictue that goes along with each 
     * train card
     * 
     * @return a Buffered image of the train card
     */
    public BufferedImage getPic(){
        return image;
    }

}