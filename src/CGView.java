import javax.swing.*;
import java.awt.*;

// This class handles painting the game to the window
public class CGView extends JFrame
{
    private Game game;
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT = 1000;
    // Background images for different game states
    private Image firstBgImage;
    private Image gameBgImage;
    private Image endGameImage;

    public CGView(Game game)
    {
        this.game = game;
        // Loads background images
        firstBgImage = new ImageIcon("Resources/FirstBg.png").getImage();
        gameBgImage = new ImageIcon("Resources/gameBg.jpg").getImage();
        endGameImage = new ImageIcon("Resources/gameOverBg.png").getImage();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Bluff");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }
    // Determines which screen to display based on the game state
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
        else if(game.getState().equals("gameOver"))
        {
            paintEndScreen(g);
        }

    }

    public void paintGame(Graphics g)
    {
        if(game.getPlayers() != null && game.getCurrentPlayer() != null) {
            Player currentPlayer = game.getCurrentPlayer();
            g.drawImage(gameBgImage, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
            // Display the next player's name if there are multiple players
            if(game.getPlayers().length > 1) {
                Player nextPlayer = game.getPlayers()[(game.getCurrentPlayerIndex() + 1) % game.getPlayers().length];
                g.setColor(Color.BLACK);
                g.setFont(new Font("SansSerif", Font.ITALIC + Font.BOLD, 25));
                g.drawString("Next up: " + nextPlayer.getName() + " 's turn", 50, 50);
            }
            g.setColor(Color.LIGHT_GRAY);
            g.setFont(new Font("SansSerif", Font.ITALIC + Font.BOLD, 50));
            g.drawString(currentPlayer.getName() + " 's turn", 50, 100);
            // Calculate the starting X position to center the player's hand
            int spacing = 5;
            int cardSize = 50;
            int hand_size = currentPlayer.getHand().size();
            int hand_width = hand_size * (cardSize + spacing) - spacing;
            int startX = (WINDOW_WIDTH - hand_width) / 2;
            // Draw each card in the player's hand
            for (int i = 0; i < currentPlayer.getHand().size(); i++) {
                Card card = currentPlayer.getHand().get(i);
                int x = startX + i * (cardSize + spacing);
                card.draw(g,x, 460);
            }
        }
    }
    // Paints the instruction screen with game rules.
    public void paintFirstScreen(Graphics g)
    {
        g.setFont(new Font("SansSerif", Font.BOLD, 15));
        g.setColor(Color.LIGHT_GRAY);
        g.drawImage(firstBgImage, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        g.drawString("Welcome to Bluff! Each player will be given a hand and the game goes", 200, 350);
        g.drawString("in increasing order such that the player going first plays aces,", 250, 380);
        g.drawString("next one plays 1s, and so on (loops around to ace after king).", 250, 410);
        g.drawString("Each turn you can bluff by putting down a different card than", 250, 440);
        g.drawString("what you are assigned. You can put up to 4 cards down per turn.", 250, 470);
        g.drawString("After each turn, all other players can call a bluff. If someone", 250, 500);
        g.drawString("calls bluff incorrectly, they take the pile. Win when your hand is empty.", 220, 530);
        g.drawString("4+ players recommended. Enjoy!", 350, 560);

    }

    public void paintEndScreen(Graphics g)
    {
        g.drawImage(endGameImage, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, this);
        // Display the winner's name
        g.setColor(Color.GREEN);
        g.setFont(new Font("SansSerif", Font.BOLD,30));
        String winner = game.gameOver().getName();
        g.drawString(winner + " Wins!", 50, 100);
    }
}
