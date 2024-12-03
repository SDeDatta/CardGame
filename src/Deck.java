import java.util.ArrayList;

public class Deck {
    private ArrayList<Card> deck;
    private int cardsLeft;

    public Deck(String[] ranks, String[] suits, int[] points)
    {
        deck = new ArrayList<>();
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

    public boolean isEmpty()
    {
        if(cardsLeft == 0)
        {
            return true;
        }
        return false;
    }

    public int getCardsLeft() {
        return cardsLeft;
    }
    public Card deal()
    {
        if(isEmpty())
        {
            return null;
        }
        cardsLeft--;
        return deck.get(cardsLeft);
    }

    public void shuffle()
    {
        for(int i = cardsLeft - 1; i > 0; i--)
        {
            int r = (int)(Math.random() * (i+1));
            Card swap = deck.get(i);
            deck.set(i, deck.get(r));
            deck.set(r, swap);
        }
    }
}
