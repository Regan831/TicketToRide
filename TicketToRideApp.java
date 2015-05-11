import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import javax.imageio.*;
import java.util.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.net.*;
import java.applet.*;
import java.awt.FontMetrics.*;
/**
 * The TicketToRideApp class creates the actual gameplay environment
 * for the Ticket To Ride game. All gameplay logic is within this class
 * and it brings together all other object classes to create the GUI, 
 * establish the players, create the necessary decks and use these tools
 * to create the logic behind the players movements.
 * 
 * Anna, Mary, Ryan, Jordan and Paul
 * Ticket to Ride: Legendary Asia
 */
public class TicketToRideApp extends JApplet implements MouseListener
{
    // various pictures displayed on the board
    BufferedImage boardPic, table, destDeck, 
    yourDest, fs, aj, paper, firework;
    // Player hand consisting of the nine color cards
    BufferedImage[] handCards = new BufferedImage[9];
    // Create and initialize all destination and train cards.
    // Added decks, top 5 cards
    ArrayList<TrainCard>  trainDeck;
    // indicates the cities selected 
    City first, second;
    // the draw deck
    DrawDeck drawDeck = new DrawDeck();
    // five face-up train cards
    ArrayList<TrainCard> top5 = new
        ArrayList<TrainCard>();
    // discard pile
    ArrayList<TrainCard> discard = new
        ArrayList<TrainCard>();
    // the lines drawn when a route is captured
    ArrayList<Lines> cityLines = new
        ArrayList<Lines>();
    // destination cards
    ArrayList<DestinationCard> destinations;
    // long destination cards
    ArrayList<DestinationCard> longDestinations;
    // initializes the board
    Board board = new Board();
    // initializes the players
    Player[] players;
    // indicates the current and winning players
    Player current, winner;
    // indicates the number of players and keeps track 
    // of the last turns of the players
    int numPlayers, endTurnCount;
    // various booleans needed for gameplay
    boolean  cityPicked, drew, loco, gameOver, drewTwo,
    end, captured, turn, pickedDest, lastTurns, tie;

    /**
     * Called by the browser or applet viewer to inform this JApplet that it
     * has been loaded into the system. It is always called before the first 
     * time that the start method is called.
     */
    public void init()
    {
        // this is a workaround for a security conflict with some browsers
        // including some versions of Netscape & Internet Explorer which do 
        // not allow access to the AWT system event queue which JApplets do 
        // on startup to check access. May not be necessary with your browser. 
        try{
            table = ImageIO.read(new java.io.File(".//images//wood.jpg"));
            boardPic = ImageIO.read(new java.io.File(".//images//board.jpg"));
            destDeck = ImageIO.read(new java.io.File(".//images//dest.png"));
            handCards[0] = 
            ImageIO.read(new java.io.File("./images/red_rotate.gif"));
            handCards[1] = 
            ImageIO.read(new java.io.File("./images/blue_rotate.gif"));
            handCards[2] = 
            ImageIO.read(new java.io.File("./images/yellow_rotate.gif"));
            handCards[3] = 
            ImageIO.read(new java.io.File("./images/green_rotate.gif"));
            handCards[4] = 
            ImageIO.read(new java.io.File("./images/brown_rotate.gif"));
            handCards[5] = 
            ImageIO.read(new java.io.File("./images/white_rotate.gif"));
            handCards[6] = 
            ImageIO.read(new java.io.File("./images/black_rotate.gif"));
            handCards[7] = 
            ImageIO.read(new java.io.File("./images/purple_rotate.gif"));
            handCards[8] = 
            ImageIO.read(new java.io.File("./images/loco_rotate.gif"));
            yourDest = 
            ImageIO.read(new java.io.File("./images/yourdest.gif"));
            fs = ImageIO.read(new java.io.File("fluttershy.png"));
            aj = ImageIO.read(new java.io.File("aj.png"));
            paper = ImageIO.read(new java.io.File("p.jpg"));
            firework = ImageIO.read(new java.io.File("confetti.jpg"));
        }
        catch(Exception e)
        {
            System.exit(-1);
        }

        JRootPane rootPane = this.getRootPane();    
        rootPane.putClientProperty("defeatSystemEventQueueCheck",
                                    Boolean.TRUE);
        addMouseListener(this);

        nameInput();
        numPlayers = players.length;
        cityPicked = drew = drewTwo = loco = 
        end = captured = turn = pickedDest = false;

        // Adds 5 cards to the top5 array list to display
        destinations = board.getDestDeck();
        longDestinations = board.getLongDestDeck();
        Collections.shuffle(destinations);
        Collections.shuffle(longDestinations);
        dealFirstDest();
        drawFirstFour();
        replaceTopFive();
        while(checkTopFive())
        {
            replaceTopFive();
        }
        JOptionPane.showMessageDialog(null,
            players[0].getName()+"'s turn!");
        players[0].changeTurn();
        current = players[0];

    }

    /**
     * Fills all players' hands with
     * 4 train cards at the beginning of game. 
     */
    public void drawFirstFour()
    {
        // deals out the 4 cards from the draw deck
        //checking for a certain name
        for(Player p : players)
        {
            if(p.getName().equals("Show me the money"))
            {
                for(int j = 0; j < 5000; j++)
                {
                    p.addCard(new TrainCard(null, "Locomotive"));
                }
            }

            for(int i = 0; i < 4; i++)
            {
                p.addCard(drawDeck.draw());
            }
        }   
    }

    /**
     * Gives players the initial destination tickets
     * to pick from at the start of the game
     */
    public void dealFirstDest()
    {
        // for each player, display their destination card options and 
        // have them pick at least 2
        for(Player p : players)
        {
            JOptionPane.showMessageDialog(null,"Dealing " +
                p.getName()+"'s destination tickets!");
            // players options for their destination cards
            DestinationCard[] cards = new DestinationCard[4];
            // adds three destination cards
            for(int k = 0; k < 3; k++)
            {
                cards[k] = destinations.remove(0);  
            }
            // adds one long destination card
            cards[3] = longDestinations.remove(0);
            // check boxes for the players options
            JCheckBox[] boxes = new JCheckBox[4];
            // creates an list of options in a JOption pain
            for(int i = 0; i < 4; i++)
            {
                boxes[i] = new JCheckBox(cards[i].getPath() + 
                    " for " + cards[i].getValue() + " points");
            }   

            int count = 0;
            // makes sure at least 2 cards are chosen
            do 
            {
                count = 0;
                JOptionPane.showMessageDialog(null,boxes,"Pick your "
                    +"destinations", JOptionPane.QUESTION_MESSAGE);
                for(int n = 0; n < 4; n++)
                {
                    if(boxes[n].isSelected())
                    {
                        count++;
                    }
                }
            } while(count <= 1);

            for(int j = 0; j < 4; j++)
            {
                if(boxes[j].isSelected())
                {
                    p.addDest(cards[j]);
                }
                else if(!boxes[j].isSelected() && j != 3)
                {
                    destinations.add(cards[j]);
                }
            }

        }
    }

