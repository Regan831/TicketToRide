import java.util.*;
import java.awt.*;
/**
 * This class creates a new player with the 
 * proper attributes.
 * 
 * Anna, Mary, Ryan, Jordan and Paul
 * Ticket to Ride: Legendary Asia
 */
public class Player
{
    // int values needed by the player
    public int score, trainsRem, mtns;
    // player's name
    public String name;
    // player's hand
    public ArrayList<TrainCard> hand;
    // player's destination cards
    public ArrayList<DestinationCard> destinations;
    // Also need to make a graph for the 
    //  current routes that they control
    public CityNode[] captured = new CityNode[39];

    //For use with DFS when finding largest component.
    public boolean[] visited = new boolean[39];

    public boolean turn = false;

    public Color col;
    /**
     * Constructor for a player. It includes a string
     * for the player's name and a color to tell apart the 
     * player's captured routes
     * 
     * @param n - the String for the player's name
     * @param c - the color of the player's captured routes
     * 
     */
    public Player(String n, Color c)
    {
        score = 0;
        trainsRem = 45; 
        name = n;
        mtns = 0;
        destinations = new ArrayList<DestinationCard>();
        hand = new ArrayList<TrainCard>();
        col = c;
        // will also need to initialize hand and destinations.
    }

    /**
     * Retrieves the player's color
     * 
     * @return the player's color
     */
    public Color getColor()
    {
        return col;
    }

    /**
     * Retrieves the player's name
     * 
     * @return the player's name
     */
    public String getName()
    {
        return name;
    }

    /**
     * This method updates the player's score depending on 
     * the input
     * 
     * @param i - the value to be added to the score
     */
    public void updateScore(int i){
        score = score +i;
    }

    /**
     * Retrieves the player's score
     * 
     * @return the player's score
     */
    public int getScore(){
        return score;
    }

    /**
     * This method updates the player's trains depending on 
     * the input
     * 
     * @param i - the value to be subtracted from
     *  the player's train count
     */
    public void updateTrains(int i){
        trainsRem = trainsRem - i;
    }

    /**
     * Retrieves the player's trains
     * 
     * @return the player's trains
     */
    public int getTrains()
    {
        return trainsRem;
    }

    /**
     * Retrieves the player's mountains
     * 
     * @return the player's mountains
     */
    public int getMtns()
    {
        return mtns;
    }

    /**
     * This method updates the player's mountains 
     * depending on ]the input
     * 
     * @param i - the value to be subtracted from
     *  the player's mountains
     */
    public void updateMtns(int i)
    {
        mtns+=i;
    }

    /**
     * This method changes the state of the 
     * player's turn.
     */
    public void changeTurn()
    {
        turn = !turn;
    }

    /**
     * This checks to see if its a player's turn or not
     * 
     * @return - true if its the current player's turn and\
     * false otherwise
     */
    public boolean isTurn()
    {
        return turn;
    }

    /**
     * This method retrieves the number of cards in a player's
     * hand deending on what the input given is
     * 
     * @param color - the specfic color of the card to be 
     *      searched for
     *  
     *  @return - the number of cards in the hand that match 
     *      the given input
     */
    public int cardCount(String color)
    {
        int count = 0;
        for(int i = 0; i<hand.size(); i++)
        {
            TrainCard temp = hand.get(i);
            String colorString = temp.getColor();
            if(color.equals(colorString))
            {
                count++;
            }
        }

        return count;
    }

    /** 
     * 
     */
    public ArrayList<TrainCard> capture
    (ArrayList<TrainCard> played)
    {
        ArrayList<TrainCard> discard = new 
            ArrayList<TrainCard>();

        for(int i = 0; i < played.size(); i++)
        {
            TrainCard temp = played.get(i);
            discard.add(temp);
            hand.remove(temp);
        }
        return discard;
    }

    /**
     * Adds a train card to the player's hand.
     * 
     * @param t The TrainCard to add.
     */
    public void addCard(TrainCard t)
    {
        hand.add(t);
    }

    /**
     * Adds a destination card to the player's deck
     * 
     * @param d - the card to be added
     */
    public void addDest(DestinationCard d)
    {
        destinations.add(d);
    }

    /**
     * Retrieves the destination cards for a specific player
     * 
     * @return the player's destination cards
     */
    public ArrayList<DestinationCard> getDest()
    {
        return destinations;
    }

    /**
     * Adds a directed edge between two cities
     * 
     * @param s - the location of the start city
     * in the captured array
     * 
     * @param d - the location of the second city 
     * in the captured array
     */
    private void addDirectedEdge( int s, int d ) 
    {
        // insert a new ENode at the beginning of the list
        if(captured[s] == null){
            captured[s] = new CityNode(d);
        }
        else{
            CityNode next = captured[s];
            captured[s] = new CityNode(d,next);
        }
    }

    /**
     *  Adds an undirected edge between vertex v1 and v2
     *  @param v1 one vertex of the edge
     *  @param v2 the other vertex of the edge
     */
    public void addEdge( int v1, int v2 ) {
        // if parameters are invalid, just return
        if ( (v1 < 0) || (v2 < 0) || (v1 >= 39) || (v2 >= 39)) {
            return;
        }
        addDirectedEdge( v1, v2 );
        addDirectedEdge( v2, v1 );
    }

    /**
     * Goes through a player's captured routes and 
     * returns the value of the longest connected route.
     * 
     * @return the longest connected route Integer value
     */
    public int longestRoute(){
        int max = 0;
        for(int i = 0;i<39;i++){
            dfs(i);
            int count = 0;
            for(int k=0;k<39;k++){
                if(visited[k]==true){
                    count++;
                }
            }
            if(count > max){
                max = count;
            }
            for(int j=0;j<39;j++){
                visited[j] = false;
            }
        }
        return max;
    }

    /**
     * This method recursively searches a graph and 
     * checks to see if all nodes (cities) are 
     * connected.
     * 
     * @param v - the integer value of the city
     */
    public void dfs(int v)
    {
        visited[v] = true;
        CityNode cur = captured[v];
        while(cur != null)
        {
            if(!visited[cur.getDest()])
            {
                dfs(cur.getDest());
            }
            else
            {
                cur = cur.getNext();
            }

        }
    }

    /**
     * Checks to see if the player's destination cards have been completed
     * 
     * @return the point values of each destination card completed
     */
    public ArrayList<Integer> checkDestinationCards(){
        ArrayList<Integer> pointValues = new 
            ArrayList<Integer>();
        for(int i=0;i<destinations.size();i++)
        {
            int firstCity = destinations.get(i).
                getFirstCity().getCityVal();
            int secondCity = destinations.get(i).
                getSecondCity().getCityVal();

            dfs(firstCity);

            int v = destinations.get(i).getValue();
            if(visited[secondCity]==true){

                pointValues.add(v);
            }
            else{
                v = (-1)*v;
                pointValues.add(v);
            }
            for(int j=0;j<39;j++){
                visited[j] = false;
            }

        }
        return pointValues;
    }

    /**
     * Returns the count of the number of destination
     * cards completed by a player
     * 
     * @return the number of destination cards
     * completed
     */
    public int destsCompleted()
    {
        int count = 0;
        ArrayList<Integer> completed = 
            checkDestinationCards();
        for(int i = 0; i < completed.size(); i++)
        {
            if(completed.get(i) > 0)
            {
                count++;
            }             
        }
        return count;
    }

}