import javax.swing.*;
import java.awt.*;

public class CGView extends JFrame
{
    private Game game;
    int WINDOW_WIDTH = 1000;
    int WINDOW_HEIGHT = 1000;
    private Image firstBgImage;
    private Image gameBgImage;
    private Image endGameImage;
    private Player currentPlayer;

    public CGView(Game game)
    {
        this.game = game;
        firstBgImage = new ImageIcon("Resources/FirstBg.png").getImage();
        gameBgImage = new ImageIcon("Resources/gameBg.jpg").getImage();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Bluff");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }
    public void paint(Graphics g)
    {
        if(game.getState().equals("instructions"))
        {
            paintFirstScreen(g);
        }
        else if(game.getState().equals("game"))
        {
            paintGame(g);
        }

    }

    public void setCurrentPlayer(Player player)
    {
        this.currentPlayer = player;
    }

    public void paintGame(Graphics g)
    {
        g.drawImage(gameBgImage, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.drawString(currentPlayer.getName() + " 's turn", 100, 200);
        for(Card c : currentPlayer.getHand())
        {
            c.draw(g);
        }

    }
    public void paintFirstScreen(Graphics g)
    {
        g.setColor(Color.LIGHT_GRAY);
        g.drawImage(firstBgImage, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.drawString("Welcome to Bluff! Each player will be given a hand and the game goes", 300, 300);
        g.drawString("in increasing order such that the player going first plays aces,", 300, 320);
        g.drawString("next one plays 1s, and so on (loops around to ace after king).", 300, 340);
        g.drawString("Each turn you can bluff by putting down a different card than", 300, 360);
        g.drawString("what you are assigned. You can put up to 4 cards down per turn.", 300, 380);
        g.drawString("After each turn, all other players can call a bluff. If someone", 300, 400);
        g.drawString("calls bluff incorrectly, they take the pile. Win when your hand is empty.", 300, 420);
        g.drawString("Enjoy!", 300, 440);

    }
}
