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
        this.hand = new ArrayList<>();
    }
    public Player(String name, ArrayList <Card> hand2)
    {
        this.name = name;
        this.hand = new ArrayList<>(hand2);
        this.points = 0;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }
    public void showHand()
    {
        for(Card card: hand)
        {
            System.out.print(card.getRank() + " of " + card.getSuit());
        }
    }

    public int getPoints() {
        return points;
    }
    public void addPoints(int points)
    {
        this.points += points;
    }
    public void addCard(Card card)
    {
        hand.add(card);
    }
    public boolean handIsEmpty()
    {
        if(this.hand.size() == 0)
        {
            return true;
        }
        return false;
    }
    public int countCheck(String rank)
    {
        int count = 0;
        for(int i = 0; i < hand.size(); i++)
        {
            if(this.hand.get(i).getRank() == rank)
            {
                count++;
            }
        }
        return count;
    }
    public Card removeCard(String rank, String suit)
    {
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
    public void addCards(ArrayList<Card> list)
    {
        hand.addAll(list);
    }

    public String toString()
    {
        return this.name + " has " + this.points + " points\n" + this.name + "'s cards: " + this.hand;
    }
}