    /**
     * Gives the user three destination cards 
     * to choose from. The user must select at
     * least one. All unselected cards are put
     * on bottom of destination deck.
     */
    public void dealThreeDests()
    {
        // Gives a player three destination cards to pick from 
        // when a player wants to pick up more during the game
        DestinationCard[] cards = new DestinationCard[4];
        // three options to choose from
        for(int k = 0; k < 3; k++)
        {
            cards[k] = destinations.remove(0);  
        }
        JCheckBox[] boxes = new JCheckBox[4];
        for(int i = 0; i < 3; i++)
        {
            boxes[i] = new JCheckBox(cards[i].getPath() + 
                " for " + cards[i].getValue() + " points");
        }   
        for(int i = 0; i < 3; i++)
        {
            boxes[i] = new JCheckBox(cards[i].getPath() + 
                " for " + cards[i].getValue() + " points");
        }   
        int count = 0;
        // playercan choose at most 3 and at least 1
        do 
        {
            count = 0;
            JOptionPane.showMessageDialog(null,boxes,"Pick your "
                +"destinations", JOptionPane.QUESTION_MESSAGE);
            for(int n = 0; n < 3; n++)
            {
                if(boxes[n].isSelected())
                {
                    count++;
                }
            }
        } while(count <= 0);

        for(int j = 0; j < 3; j++)
        {
            if(boxes[j].isSelected())
            {
                current.addDest(cards[j]);
            }
            else if(!boxes[j].isSelected())
            {
                destinations.add(cards[j]);
            }
        }
    }

    /**
     * Replaces the top 5 cards for
     * selection.
     */
    public void replaceTopFive()
    {

        for(int i = top5.size()-1; i > -1; i--)
        {
            discard.add(top5.remove(i));
        }
        // Adds 5 cards to the top5 array list to display
        if(drawDeck.getSize() >=5)
        {
            for(int j = 0; j <5; j++)
            {
                top5.add(drawDeck.draw());
            }
        }
        else
        {
            for(int i = drawDeck.getSize()-1; i > -1; i--)
            {
                discard.add(drawDeck.draw());
            }
            discardToMain();
            for(int j = 0; j <5; j++)
            {
                top5.add(drawDeck.draw());
            }
        }

        repaint();
    }

    /**
     * Shuffles the discard pile back into the main 
     * deck, as long as there are cards in 
     * the discard pile.
     */
    public void discardToMain()
    {
        // When/if the cards run out, all the cards
        // in the discard pile are shuffled and then
        // turned into the new deck
        if(discard.size() > 0)
        {
            JOptionPane.showMessageDialog(null,
                "Shuffling discard pile back into deck!");
            for(int i = discard.size()-1; i > -1; i--)
            {
                drawDeck.add(discard.remove(i));
            }

            discard = new ArrayList<TrainCard>();
        }
        else
        {
            JOptionPane.showMessageDialog(null,
                "Discard pile empty! Cannot shuffle.");
        }
        repaint();
    }

