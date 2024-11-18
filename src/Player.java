import java.util.ArrayList;

public class Player
{
    private String name;
    private ArrayList<Card> hand = new ArrayList<>();
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
        this.hand = new ArrayList<>(hand);
        this.points = 0;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public int getPoints() {
        return points;
    }
    public void addPoints(Card card)
    {
        hand.add(card);
    }
    public String toString()
    {
        return this.name + " has " + this.points + " points.";
    }
}
