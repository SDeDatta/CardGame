import javax.swing.*;
import java.awt.*;

public class CGView extends JFrame
{
    private Game game;
    int WINDOW_WIDTH = 1000;
    int WINDOW_HEIGHT = 1000;
    private Image firstBgImage;

    public CGView(Game game)
    {
        this.game = game;
        firstBgImage = new ImageIcon("Resources/FirstBg.png").getImage();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Tic Tac Toe");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }
    public void paint(Graphics g)
    {
        paintFirstScreen(g);

    }

    public void paintFirstScreen(Graphics g)
    {
        g.setColor(Color.LIGHT_GRAY);
        g.drawImage(firstBgImage, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.drawString("Welcome to Bluff! Each player will be given a hand and the game goes in increasing order" +
                " such that the player going first plays aces, next one plays 1s, and so on (loops around to ace " +
                "after king). Each turn you can bluff by putting down a different card than what you are assigned. " +
                "You can put up to 4 cards down per turn. After each turn, all other players in the game are asked " +
                "whether they think the player who just put down cards bluffed or not. If someone calls bluff when " +
                "there isn't one, they have to take the whole pile. Same for the person who bluffed if they get " +
                "caught. Make sure to type each suit and rank exactly as written in your hand. Enjoy! ", 300, 300);
    }
}
