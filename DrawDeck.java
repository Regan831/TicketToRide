import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
/**
 * Creates the draw deck of cards. Populates an ArrayList of
 * Train cards that serve as the 110 card deck.
 * 
 * @author (your name) 
 * @version Ticket to Ride: Legendary Asia
 */
public class DrawDeck
{
    // creates the various traincards needed
    public TrainCard red,blue,black,brown,
    green,yellow,purple,white,loco;
    // array list that will store the cards
    public ArrayList<TrainCard> colorCards = 
        new ArrayList<TrainCard>();
    // picture that is displayed
    public BufferedImage deckPic;

    /**
     * Creates the initial draw deck of cards. Contians the
     * nine different cards with 12 instances of each and 14
     * of the locomotive card.
     */
    public DrawDeck(){
        // creates the train cards with all the proper pictures
        try{
            black = new TrainCard(ImageIO.read
                (new java.io.File("./images/black.gif")), "Black");
            blue = new TrainCard(ImageIO.read
                (new java.io.File("./images/blue.gif")), "Blue");
            brown = new TrainCard(ImageIO.read
                (new java.io.File("./images/brown.gif")), "Brown");
            green = new TrainCard(ImageIO.read
                (new java.io.File("./images/green.gif")), "Green");
            loco = new TrainCard(ImageIO.read
                (new java.io.File( "./images/loco.gif")), "Locomotive");
            purple = new TrainCard(ImageIO.read
                (new java.io.File("./images/purple.gif")), "Purple");
            red = new TrainCard(ImageIO.read
                (new java.io.File("./images/red.gif")), "Red");
            white = new TrainCard(ImageIO.read
                (new java.io.File("./images/white.gif")),"White");
            yellow = new TrainCard(ImageIO.read
                (new java.io.File("./images/yellow.gif")), "Yellow");    
        }
        catch(Exception e)
        {
            System.exit(-1);
        }

        // adds 12 of each card to the colorCards array list
        for (int i = 0; i < 12; i++){
            colorCards.add(loco);
            colorCards.add(white);
            colorCards.add(red);
            colorCards.add(purple);
            colorCards.add(yellow);
            colorCards.add(green);
            colorCards.add(brown);
            colorCards.add(blue);
            colorCards.add(black);
        }
        // adds 2 additional locomotive cards for a total of 14
        colorCards.add(loco);
        colorCards.add(loco);

    }

    /**
     * Selects a random card from the deck
     */
    public TrainCard draw(){
        if(!colorCards.isEmpty())
        {
            Random r = new Random();
            int cardToDraw = r.nextInt(colorCards.size());
            TrainCard c = colorCards.remove(cardToDraw);
            return c;
        }
        return null;
    }

    /**
     * Checks to see if the deck is empty.
     * 
     * @return  True if the deck is empty and false otherwise
     */
    public boolean hasCards()
    {
        return !colorCards.isEmpty();
    }

    /**
     * Checks the size of the deck
     * 
     * @return  The integer size of the deck
     */
    public int getSize()
    {
        return colorCards.size();
    }

    /**
     * Adds a new TrainCard to the colorCards deck.
     */
    public void add(TrainCard c)
    {
        colorCards.add(c);
    }

    /**
     * Displays the picture of the deck 
     * 
     * @return  The Buffered Image of the 
     */
    public BufferedImage getDeckPic(){
        try{
            deckPic = 
            ImageIO.read(new java.io.File("./images/DrawDeck.png"));
        }
        catch(Exception e)
        {
            System.exit(-1);
        }
        return deckPic;

    }
}