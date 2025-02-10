import java.util.ArrayList;

public class Player
{
    private String name;
    private ArrayList<Card> hand;
    private int points;

    public Player(String name)
    {
        this.name = name;
        this.points = 0;
        // Initializes an arraylist of cards to create a hand
        this.hand = new ArrayList<>();
    }
    public Player(String name, ArrayList <Card> hand2)
    {
        this.name = name;
        this.hand = hand2;
        this.points = 0;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
    // Prints each card in a player's hand one by one
    public void showHand()
    {
        for(Card card: hand)
        {
            System.out.print(card.getRank() + " of " + card.getSuit() + ", ");
        }
        System.out.println();
    }

    public int getPoints() {
        return points;
    }
    public void addPoints(int points)
    {
        this.points += points;
    }
    // Adds a card to a player's hand
    public void addCard(Card card)
    {
        hand.add(card);
    }
    // Checks if a player has no cards left (their hand is empty)
    public boolean handIsEmpty()
    {
        return this.hand.isEmpty();
    }
    public Card removeCard(String rank, String suit)
    {
        // Goes card by card in a hand to find one to remove based on a given rank and suit
        for(Card card: hand)
        {
            if(card.getRank().equals(rank) && card.getSuit().equals(suit))
            {
                hand.remove(card);
                return card;
            }
        }
        return null;
    }
    // Adds an arraylist of cards (a pile) to a player's hand
    public void addCards(ArrayList<Card> list)
    {
        hand.addAll(list);
    }

    public String toString()
    {
        return this.name + " has " + this.points + " points\n" + this.name + "'s cards: " + this.hand;
    }
}
