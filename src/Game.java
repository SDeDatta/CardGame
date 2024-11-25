import java.util.ArrayList;
import java.util.Scanner;

class Game
{
    private Player[] players;
    private Deck deck;
    ArrayList<Card> pile;

    public Game(int players2)
    {
        String[] ranks = {"A", "2", "3", "4", "5","6", "7", "8", "9", "10", "J", "Q", "K"};
        String[] suits = {"Hearts", "Clubs", "Spades", "Diamonds"};
        int[] points = {1,2,3,4,5,6,7,8,9,10,11,12,13};
        deck = new Deck(ranks, suits, points);
        players = new Player[players2];
        pile = new ArrayList<>();
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
        System.out.println("");
    }

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
        printInstructions();
        System.out.println("How many players would you like? ");
        int numPlayers = input.nextInt();
        new Game(numPlayers);

        while(gameOver() == null)
        {
            for(int i = 0; i < players.length; i++)
            {
                System.out.println(players[i] + " 's turn");
                System.out.println("Your hand: ");
                players[i].showHand();
                System.out.println(("Hello " + players[i] + ", " + "how many cards would you like to put down (1-4)? ");
                int numDown = input.nextInt();
                System.out.println();
                ArrayList<Card> declaredCards = new ArrayList();
                System.out.println("Declare the rank you're putting down: ");
                String declaredRank = input.nextLine();
                String suitDown = "";
                for(int k  = 0; k < numDown; k++)
                {
                    System.out.println("What suit would you like to put down for card " + (k+1) + "? ");
                    suitDown = input.nextLine();
                    Card newCard = players[i].removeCard(declaredRank, suitDown);
                    addToPile(newCard);
                    declaredCards.add(newCard);
                }
                System.out.println(players[i] + " put down " + numDown + " " + declaredRank + " of " + suitDown);
                boolean bluffCaught = false;
                for(int j = 0; j <  players.length; j++)
                {
                    if(j != i) {
                        System.out.println(players[j] + ", do you think " + players[i] + " lied (t or l)? ");
                        String guess = input.nextLine();
                        if (guess.equals("l")) {
                            bluffCaught = true;
                            boolean truth = players[i].countCheck(declaredRank) >= numDown;
                            if(truth)
                            {
                                System.out.println(players[i] + " was truthful!");
                                players[j].addCards(pile);
                            }
                            else
                            {
                                System.out.println(players[j] + " was right!");
                                players[i].addCards(pile);
                            }
                            pile.clear();
                            break;
                        }
                    }
                }


            }

        }
        Player winner = gameOver();
        System.out.println("Congrats " + winner.getName());
    }

    public static void main(String[] args)
    {
        printInstructions();
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many players do you want in this game? ");
        int people = scanner.nextInt();
        Game game = new Game(people);
        game.playGame();
    }
}
