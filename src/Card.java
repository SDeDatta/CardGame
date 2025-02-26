import javax.swing.*;
import java.awt.*;

public class Card {
    private String rank;
    private String suit;
    private int value;
    private CGView game;
    private Image cardImage;
    private static final int CARD_WIDTH = 55;
    private static final int CARD_HEIGHT = 85;
    // Initializes values for a card
    public Card(String rank, String suit, int value, CGView game)
    {
        this.rank = rank;
        this.suit = suit;
        this.value = value;
        this.game = game;
    }
    public int getWIDTH()
    {
        return CARD_WIDTH;
    }
    public int getHEIGHT()
    {
        return CARD_HEIGHT;
    }

    public void setCardImage(int num)
    {
        this.cardImage = new ImageIcon("Resources/" + num + ".png").getImage();
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
    // Card draws itself
    public void draw(Graphics g, int x, int y)
    {

        g.drawImage(cardImage, x, y, CARD_WIDTH, CARD_HEIGHT, game);
    }
}
