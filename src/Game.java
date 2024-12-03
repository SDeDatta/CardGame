import java.util.ArrayList;
import java.util.Scanner;

class Game
{
    private Player[] players;
    private Deck deck;
    ArrayList<Card> pile;
    private String[] ranks = {"A", "2", "3", "4", "5","6", "7", "8", "9", "10", "J", "Q", "K"};
    private int currentRank;

    public Game(int players2)
    {
        String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
        int[] points = {1,2,3,4,5,6,7,8,9,10,11,12,13};
        deck = new Deck(ranks, suits, points);
        players = new Player[players2];
        pile = new ArrayList<>();
        currentRank = 0;
        for(int i = 0; i < players2; i++)
        {
            players[i] = new Player("Player: " + (i+1));
        }
        deck.shuffle();
        for(int i = 0; i < 52/players2; i++)
        {
            for(Player player: players)
            {
                player.addCard(deck.deal());
            }
        }
    }
    public static void printInstructions()
    {
        System.out.println("Welcome to Bluff! Each player will be given a hand and the game goes in increasing order" +
                " such that the player going first plays aces, next one plays 1s, and so on (loops around to ace " +
                "after king). Each turn you can bluff by putting down a different card than what you are assigned. " +
                "You can put up to 4 cards down per turn. After each turn, all other players in the game are asked " +
                "whether they think the player who just put down cards bluffed or not. If someone calls bluff when " +
                "there isn't one, they have to take the whole pile. Same for the person who bluffed if they get " +
                "caught. Enjoy! ");
    }
    // Checks if the game is over and returns who won
    public Player gameOver()
    {
        for(int i = 0; i < players.length; i++)
        {
            if(players[i].handIsEmpty())
            {
                return players[i];
            }
        }
        return null;
    }
    public void addToPile(Card card)
    {
        pile.add(card);
    }

    public void playGame()
    {
        Scanner input = new Scanner(System.in);
        while(gameOver() == null)
        {
            for(int i = 0; i < players.length; i++)
            {
                System.out.println(players[i].getName() + " 's turn");
                System.out.println("Your hand: ");
                players[i].showHand();
                System.out.println("Rank to play: " + ranks[currentRank]);
                System.out.println("How many cards would you like to put down (1-4)? ");
                int numDown = input.nextInt();
                input.nextLine();
                ArrayList<Card> declaredCards = new ArrayList<Card>();
                boolean truth = true;
                String suitDown = "";
                String rankDown = "";
                for(int k  = 0; k < numDown; k++)
                {
                    System.out.println("What suit would you like to put down for card " + (k+1) + "? ");
                    suitDown = input.nextLine();
                    System.out.println("What rank would you like to put down for card " + (k+1) + "? ");
                    rankDown = input.nextLine();
                    Card newCard = players[i].removeCard(rankDown, suitDown);
                    if(newCard == null)
                    {
                        truth = false;
                    }
                    else {
                        addToPile(newCard);
                        declaredCards.add(newCard);
                    }
                }
                System.out.println(players[i].getName() + " supposedly put down " + numDown + " " + ranks[currentRank] + " of " + suitDown);
                for(int j = 0; j <  players.length; j++)
                {
                    if(j != i) {
                        System.out.println(players[j].getName() + ", do you think " + players[i].getName() + " lied (t or l)? ");
                        String guess = input.nextLine();
                        if (guess.equals("l")) {
                            if(truth)
                            {
                                System.out.println(players[i].getName() + " was truthful!");
                                players[j].addCards(pile);
                            }
                            else
                            {
                                System.out.println(players[j].getName() + " was right!");
                                players[i].addCards(pile);
                            }
                            pile.clear();
                            break;
                        }
                    }
                }
                currentRank = (currentRank + 1) % ranks.length;
                if(gameOver() != null)
                {
                    Player winner = gameOver();
                    System.out.println("Congrats " + winner.getName());
                }
            }

        }
    }

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        printInstructions();
        System.out.println("How many players would you like? ");
        int competitors = input.nextInt();
        Game game = new Game(competitors);
        game.playGame();
    }
}
