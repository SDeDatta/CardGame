import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;
    private int cardsLeft;

    public Deck(String[] ranks, String[] suits, int[] points)
    {
        deck = new ArrayList<>();
        // Loops through all possible suits and creates and adds a card with the rank in that iteration,
        // the suit, and the corresponding points
        for(String suit:suits)
        {
            for(int i = 0; i < ranks.length; i++)
            {
                deck.add(new Card(ranks[i], suit, points[i]));
            }
        }
        cardsLeft = deck.size();
        shuffle();
    }
    //Checks if the deck is empty
    public boolean isEmpty()
    {
        return cardsLeft == 0;
    }

    public int getCardsLeft()
    {
        return cardsLeft;
    }
    // Deals a card
    public Card deal()
    {
        if(isEmpty())
        {
            return null;
        }
        // Decreases the number of cards and returns the card at the top of the deck
        cardsLeft--;
        return deck.get(cardsLeft);
    }
    // Shuffles the deck
    public void shuffle()
    {
        // Goes through the cards in reverse order
        for(int i = cardsLeft - 1; i > 0; i--)
        {
            // Generates a random number between 0 and the number of cards in the iteration (current index)
            int r = (int)(Math.random() * (i+1));
            // Swaps the card at the current index and the random number
            Card swap = deck.get(i);
            deck.set(i, deck.get(r));
            deck.set(r, swap);
        }
    }
}
