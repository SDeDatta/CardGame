import javax.swing.*;
import java.awt.*;

public class Card {
    private String rank;
    private String suit;
    private int x;
    private int y = 100;
    private int value;
    private CGView game;
    private Image cardImage;
    // Initializes values for a card
    public Card(String rank, String suit, int value, CGView game)
    {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        this.game = game;
    }

    public void setX(int x)
    {
        this.x = x;
    }
    public void setCardImage(int num)
    {
        this.cardImage = new ImageIcon("Resources/" + Integer.toString(num) + ".png").getImage();
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
        g.drawImage(cardImage, x, y, cardImage.getWidth(game), cardImage.getHeight(game), game);
    }
}