    /**
     * Checks the top five cards to make sure there is
     * not more than three Locomotive cards.
     * 
     * @return true if there are more than three and 
     * false otherwise.
     */
    public boolean checkTopFive()
    {
        //keeps track of the number of locomotive cards 
        int locoCount = 0;
        for(int i = 0; i < top5.size(); i++)
        {
            TrainCard temp = top5.get(i);
            if(temp.getColor().equals("Locomotive"))
            {
                locoCount++;
            }
        }
        //if the number of locomotive cards is greater than
        //of equal to three, return true else false
        if(locoCount >= 3)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Prompts the user to input the number of
     * players to play the game (2 or 3), then
     * allows the player to enter their 
     * distinct names.
     */
    public void nameInput(){
        boolean playersChosen = false;
        boolean diffNames = false;
        int numPlayers = 0;
        //makes sure number entered is valid 2 or 3
        while(playersChosen != true){
            String strNumPlayers = JOptionPane.showInputDialog(null,
                    "Number of players",
                    "How many players are playing? (2 or 3)",
                    JOptionPane.QUESTION_MESSAGE);

            if((strNumPlayers.equals("2"))||(strNumPlayers.equals("3"))){
                playersChosen = true;
                numPlayers = Integer.parseInt(strNumPlayers);
                players = new Player[numPlayers];
            }

        }

        //if 2 players make sure not same name
        if ( numPlayers == 2 ) {
            String playerName = JOptionPane.showInputDialog(null,
                    "Player  1's name",
                    "What is Player 1's name?",
                    JOptionPane.QUESTION_MESSAGE);
            // assigns blue to player one
            players[0] = new Player(playerName, Color.BLUE);
            JOptionPane.showMessageDialog(null,
                "You are the blue player!");
            while(diffNames != true){
                String playerName2 = JOptionPane.showInputDialog(null,
                        "Player 2's name",
                        "What is Player 2's name?",
                        JOptionPane.QUESTION_MESSAGE);
                // assigns red to the second player as their inputted
                // name isn't the same as the first
                if ( !playerName2.equals(players[0].getName()) ) {
                    diffNames = true;
                    JOptionPane.showMessageDialog(null,
                        "You are the red player!");
                    players[1] = new Player(playerName2, Color.RED);
                }
            }
        }
        //if 3 players make sure 3 diff names
        else if ( numPlayers == 3 ) {
            String playerName = JOptionPane.showInputDialog(null,
                    "Player 1's name",
                    "What is Player 1's name?",
                    JOptionPane.QUESTION_MESSAGE);
            JOptionPane.showMessageDialog(null,
                "You are the blue player!");
            players[0] = new Player(playerName,Color.BLUE);

            while(diffNames != true){
                String playerName2 = JOptionPane.showInputDialog(null,
                        "Player 2's name",
                        "What is Player 2's name?",
                        JOptionPane.QUESTION_MESSAGE);
                if ( !playerName2.equals(players[0].getName()) ) {
                    JOptionPane.showMessageDialog(null,
                        "You are the red player!");
                    players[1] = new Player(playerName2,Color.RED);
                    while(diffNames != true){
                        String playerName3 = JOptionPane.showInputDialog(null,
                                "Player 3's name",
                                "What is Player 3's name?",
                                JOptionPane.QUESTION_MESSAGE);
                        // assigns red to the third player as their inputted
                        // name isn't the same as the first or second
                        if ( !playerName3.equals(players[0].getName()) &&
                        !playerName3.equals(players[1].getName())) {
                            diffNames = true;
                            JOptionPane.showMessageDialog(null,
                                "You are the green player!");
                            players[2] = new Player(playerName3,Color.GREEN);
                        }
                    }
                }
            }
        }
    }

    /**
     * Checks the route to see if it's a double route or
     * single route
     * 
     * @param begin the first city
     * @param end the second city
     * @return 2 if its a double, 1 if its a single and 0 if
     * there isn't a route
     */
    public int checkRoute(City begin, City end)
    {
        int beginVal = begin.getCityVal();
        int endVal = end.getCityVal();

        if(board.getRoute(beginVal, endVal) == 1)
        {
            return 1;
        }
        else if (board.getRoute(beginVal, endVal) == 2)
        {
            return 2;
        }
        return 0;

    }

    /**
     * Changes the turn accordingly.
     */
    public void nextTurn()
    {
        // Changes turns between players in the instance of
        // 2 players and with 3 players
        if(numPlayers == 2)
        {
            // changes the players turn from the first player
            // to the second player if it's the first
            // player's turn, else the third player if
            // its the second players turn, else the first 
            // player if it's the third players turn
            if(players[0].isTurn())
            {
                players[0].changeTurn();
                players[1].changeTurn();
                JOptionPane.showMessageDialog(null,
                    players[1].getName()+"'s turn!");
                current = players[1];

            }
            else
            {
                players[1].changeTurn();
                players[0].changeTurn();
                JOptionPane.showMessageDialog(null,
                    players[0].getName()+"'s turn!");
                current = players[0];
            }         

        }
        else if(numPlayers == 3)
        {
            if(players[0].isTurn())
            {
                players[0].changeTurn();
                players[1].changeTurn();
                JOptionPane.showMessageDialog(null,
                    players[1].getName()+"'s turn!");
                current = players[1];

            }
            else if(players[1].isTurn())
            {
                players[1].changeTurn();
                players[2].changeTurn();
                JOptionPane.showMessageDialog(null,
                    players[2].getName()+"'s turn!");
                current = players[2];
            }   
            else
            {
                players[2].changeTurn();
                players[0].changeTurn();
                JOptionPane.showMessageDialog(null,
                    players[0].getName()+"'s turn!");
                current = players[0];
            }

        }
        first = second = null;
        cityPicked = drew = drewTwo = captured = loco = 
        turn = pickedDest = false;
        // displays a prompt if its the players last turn
        if(lastTurns)
        {
            JOptionPane.showMessageDialog(null,
                "It's your last turn! Make it count!");
            endTurnCount++;
        }
        repaint();
    }   

    /**
     * Called by the browser or applet viewer to inform this JApplet that it 
     * should start its execution. It is called after the init method and 
     * each time the JApplet is revisited in a Web page. 
     */
    public void start()
    {
        // provide any code requred to run each time 
        // web page is visited
    }

    /** 
     * Called by the browser or applet viewer to inform this JApplet that
     * it should stop its execution. It is called when the Web page that
     * contains this JApplet has been replaced by another page, and also
     * just before the JApplet is to be destroyed. 
     */
    public void stop()
    {
        // provide any code that needs to be run when page
        // is replaced by another page or before JApplet is destroyed 
    }

    /**
     * Checks to see which of the five draw cards was chosen
     * 
     * @param xVal the x value of the clicked position
     * @param yVal the y value of the clicked position
     * @return 0-5 depending on which card was chosen or -1
     * if there wasn't a card clicked
     */
    public int trainClicked(int xVal, int yVal){
        if((xVal >= 860 && xVal < 970 )){
            if((yVal >= 20 && yVal < 90))return 0;
            else if((yVal >= 100 && yVal < 170))return 1;
            else if((yVal >= 180 && yVal < 250))return 2;
            else if((yVal >= 260 && yVal < 330))return 3;
            else if((yVal >= 340 && yVal < 410))return 4;
            else if((yVal >= 420 && yVal < 490))return 5;
        }
        return -1;
    }

    /**
     * Captures a route 
     * 
     * @param r1 the route that is to be captured
     */
    public void capture(Route r1)
    {
        // city names
        String cityA = r1.getCityA().toString(); 
        String cityB = r1.getCityB().toString();
        // number of mountains in the route
        int mtns = r1.getMountains();
        // captures route
        board.capture(first.getCityVal(),
            second.getCityVal());
        captured = true;
        int x1, x2, y1, y2;
        x1 = first.getX();
        x2 = second.getX();
        y1 = first.getY();
        y2 = second.getY();

        Player god = null;
        boolean isGod = false;
        // checks for a certain name to do certain things
        for(int i = 0; i < players.length; i++)
        {
            String godCheck = players[i].getName();
            if(godCheck.equals("iddqd"))
            {
                god = players[i];
                isGod = true;
            }
        }
        // does certain things if a player has a certain name
        if(!isGod)
        {
            current.updateTrains(r1.getLength());
            //updates score
            current.updateScore(r1.getValue());
            cityLines.add(new Lines (x1, y1, x2, y2, current.getColor()));
            current.addEdge(first.getCityVal(),second.getCityVal());
            current.updateMtns(mtns);
        }
        else
        {
            cityLines.add(new Lines (x1, y1, x2, y2, god.getColor()));
            god.addEdge(first.getCityVal(),second.getCityVal());
            current.updateTrains(r1.getLength());
            god.updateMtns(mtns);
            god.updateScore(r1.getValue());
        }
        repaint();
        // display for a successfully captured routes
        if(!isGod)
        {
            JOptionPane.showMessageDialog(null,
                "You captured the route from " +
                cityA + " to " + cityB + "!");
        }
        else
        {

            JOptionPane.showMessageDialog(null,
                "iddqd captured the route from " +
                cityA + " to " + cityB + "!");
        }
    }

    /**
     * This method checks all the possibilites that a 
     * player has for claiming a route
     * depending on the cards in the players hand.
     * It presents the player with every option possible 
     * depending on the route chosen.
     * 
     * @param r1 the route that is to be checked
     */
    public void checkHand(Route r1)
    {
        String c = r1.getColor();
        int rLength = r1.getLength() - r1.getMountains();
        boolean loco = false;
        boolean col = false;
        if(current.getTrains() >= r1.getLength())
        {
            ButtonGroup group = new ButtonGroup();
            ArrayList<JRadioButton> buttons = 
                new ArrayList<JRadioButton>();
            int locoCount = current.cardCount("Locomotive");
            if(!c.equals("Gray"))
            {
                if(!r1.isFerry())
                {
                    int rColorCount = current.cardCount(c);
                    for(int i = 0; i < locoCount+1; i++)
                    {
                        for(int j = rColorCount; j > -1; j--)
                        {
                            if((i+j) == rLength)
                            {
                                JRadioButton jbut = new JRadioButton(j 
                                        + " " + c  + " train cards and " 
                                        + i + " locomotive cards");
                                buttons.add(jbut);
                                group.add(jbut);
                            }

                        }

                    }
                    if(group.getButtonCount() == 0)
                    {
                        JOptionPane.showMessageDialog(null,
                            "You don't have the cards for that!");  
                    }
                    else
                    {
                        JRadioButton[] butArray = buttons.toArray
                            (new JRadioButton[buttons.size()]);
                        butArray[0].setSelected(true);
                        JOptionPane.showMessageDialog(null,butArray,
                            "Choose what you would like to use", 
                            JOptionPane.QUESTION_MESSAGE);

                        String ans = "";

                        for(int k = 0; k < butArray.length; k++)
                        {
                            if(butArray[k].isSelected())
                            {
                                ans = butArray[k].getText();
                            }
                        }
                        showStatus(ans);
                        String[] getInfo = ans.split(" ");
                        int coloredCards = Integer.parseInt(getInfo[0]);
                        String colorUsed = getInfo[1];
                        int locoCards = Integer.parseInt(getInfo[5]);
                        if(locoCards > 0)
                        {
                            loco = true;
                        }
                        if(coloredCards > 0)
                        {
                            col = true;
                        }

                        while(loco || col)
                        {
                            for(int n = 0; n < current.hand.size(); n++)
                            {
                                String cur = current.hand.get(n).getColor();
                                if(cur.equals(colorUsed) && coloredCards > 0)
                                {
                                    TrainCard toDiscard = 
                                        current.hand.remove(n);
                                    discard.add(toDiscard);
                                    coloredCards--;
                                    if(coloredCards == 0)
                                    {
                                        col = false;
                                    }
                                }
                                else if(cur.equals("Locomotive") 
                                            && locoCards > 0)
                                {
                                    TrainCard toDiscard = 
                                        current.hand.remove(n);
                                    discard.add(toDiscard);
                                    locoCards--;
                                    if(locoCards == 0)
                                    {
                                        loco = false;
                                    }
                                }
                            }
                        }

                        repaint();
                        capture(r1);

                    }   
                }
                else
                {
                    if(locoCount == 0)
                    {
                        JOptionPane.showMessageDialog(null,
                            "You need at least 1 locomotive card"
                            + " to capture a ferry route!");
                    }
                    else
                    {
                        int rColorCount = current.cardCount(c);
                        for(int i = 1; i < locoCount+1; i++)
                        {
                            for(int j = rColorCount; j > -1; j--)
                            {
                                if((i+j) == rLength)
                                {
                                    JRadioButton jbut = new JRadioButton(j + 
                                        " " + c + " train cards and " + i + 
                                        " locomotive cards");
                                    buttons.add(jbut);
                                    group.add(jbut);
                                }

                            }

                        }
                        if(group.getButtonCount() == 0)
                        {
                            JOptionPane.showMessageDialog(null,
                                "You don't have the cards for that!");  
                        }
                        else
                        {
                            JRadioButton[] butArray = buttons.toArray
                                (new JRadioButton[buttons.size()]);
                            butArray[0].setSelected(true);
                            JOptionPane.showMessageDialog(null,butArray,
                                "Choose what you would like to use", 
                                JOptionPane.QUESTION_MESSAGE);

                            String ans = "";

                            for(int k = 0; k < butArray.length; k++)
                            {
                                if(butArray[k].isSelected())
                                {
                                    ans = butArray[k].getText();
                                }
                            }
                            showStatus(ans);
                            String[] getInfo = ans.split(" ");
                            int coloredCards = Integer.parseInt(getInfo[0]);
                            String colorUsed = getInfo[1];
                            int locoCards = Integer.parseInt(getInfo[5]);
                            if(locoCards > 0)
                            {
                                loco = true;
                            }
                            if(coloredCards > 0)
                            {
                                col = true;
                            }

                            while(loco || col)
                            {
                                for(int n = 0; n < current.hand.size(); n++)
                                {
                                    String cur = 
                                        current.hand.get(n).getColor();
                                    if(cur.equals(colorUsed) && 
                                        coloredCards > 0)
                                    {
                                        TrainCard toDiscard = 
                                            current.hand.remove(n);
                                        discard.add(toDiscard);
                                        coloredCards--;
                                        if(coloredCards == 0)
                                        {
                                            col = false;
                                        }

                                    }
                                    else if(cur.equals("Locomotive") && 
                                        locoCards > 0)
                                    {
                                        TrainCard toDiscard = 
                                            current.hand.remove(n);
                                        discard.add(toDiscard);
                                        locoCards--;
                                        if(locoCards == 0)
                                        {
                                            loco = false;
                                        }
                                    }
                                }
                            }
                            repaint();
                            capture(r1);

                        }   
                    }
                }
            }
            else
            {
                String[] colorOps = {"Blue","Green","Red","White","Black",
                        "Brown","Purple","Yellow", "Locomotive"};
                ButtonGroup colorGroup = new ButtonGroup();
                JRadioButton[] colorButs = new JRadioButton[9];
                for(int m = 0; m < colorOps.length; m++)
                {
                    colorButs[m] = new JRadioButton(colorOps[m]);
                    colorGroup.add(colorButs[m]);  

                }
                colorButs[0].setSelected(true);
                JOptionPane.showMessageDialog(null,colorButs,
                    "Choose color to use", 
                    JOptionPane.QUESTION_MESSAGE);
                String ans = "";
                for(int k = 0; k < colorButs.length; k++)
                {
                    if(colorButs[k].isSelected())
                    {
                        ans = colorButs[k].getText();
                    }
                }

                if(!r1.isFerry())
                {
                    if(ans.equals("Locomotive"))
                    {
                        if(locoCount >= rLength)
                        {
                            while(rLength > 0)
                            {
                                for(int n=0; n<current.hand.size(); n++)
                                {

                                    String cur = 
                                        current.hand.get(n).getColor();
                                    if(cur.equals("Locomotive") && 
                                        rLength > 0)
                                    {
                                        TrainCard toDiscard = 
                                            current.hand.remove(n);
                                        discard.add(toDiscard);
                                        rLength--;
                                    }

                                }
                            }
                            repaint();
                            capture(r1);

                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,
                                "You don't have the cards for that!");     
                        }

                    }
                    else
                    {
                        int rColorCount = current.cardCount(ans);
                        for(int i = 0; i < locoCount+1; i++)
                        {
                            for(int j = rColorCount; j > -1; j--)
                            {
                                if((i+j) == rLength)
                                {
                                    JRadioButton jbut = new JRadioButton(j + 
                                        " " + ans + " train cards and " + i 
                                        + " locomotive cards");
                                    buttons.add(jbut);
                                    group.add(jbut);
                                }

                            }

                        }
                        if(group.getButtonCount() == 0)
                        {
                            JOptionPane.showMessageDialog(null,
                                "You don't have the cards for that!");  
                        }
                        else
                        {
                            JRadioButton[] butArray = buttons.toArray
                                (new JRadioButton[buttons.size()]);
                            butArray[0].setSelected(true);
                            JOptionPane.showMessageDialog(null,butArray,
                                "Choose what you would like to use", 
                                JOptionPane.QUESTION_MESSAGE);

                            ans = "";

                            for(int k = 0; k < butArray.length; k++)
                            {
                                if(butArray[k].isSelected())
                                {
                                    ans = butArray[k].getText();
                                }
                            }
                            showStatus(ans);
                            String[] getInfo = ans.split(" ");
                            int coloredCards = Integer.parseInt(getInfo[0]);
                            String colorUsed = getInfo[1];
                            int locoCards = Integer.parseInt(getInfo[5]);
                            showStatus(""+coloredCards);
                            if(locoCards > 0)
                            {
                                loco = true;
                            }
                            if(coloredCards > 0)
                            {
                                col = true;
                            }

                            while(loco || col)
                            {
                                for(int n = 0; n < current.hand.size(); n++)
                                {
                                    TrainCard curCard = current.hand.get(n);
                                    String cur = curCard.getColor();
                                    if(cur.equals(colorUsed) && 
                                        coloredCards > 0)
                                    {
                                        TrainCard toDiscard = 
                                            current.hand.remove(n);
                                        discard.add(toDiscard);
                                        coloredCards--;
                                        if(coloredCards == 0)
                                        {
                                            col = false;
                                        }
                                    }
                                    else if(cur.equals("Locomotive") && 
                                        locoCards > 0)
                                    {
                                        TrainCard toDiscard = 
                                            current.hand.remove(n);
                                        discard.add(toDiscard);
                                        locoCards--;
                                        if(locoCards == 0)
                                        {
                                            loco = false;
                                        }
                                    }

                                }
                            }

                            repaint();
                            capture(r1);

                        }
                    }
                }
                else
                {
                    if(ans.equals("Locomotive"))
                    {
                        if(locoCount == 0)
                        {
                            JOptionPane.showMessageDialog(null,
                                "You need at least 1 locomotive card"
                                + " to capture a ferry route!");
                        }
                        else
                        {
                            if(locoCount >= rLength)
                            {
                                for(int n=0; n<current.hand.size(); n++)
                                {
                                    String cur = 
                                        current.hand.get(n).getColor();

                                    if(cur.equals("Locomotive") &&
                                        rLength > 0)
                                    {
                                        TrainCard toDiscard = 
                                            current.hand.remove(n);
                                        discard.add(toDiscard);
                                        rLength--;
                                    }

                                }
                                repaint();
                                capture(r1);

                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null,
                                    "You don't have the cards for that!");     
                            }

                        }
                    }
                    else
                    {
                        int rColorCount = current.cardCount(ans);
                        for(int i = 1; i < locoCount+1; i++)
                        {
                            for(int j = rColorCount; j > -1; j--)
                            {
                                if((i+j) == rLength)
                                {
                                    JRadioButton jbut = new JRadioButton(j + 
                                        " " + ans + " train cards and " + i 
                                        + " locomotive cards");
                                    buttons.add(jbut);
                                    group.add(jbut);
                                }

                            }

                        }
                        if(group.getButtonCount() == 0)
                        {
                            JOptionPane.showMessageDialog(null,
                                "You don't have the cards for that!");  
                        }
                        else
                        {
                            JRadioButton[] butArray = buttons.toArray
                                (new JRadioButton[buttons.size()]);
                            butArray[0].setSelected(true);
                            JOptionPane.showMessageDialog(null,butArray,
                                "Choose what you would like to use", 
                                JOptionPane.QUESTION_MESSAGE);

                            ans = "";

                            for(int k = 0; k < butArray.length; k++)
                            {
                                if(butArray[k].isSelected())
                                {
                                    ans = butArray[k].getText();
                                }
                            }
                            showStatus(ans);
                            String[] getInfo = ans.split(" ");
                            int coloredCards = Integer.parseInt(getInfo[0]);
                            String colorUsed = getInfo[1];
                            int locoCards = Integer.parseInt(getInfo[5]);
                            if(locoCards > 0)
                            {
                                loco = true;
                            }
                            if(coloredCards > 0)
                            {
                                col = true;
                            }

                            while(loco || col)
                            {
                                for(int n = 0; n < current.hand.size(); n++)
                                {

                                    String cur = 
                                        current.hand.get(n).getColor();
                                    if(cur.equals(colorUsed) && 
                                        coloredCards > 0)
                                    {
                                        TrainCard toDiscard = 
                                            current.hand.remove(n);
                                        discard.add(toDiscard);
                                        coloredCards--;
                                        if(coloredCards == 0)
                                        {
                                            col = false;
                                        }
                                    }
                                    else if(cur.equals("Locomotive") && 
                                        locoCards > 0)
                                    {
                                        TrainCard toDiscard = 
                                            current.hand.remove(n);
                                        discard.add(toDiscard);
                                        locoCards--;
                                        if(locoCards == 0)
                                        {
                                            loco = false;
                                        }
                                    }
                                }
                            }
                            repaint();
                            capture(r1);

                        }

                    }

                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,
                "You don't have the trains for that!");
        }
    }

