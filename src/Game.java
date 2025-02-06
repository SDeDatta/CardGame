// Bluff by Surya De Datta
import java.util.ArrayList;
import java.util.Scanner;

class Game
{
    private Player[] players;
    private Deck deck;
    ArrayList<Card> pile;
    private String[] ranks = {"A", "2", "3", "4", "5","6", "7", "8", "9", "10", "J", "Q", "K"};
    // Used to determine what rank should be played next
    private int currentRank;
    private CGView window;

    public Game(int players2)
    {
        // Sets the values of the arrays and arraylists
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        int[] points = {1,2,3,4,5,6,7,8,9,10,11,12,13};
        // Initializes a new deck, a list of players, and a pile of cards put down
        deck = new Deck(ranks, suits, points, window);
        players = new Player[players2];
        pile = new ArrayList<>();
        currentRank = 0;
        window = new CGView(this);
        // Adds players to the empty players list
        for(int i = 0; i < players2; i++)
        {
            players[i] = new Player("Player " + (i+1));
        }
        deck.shuffle();
        // Deals cards to each player
        for(int i = 0; i < 52/players2; i++)
        {
            for(Player player: players)
            {
                player.addCard(deck.deal());
            }
        }
    }
    // Prints the instructions of the game
    public static void printInstructions()
    {
        System.out.println("Welcome to Bluff! Each player will be given a hand and the game goes in increasing order" +
                " such that the player going first plays aces, next one plays 1s, and so on (loops around to ace " +
                "after king). Each turn you can bluff by putting down a different card than what you are assigned. " +
                "You can put up to 4 cards down per turn. After each turn, all other players in the game are asked " +
                "whether they think the player who just put down cards bluffed or not. If someone calls bluff when " +
                "there isn't one, they have to take the whole pile. Same for the person who bluffed if they get " +
                "caught. Make sure to type each suit and rank exactly as written in your hand. Enjoy! ");
    }
    // Checks if the game is over and returns who won
    public Player gameOver()
    {
        for (Player player : players) {
            if (player.handIsEmpty()) {
                return player;
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
        // Makes sure the game is not over before continuing the game
        while(gameOver() == null)
        {
            // Goes through a round (each player plays once)
            for(int i = 0; i < players.length; i++)
            {
                System.out.println(players[i].getName() + " 's turn");
                System.out.println("Your hand: ");
                players[i].showHand();
                // Shows the rank the player is supposed to play
                System.out.println("Rank to play: " + ranks[currentRank]);
                System.out.println("How many cards would you like to put down (1-4)? ");
                int numDown = input.nextInt();
                input.nextLine();
                boolean truth = true;
                String suitDown = "";
                String rankDown;
                // Goes through one turn for a single player
                for(int k  = 0; k < numDown; k++)
                {
                    // Goes card by card asking which specific card to play
                    System.out.println("What suit would you like to put down for card " + (k+1) + " (write it exactly" +
                            " as written in your hand)? ");
                    suitDown = input.nextLine();
                    System.out.println("What rank would you like to put down for card " + (k+1) + " (write it exactly" +
                            " as written in your hand)? ");
                    rankDown = input.nextLine();
                    // Removes the card played from the player's hand
                    Card newCard = players[i].removeCard(rankDown, suitDown);
                    // Checks if the card exists in the player's hand and matches the expected rank
                    // Ignores whether the user typed in upper or lower case
                    if(newCard == null || !rankDown.equalsIgnoreCase(ranks[currentRank]))
                    {
                        truth = false;
                    }
                    //Adds the card to the pile
                    addToPile(newCard);

                }
                System.out.println(players[i].getName() + " supposedly put down " + numDown + " " + ranks[currentRank] +
                        " of " + suitDown);
                // Goes through each player to ask whether they think the player who most recently played lied or not
                for(int j = 0; j <  players.length; j++)
                {
                    // Avoids the player who is under question
                    if(j != i) {
                        System.out.println(players[j].getName() + ", do you think " + players[i].getName() + " lied " +
                                "(t or l)? ");
                        String guess = input.nextLine();
                        if (guess.equals("l")) {
                            if(truth)
                            {
                                System.out.println(players[i].getName() + " was truthful!");
                                // Adds the cards to the person who thought the most recent player bluffed but was wrong
                                players[j].addCards(pile);
                            }
                            else
                            {
                                System.out.println(players[j].getName() + " was right!");
                                // Adds the cards to the person who bluffed
                                players[i].addCards(pile);
                            }
                            // Empties the pile after it is added to someone's hand
                            pile.clear();
                            break;
                        }
                    }
                }
                // Augments the current rank and ensure it will loop around to Ace after King
                currentRank = (currentRank + 1) % ranks.length;
                if(gameOver() != null)
                {
                    // Prints the winner if there is one
                    Player winner = gameOver();
                    System.out.println("Congrats " + winner.getName() + ", you win!");
                    System. exit(0);
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
        // Creates a game based on the number of players the user(s) would like
        Game game = new Game(competitors);
        game.playGame();
    }
}
