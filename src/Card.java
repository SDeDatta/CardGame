import java.awt.*;

public class Card {
    private String rank;
    private String suit;
    private int value;
    private CGView game;
    // Initializes values for a card
    public Card(String rank, String suit, int value, CGView game)
    {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        this.game = game;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }
    // Prints the card in a formal _ of _ format
    public String toString() {
        return rank + " of " + suit;
    }

    public void draw(Graphics g)
    {
        //if(dx >= 0)
        {
            //g.drawImage(rightImage, x, y, rightImage.getWidth(tank), rightImage.getHeight(tank), tank);
        }
        //else
        {
            //g.drawImage(leftImage, x, y, leftImage.getWidth(tank), leftImage.getHeight(tank), tank);
        }
    }
}