    /**
     * Mouse clicked method for the applet
     */
    public void mouseClicked(MouseEvent e){

        // if game is over no mouseClicked events
        if(gameOver)
        {
            return;
        }
        // if game is not over get X and Y
        int xVal = e.getX();
        int yVal = e.getY();
        //showStatus("(" + xVal + ", " + yVal + ")");
        // if the player clicks on the board 
        if(xVal < 850 && yVal < 550 && !drew)
        {
            // checks to see what cities were selected
            City clickedCity = board.cityClicked(xVal,yVal);
            String nameOfCity = clickedCity.getName();
            JOptionPane.showMessageDialog(null,nameOfCity);

            if(first == null)
            {
                first = clickedCity;
                cityPicked = true;
                showStatus("First city selected");
            }
            else if(second == null)
            {
                second = clickedCity;
                showStatus("Second city selected");

                if(checkRoute(first,second) == 1)
                {
                    // new route
                    Route r1 = board.getRoute(first, second);
                    checkHand(r1);

                }
                else if (checkRoute(first,second) == 2)
                {
                    // arraylist of 2 double routes
                    ArrayList<Route> r2 = board.getArray(first, second);
                    // first route

                    Route firstR = r2.get(0);
                    //second route
                    Route secondR = r2.get(1);

                    String c1 = firstR.getColor();
                    String c2 = secondR.getColor();

                    int m1 = firstR.getMountains();
                    int m2 = secondR.getMountains();

                    if((m1 == m2) && c1.equals(c2))
                    {
                        checkHand(firstR);
                    }
                    else
                    {

                        ButtonGroup dubRoute = new ButtonGroup();
                        JRadioButton[] dubBut = new JRadioButton[2];

                        String mount1 = "";
                        String mount2 = "";
                        if(m1 == 1)
                        {
                            mount1 = "with 1 mountain";
                        }
                        else if(m1 > 1)
                        {
                            mount1 = "with " + m1 + 
                            " mountains";
                        }
                        if(m2 == 1)
                        {
                            mount2 = "with 1 mountain";
                        }
                        else if(m2 > 1)
                        {
                            mount2 = "with " + m2 +
                            " mountains";
                        }

                        dubBut[0] = new JRadioButton(c1 + " route " 
                            + mount1);
                        dubBut[1] = new JRadioButton(c2 + " route " 
                            + mount2);

                        dubRoute.add(dubBut[0]);
                        dubRoute.add(dubBut[1]);

                        dubBut[0].setSelected(true);
                        JOptionPane.showMessageDialog(null,dubBut,
                            "Choose your route", 
                            JOptionPane.QUESTION_MESSAGE);

                        if(dubBut[0].isSelected())
                        {
                            checkHand(firstR);
                        }
                        else
                        {
                            checkHand(secondR);
                        }

                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,
                        "Invalid Route!");
                }
                first = second = null;
                cityPicked = false;
            }
        }
        
        else if(xVal >= 860 && xVal < 970 && 
        yVal < 490 && !cityPicked)
        {
            //If the user clicks on the top 5 cards, adds that 
            //card to the user's hand.
            int cardTaken = trainClicked(xVal,yVal);
            if(cardTaken >= 0 && cardTaken < 5)
            {
                TrainCard check = top5.get(cardTaken);
                if(check.getColor().equals("Locomotive") && drew)
                {
                    JOptionPane.showMessageDialog(null,
                        "Cannot draw a locomotive after drawing " +
                        "a non-locomotive.");
                }
                else
                {

                    if(cardTaken < top5.size())
                    {
                        TrainCard temp = top5.remove(cardTaken);
                        //if drew a loco, end.
                        //if not, check to see if 
                        //user drew before.
                        //if so, end,
                        //if not, allow another draw.
                        if(temp.getColor().equals("Locomotive"))
                        {
                            loco = true;
                        }
                        else if(drew)
                        {
                            drewTwo = true;
                        }
                        else
                        {
                            drew = true;
                        }

                        current.addCard(temp);
                        if(drawDeck.getSize() == 0 &&
                        discard.size() > 0)
                        {
                            discardToMain();
                            if(top5.size() < 5)
                            {
                                int topSize = top5.size();
                                for(int n = topSize; n < 5; n++)
                                {
                                    top5.add(drawDeck.draw());
                                }
                            }
                            else
                            {                
                                top5.add(cardTaken,drawDeck.draw());
                            }
                        }
                        else if(drawDeck.getSize() > 0)
                        {
                            top5.add(cardTaken,drawDeck.draw());
                        }

                        repaint();
                        //Does additional checking for
                        //if loco was added to top5 and 3 
                        //are there now.
                        while(checkTopFive())
                        {
                            JOptionPane.showMessageDialog(null,
                                "Replacing top 5!");
                            replaceTopFive();

                        }
                    }
                }   
            }
            else if(cardTaken == 5)
            {

                if(drawDeck.getSize() == 0 && 
                discard.size() > 0)
                {
                    discardToMain();
                    if(top5.size() < 5)
                    {
                        int topSize = top5.size();

                        for(int n = topSize; n < 5; n++)
                        {
                            if(drawDeck.getSize() > 0)
                            {
                                top5.add(drawDeck.draw());
                            }
                        }

                    }
                }      
                if(drawDeck.getSize() > 0)
                {
                    TrainCard offTop = drawDeck.draw();
                    current.addCard(offTop);
                    repaint();
                    JOptionPane.showMessageDialog(null,
                        offTop.getColor());
                    if(drew)
                    {
                        drewTwo = true;
                    }
                    else
                    {
                        drew = true;
                    }
                }   

            }
        }
        else if(xVal > 1000 && xVal <=1110 && yVal > 424 && 
        yVal <= 524 && !drew && !cityPicked && 
        destinations.size() > 0)
        {
            dealThreeDests();
            pickedDest = true;
        }
        else if(xVal > 860 && xVal <= 970 && yVal > 580 &&
        yVal <= 650)
        {
            ArrayList<DestinationCard> curDests = current.getDest();
            String[] playerDests = new String[curDests.size()];
            for(int i = 0; i < playerDests.length; i++)
            {
                playerDests[i] = curDests.get(i).getPath() + 
                " for " + curDests.get(i).getValue() + " points";
            }
            JOptionPane.showMessageDialog(null,
                playerDests, "Your destinations",
                JOptionPane.PLAIN_MESSAGE);

        }

        if(drewTwo || captured || loco || pickedDest
        || (drew && top5.size() == 0 && 
            drawDeck.getSize() == 0))
        {
            turn = true;
            repaint();
            if(current.getTrains() <= 2)
            {
                lastTurns = true;         
            }

            if(endTurnCount == players.length)
            {
                endGame();
            }
            else
            {
                nextTurn();
            }

        }
    }

