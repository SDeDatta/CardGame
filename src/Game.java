import java.util.ArrayList;
import java.util.Scanner;

class Game
{
    private Player[] players;
    private ArrayList<Card> deck;

    public Game(int players2)
    {
        String[] ranks = {"A", "2", "3", "4", "5","6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
        int[] points = {1,2,3,4,5,6,7,8,9,10,11,12,13};
        new Deck(ranks, suits, points);
        for(int i = 0; i < players2; i++)
        {
            players[i] = new Player("Player: " + i);
        }
        for(int i = 0; i < players2; i++)
        {
            players[i].deal();
        }
    }
    public static void printInstructions()
    {
        System.out.println("");
    }

    public boolean gameOver()
    {
        for(int i = 0; i < players.length; i++)
        {
            if(players[i].handIsEmpty())
            {
                return true;
            }
        }
        return false;
    }

    public void playGame()
    {
        Scanner input = new Scanner(System.in);
        printInstructions();
        System.out.println("How many players would you like? ");
        int numPlayers = input.nextInt();
        new Game(numPlayers);

        while(gameOver() != true)
        {
            for(int i = 0; i < players.length; i++)
            {
                System.out.println(("Hello " + players[i] + ", what suit would you like to put down? "));
                String suitDown = input.nextLine();
                System.out.println("What rank? ");
                String rankDown = input.nextLine();
                System.out.println("How many? ");
                int numDown = input.nextInt();


            }

        }
    }



    public void main(String[] args)
    {
        printInstructions();
        playGame();
    }
}