    /**
     * Method that handles the end game factors
     */
    public void endGame()
    {
        checkDestinationCards();

        declareLongestRoute();

        declareWinner();
    }

    /**
     * Check longest routes for each player, if there is more 
     * than one player with the same length longest route
     * add 10 points to each player, or just add 10 points
     * to the single player with the longest route.
     */
    public void declareLongestRoute()
    {
        int maxRoute = 0;
        ArrayList<Player> longest = new 
            ArrayList<Player>();
        //loops through players array to check their longest route
        for(int i = 0;i<players.length;i++)
        {
            Player temp = players[i];
            int checkLong = temp.longestRoute();
            //if the checkLong is longer than current longest route
            //update to new longest route and add to longest ArrayList
            if(checkLong > maxRoute)
            {
                maxRoute = checkLong;
                longest.clear();
                longest.add(temp);
            }
            //if equal to longest route add to longest ArrayList
            else if(checkLong == maxRoute)
            {
                longest.add(temp);
            }
        }
        //if more than one player has the same longest route
        if(longest.size()>1)
        {
            //add 10 points to each players score for longest route
            for(int i = 0;i<longest.size();i++)
            {
                Player temp = longest.get(i);
                temp.updateScore(10);
                //display window with update to score
                JOptionPane.showMessageDialog(null,
                    temp.getName() +" has tied for the longest route, "
                    + temp.getName() + " will receive 10 points!");
                repaint();
            }
        }
        //only one player had longest route, add 10 points to their score
        else 
        {
            Player temp = longest.get(0);
            temp.updateScore(10);
            //display window with update to score
            JOptionPane.showMessageDialog(null,
                temp.getName() +" has longest route, "
                + temp.getName() + " will receive 10 points!");
            repaint();
        }

    }

    /**
     * Checks to see if the player has completed their
     * destination cards.
     */
    public void checkDestinationCards()
    {
        // for all of the players destination cards, go
        // through each value and if it's greater than 0 then
        // that player earns the points and loses the points 
        // otherwise
        for(int i = 0;i<players.length;i++)
        {
            Player temp = players[i];
            JOptionPane.showMessageDialog(null,
                "Checking " + temp.getName() +"'s " +
                "destination cards.");
            // player's destination cards
            ArrayList<DestinationCard> playersDest =
                temp.getDest();
            // the values of the destination cards
            ArrayList<Integer> values = 
                temp.checkDestinationCards();
            for(int j =0;j<playersDest.size();j++)
            {
                // if the value is greater than 0, success
                if(values.get(j) > 0)
                {
                    JOptionPane.showMessageDialog(null,
                        playersDest.get(j).getPath() +
                        " was completed! " + temp.getName() +
                        " earns " + values.get(j) + " points!");
                }
                else
                {
                    JOptionPane.showMessageDialog(null,
                        playersDest.get(j).getPath() +
                        " was not completed! " + temp.getName() +
                        " loses " + values.get(j)*-1 + " points!");
                }
                // update the player's score accordingly
                temp.updateScore(values.get(j));
                repaint();
            }

        }

    }

    /**
     * Compares players destinations, scores, and mountains
     * to determine a winner or declare a tie if there is no winner.
     */
    public void declareWinner()
    {
        int maxScore = Integer.MIN_VALUE;
        int maxDest = 0;
        int maxMtns = 0;

        //goes through all the players and finds a winner based 
        //on the scores
        for(Player p : players)
        {
            if(p.getScore() > maxScore)
            {
                maxScore = p.getScore();
                winner = p;
                tie = false;
            }
            else if(p.getScore() == maxScore)
            {
                tie = true;
                winner = null;
            }

        }

        //if there is no tie from the score game over
        if(!tie)
        {
            gameOver = true;
        }
        else //there was a tie so check completer destinations
        {
            tie = false;
            //goes through each player and updates player
            //with the max destinations
            for(Player p : players)
            {
                if(p.destsCompleted() > maxDest)
                {
                    maxDest = p.destsCompleted();
                    winner = p;
                    tie = false;
                }
                //if still a tie 
                else if(p.destsCompleted() == maxDest)
                {
                    tie = true;
                    winner = null;
                }

            }
        }

        //if there is no tie after destination check game over
        if(!tie)
        {
            gameOver = true;
        }
        else //there is still a tie so check mountains
        {
            tie = false;
            //goes through each player and updates player
            //with the max mountains
            for(Player p : players)
            {
                if(p.getMtns() > maxMtns)
                {
                    maxMtns = p.getMtns();
                    winner = p;
                    tie = false;
                }
                //if still a tie, then game tied
                else if(p.getMtns() == maxMtns)
                {
                    tie = true;
                    winner = null;
                }

            }
        }

        //game is over 
        gameOver = true;
    }

    /**
     * Paint method for applet.
     * 
     * @param  g   the Graphics object for this applet
     */
    public void paint(Graphics g)
    {
        // draws the background and the board
        g.drawImage(table,0,0,1250,900,this);
        g.drawImage(boardPic,0,0,850,550,this);

        // Draws the 5 draw cards
        int add = 20;
        for(int j = 0; j < top5.size(); j++)
        {
            g.drawImage(top5.get(j).getPic(), 860, 
                20 + (80*j), 110, 70, this);
        }
        // draws the players hand cards
        for(int i = 0; i < handCards.length; i++)
        {
            g.drawImage(handCards[i], i*80 + 60, 560, 70, 110,this);
        }
        // draws the background image where player
        // information is displayed
        g.drawImage(paper,982,20,250,380,this);
        Font myFont = new Font("Sans_Serif",Font.BOLD,17);
        g.setFont(myFont);
        // Player 1 display
        g.drawString("Score: " + players[0].getScore(), 1000, 100);
        g.drawString("Trains: " + players[0].getTrains(), 1000, 120);
        //Player 2 display
        g.drawString("Score: " + players[1].getScore(), 1000, 195);
        g.drawString("Trains: " + players[1].getTrains(), 1000, 215);

        // Player names
        g.drawString("~"+players[0].getName()+"~",990, 80);
        g.drawString("~"+players[1].getName()+"~",990, 175);
        // Player three display
        if(players.length == 3){
            g.drawString("~"+players[2].getName()+"~",990, 270);
            g.drawString("Score: " + players[2].getScore(), 1000, 290);
            g.drawString("Trains: " + players[2].getTrains(), 1000, 310);
        }

        //Draws the deck
        g.drawImage(drawDeck.getDeckPic(), 860, 420, 138, 98, this);
        g.drawImage(destDeck, 1000, 424, 138, 102, this);
        g.drawImage(yourDest, 860, 580, 110, 70, this);
        g.setColor(Color.BLACK);
        //format font using 2D graphics
        Graphics2D g2 = (Graphics2D)g;
        myFont = new Font("Serif",Font.BOLD,22);
        g2.setFont(myFont);
        g2.drawString("Your Destinations", 830, 670);

        //font for displaying card numbers        
        myFont = new Font("Serif",Font.BOLD,48);
        g2.setFont(myFont);

        //center the number of cards left in the deck
        if (drawDeck.getSize() > 9)
            g2.drawString(""+drawDeck.getSize(),895,475);
        else 
            g2.drawString(""+drawDeck.getSize(),905,475);

        //center and display cards in hands under card image
        if(!turn)
        {
            if (current.cardCount("Red") > 9)
                g.drawString(""+current.cardCount("Red"),72,710);
            else
                g.drawString(""+current.cardCount("Red"),85,710);
            if (current.cardCount("Blue") > 9)
                g.drawString(""+current.cardCount("Blue"),152,710);
            else
                g.drawString(""+current.cardCount("Blue"),165,710);
            if (current.cardCount("Yellow") > 9)
                g.drawString(""+current.cardCount("Yellow"),232,710);
            else
                g.drawString(""+current.cardCount("Yellow"),245,710);
            if (current.cardCount("Green") > 9) 
                g.drawString(""+current.cardCount("Green"),312,710);
            else
                g.drawString(""+current.cardCount("Green"),325,710);
            if (current.cardCount("Brown") > 9)
                g.drawString(""+current.cardCount("Brown"),392,710);
            else
                g.drawString(""+current.cardCount("Brown"),405,710);
            if (current.cardCount("White") > 9)
                g.drawString(""+current.cardCount("White"),472,710);
            else
                g.drawString(""+current.cardCount("White"),485,710);
            if (current.cardCount("Black") > 9)
                g.drawString(""+current.cardCount("Black"),552,710);
            else
                g.drawString(""+current.cardCount("Black"),565,710);
            if (current.cardCount("Purple") > 9)
                g.drawString(""+current.cardCount("Purple"),632,710);
            else
                g.drawString(""+current.cardCount("Purple"),645,710);
            if (current.cardCount("Locomotive") > 9)
                g.drawString(""+current.cardCount("Locomotive"),712,710);
            else
                g.drawString(""+current.cardCount("Locomotive"),725,710);
        }
        // Certain images for certain player names
        if(current.getName().equals("Fluttershy"))
        {
            g.drawImage(fs,990,510,236,236,this);
        }
        if(current.getName().equals("Applejack"))
        {
            g.drawImage(aj,990,510,236,236,this);
        }

        g.setColor(Color.black);
        //draw/redraw all lines on the board
        for(int i = 0; i <cityLines.size(); i++){
            //players color to draw the line
            g.setColor(cityLines.get(i).getColor());
            int firstX, secondX, firstY, secondY;
            //get X and Y coordinates of the 2 cities
            firstX = cityLines.get(i).getFirstX();
            secondX = cityLines.get(i).getSecondX();
            firstY = cityLines.get(i).getFirstY();
            secondY = cityLines.get(i).getSecondY();
            //new graphic2D object for thick line
            Graphics2D g3 = (Graphics2D)g;
            //set line to thickness of 8
            g3.setStroke(new BasicStroke(8));
            //draw line from center of first city to center of second
            g3.draw(new Line2D.Double(firstX+10, firstY+10, 
                        secondX+10, secondY+10));
        }

        // decides the winner and whether or not there is a tie
        if(gameOver && !tie) //game over and there is a winner
        {
            //cover applet screen with firework picture
            g.drawImage(firework,0,0,1250,900,this);
            //formatng for the game over screen
            g.setColor(Color.WHITE);
            g.fillRect(0,410,1250,80);
            myFont = new Font("Serif",Font.BOLD,70);
            g2.setFont(myFont);
            g2.setColor(winner.getColor());
            String winnerString = winner.getName() + " WINS!";
            FontMetrics fm = g.getFontMetrics();
            //center string in the middle of the screen
            int stringX = 625-(fm.stringWidth(winnerString)/2);
            g2.drawString(winnerString,stringX,472);

        }
        else if(gameOver && tie) //game over and players tied
        {
            //cover applet screen with firework picture
            g.drawImage(firework,0,0,1250,900,this);
            //formatting for came over screen
            g.setColor(Color.WHITE);
            g.fillRect(0,410,1250,80);
            myFont = new Font("Serif",Font.BOLD,70);
            g2.setFont(myFont);
            g2.setColor(Color.BLACK);
            //display tie string
            g2.drawString("NO ONE WINS!",395,475);
        }

    }

    /**
     * Called by the browser or applet viewer to inform this JApplet that it
     * is being reclaimed and that it should destroy any resources that it
     * has allocated. The stop method will always be called before destroy. 
     */
    public void destroy()
    {
        // provide code to be run when JApplet is about to be destroyed.
    }

    /**
     * Returns information about this applet. 
     * An applet should override this method to return a String containing 
     * information about the author, version, and copyright of the JApplet.
     *
     * @return a String representation of information about this JApplet
     */
    public String getAppletInfo()
    {
        // provide information about the applet
        return "Title:   \nAuthor:   \nA simple applet example description. ";
    }

    /**
     * Returns parameter information about this JApplet. 
     * Returns information about the parameters than are understood by this 
     * JApplet.
     * An applet should override this method to return an array of Strings 
     * describing these parameters. 
     * Each element of the array should be a set of three Strings containing 
     * the name, the type, and a description.
     *
     * @return a String[] representation of parameter information about this 
     * JApplet
     */
    public String[][] getParameterInfo()
    {
        // provide parameter information about the applet
        String paramInfo[][] = {
                {"firstParameter","1-10","description of first parameter"},
                {"status", "boolean", "description of second parameter"},
                {"images",  "url",  "description of third parameter"}
            };
        return paramInfo;
    }

    public void mouseEntered(MouseEvent e){}

    public void mouseExited(MouseEvent e){}

    public void mouseReleased(MouseEvent e){}

    public void mousePressed(MouseEvent e){}
}