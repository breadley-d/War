import java.awt.Font;
import java.awt.Toolkit;

public class Deck {

    private Card[] deck;
    private int size;

    public Deck(Card[] deck) {
        this.deck = deck;
        size = deck.length;
    }


    public Deck(int len) {
        this.deck = new Card[len];
        size = deck.length;
    }

    public Deck(Deck deck) {
        this.deck = deck.getDeck();
        size = this.deck.length;
    }


    //shuffles deck
    public void Shuffle() {
        for(int i = 0; i < this.size; i++) { //how do I get the length of the deck
            //System.out.println("i " + i + " this.size " + this.size); //testing
            int r = i + (int) (Math.random() * (this.size - i));
            Card temp = this.deck[i];
            this.deck[i] = this.deck[r];
            this.deck[r] = temp;

        }
    }

    public void changeCard(int index, Card card) { //change a card in a deck
        this.deck[index] = card;
    }



    public Deck[] Deal() {
        Deck returnDeck = new Deck(new Card[26]);
        for(int i = 0; i < 26 ; i++) {
            returnDeck.changeCard(i,this.deck[i]); //error
        }
        return(this.Deal2(returnDeck));
        //return returnDeck;

    }

    private Deck[] Deal2(Deck otherDeck) {
        Deck returnDeck = new Deck(new Card[26]);
        for(int i = 26; i < 52; i++) {
            returnDeck.getDeck()[i-26] = this.deck[i]; //error
        }
        Deck[] returns = new Deck[2];
        returns[0] = otherDeck;
        returns[1] = returnDeck;
        return returns;
    }



    public static Deck[] hand(Deck player1, Deck player2) {
        Card player1Card = player1.getDeck()[0];
        Card player2Card = player2.getDeck()[0];

        //player 1 wins the hand
        if(player1Card.getValue() > player2Card.getValue()) {
            Deck player1NewDeck = new Deck(player1.getSize() + 1);
            Deck player2NewDeck = new Deck(player2.getSize() - 1);

            //creating a new deck for player 1 with the won cards
            for(int i = 1; i < player1.getSize(); i++) { //somewhere I need to account for the fact that the "played" card needs to go to the back of the deck.
                //error in here with numbers somehwere i think
               player1NewDeck.changeCard(i-1, player1.getDeck()[i]); //sends cards from old deck into new deck
            }
            player1NewDeck.changeCard(player1.getSize() - 1, player1Card); //assigns "played" card to the end of the deck
            player1NewDeck.changeCard(player1.getSize(), player2Card); // assigns "played" card to the end of the deck

            //creating a new deck for player 2 without the lost card
            for(int i = 1; i < player2.getSize(); i++) {
                player2NewDeck.changeCard(i-1, player2.getDeck()[i]); //should ignore the lost card at player2.getDeck()[0]
            }

            Deck[] returnDecks =  new Deck[2];
            returnDecks[0] = player1NewDeck;
            returnDecks[1] = player2NewDeck;

            return returnDecks; 

        }

        else if (player1Card.getValue() < player2Card.getValue()) {
            Deck player1NewDeck = new Deck(player1.getSize() - 1);
            Deck player2NewDeck = new Deck(player2.getSize() + 1);

            // creating a new deck for player 1 with the won cards
            for (int i = 1; i < player2.getSize(); i++) { // somewhere I need to account for the fact that the "played"
                                                          // card needs to go to the back of the deck.
                // error in here with numbers somehwere i think
                player2NewDeck.changeCard(i - 1, player2.getDeck()[i]); // sends cards from old deck into new deck
            }
            player2NewDeck.changeCard(player2.getSize() - 1, player2Card); // assigns "played" card to the end of the
                                                                           // deck
            player2NewDeck.changeCard(player2.getSize(), player1Card); // assigns "played" card to the end of the deck

            // creating a new deck for player 2 without the lost card
            for (int i = 1; i < player1.getSize(); i++) {
                player1NewDeck.changeCard(i - 1, player1.getDeck()[i]); // should ignore the lost card at
                                                                        // player2.getDeck()[0]
            }

            Deck[] returnDecks = new Deck[2];
            returnDecks[0] = player1NewDeck;
            returnDecks[1] = player2NewDeck;

            return returnDecks;

        }

        // //tie, WAR
        else {
            return war(player1, player2);
        }
   }


  public static Deck[] war(Deck player1, Deck player2) {
    //System.out.println("WAR");  test
    Deck[] returnDecks = new Deck[2];
    // need to figure out / deal with issue of one of the arrays not having enough
    // cards in it (array index out of bounds)
    if (player1.getSize() < 3) {
          // player 2 wins
          System.out.println("Won via war");
          playerTwoWins();
          //System.exit(0);
    }
    else if (player2.getSize() < 3) {
        // player 1 wins
        System.out.println("Won via war");
        playerOneWins();
        //System.exit(0);
    }

    Card player1PlayedCard = player1.getDeck()[0];
    Card player2PlayedCard = player2.getDeck()[0];

    Card player1FaceDownCard = player1.getDeck()[1]; //second card in the array (first card was the card that tied to start war)
    Card player2FaceDownCard = player2.getDeck()[1];

    Card player1FaceUpCard = player1.getDeck()[2]; // third card in the array (second card was facedown card)
    Card player2FaceUpCard = player2.getDeck()[2];

    Deck player1NewDeck;
    Deck player2NewDeck;

    displayWar(player1PlayedCard, player2PlayedCard, player1FaceUpCard, player2FaceUpCard);
    //player 1 wins the war
    if(player1FaceUpCard.getValue() > player2FaceUpCard.getValue()) {
        player1NewDeck = new Deck(player1.getSize() + 3);
        player2NewDeck = new Deck(player2.getSize() - 3);

        //fixing player 1s new deck
        for(int i = 3; i < player1.getSize(); i++) {
            player1NewDeck.changeCard(i-3, player1.getDeck()[i]);
        }

        player1NewDeck.changeCard(player1NewDeck.getSize() - 6, player1PlayedCard);
        player1NewDeck.changeCard(player1NewDeck.getSize() - 5, player2PlayedCard);
        player1NewDeck.changeCard(player1NewDeck.getSize() - 4, player1FaceDownCard);
        player1NewDeck.changeCard(player1NewDeck.getSize() - 3, player2FaceDownCard);
        player1NewDeck.changeCard(player1NewDeck.getSize() - 2, player1FaceUpCard);
        player1NewDeck.changeCard(player1NewDeck.getSize() - 1, player2FaceUpCard);


        for(int i = 3; i < player2.getSize(); i++) {
            player2NewDeck.changeCard(i-3, player2.getDeck()[i]);
        }

        returnDecks[0] = player1NewDeck;
        returnDecks[1] = player2NewDeck;
        return returnDecks;
    }

    // player 2 wins the war
    if (player2FaceUpCard.getValue() > player1FaceUpCard.getValue()) {
        player1NewDeck = new Deck(player1.getSize() - 3);
        player2NewDeck = new Deck(player2.getSize() + 3);

        // fixing player 2s new deck
        for (int i = 3; i < player2.getSize(); i++) {
            player2NewDeck.changeCard(i - 3, player2.getDeck()[i]);
        }
        player2NewDeck.changeCard(player2NewDeck.getSize() - 6, player1PlayedCard);
        player2NewDeck.changeCard(player2NewDeck.getSize() - 5, player2PlayedCard);
        player2NewDeck.changeCard(player2NewDeck.getSize() - 4, player1FaceDownCard);
        player2NewDeck.changeCard(player2NewDeck.getSize() - 3, player2FaceDownCard);
        player2NewDeck.changeCard(player2NewDeck.getSize() - 2, player1FaceUpCard);
        player2NewDeck.changeCard(player2NewDeck.getSize() - 1, player2FaceUpCard);

        //fixing player 1s new deck
        for (int i = 3; i < player1.getSize(); i++) {
            player1NewDeck.changeCard(i - 3, player1.getDeck()[i]);
        }

        returnDecks[0] = player1NewDeck;
        returnDecks[1] = player2NewDeck;
        return returnDecks;

    }
    //else if they tie again in the war, redistribute cards back into the deck
    else {
        player1NewDeck = new Deck(player1.getSize());
        player2NewDeck = new Deck(player2.getSize());

        for(int i = 3; i < player1.getSize(); i++) {
            player1NewDeck.changeCard(i-3, player1.getDeck()[i]);
        }

        for (int i = 3; i < player2.getSize(); i++) {
            player2NewDeck.changeCard(i - 3, player2.getDeck()[i]);
        }

        //add cards back in here
        player1NewDeck.changeCard(player1NewDeck.getSize()-3, player1PlayedCard);
        player1NewDeck.changeCard(player1NewDeck.getSize()-2, player1FaceDownCard);
        player1NewDeck.changeCard(player1NewDeck.getSize()-1, player1FaceUpCard);


        player2NewDeck.changeCard(player2NewDeck.getSize() - 3, player2PlayedCard);
        player2NewDeck.changeCard(player2NewDeck.getSize() - 2, player2FaceDownCard);
        player2NewDeck.changeCard(player2NewDeck.getSize() - 1, player2FaceUpCard);

        returnDecks[0] = player1NewDeck;
        returnDecks[1] = player2NewDeck;
        return returnDecks;

    }
  }

  public static void displayCards(Card player1Card, Card player2Card, int counter, int delay, int player1Cards, int player2Cards) {
    int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();


    // https://docs.oracle.com/javase/8/docs/api/java/awt/Font.html
    // font help gained from here (increaseing font size)
    Font bold = new Font("Arial", Font.BOLD, 36); // Adjust font size here
    Font normal = new Font("Arial", Font.PLAIN, 20); // Adjust font size here

    if (counter == 1) {
        StdDraw.setCanvasSize(screenWidth, screenHeight);
        StdDraw.setXscale(0, screenWidth);
        StdDraw.setYscale(0, screenHeight);
        StdDraw.setTitle("WAR OUTPUT");        
    }

    
    StdDraw.enableDoubleBuffering();
    StdDraw.clear();
    StdDraw.setFont(bold);
    String player1Path = "Cards/" + (String) player1Card.getKey() + ".png";
    String player2Path = "Cards/" + (String) player2Card.getKey() + ".png";
    StdDraw.picture(screenWidth * 0.25, screenHeight * 0.60, player1Path, screenWidth * 0.225, screenHeight * 0.6);
    StdDraw.picture(screenWidth * 0.75, screenHeight * 0.60, player2Path, screenWidth * 0.225, screenHeight * 0.6);
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.text(screenWidth * 0.25, screenHeight / 2.0 - (screenHeight * 0.6) / 2 + 10, "PLAYER 1");
    StdDraw.text(screenWidth * 0.75, screenHeight / 2.0 - (screenHeight * 0.6) / 2 + 10, "PLAYER 2");

    //player card counts
    Font font = new Font("Arial", Font.PLAIN, 20); // Adjust font size here
    StdDraw.setFont(font);
    StdDraw.text(screenWidth * 0.25, screenHeight / 2.0 - (screenHeight * 0.6) / 2 - 40, "REMAINING CARDS: " + player1Cards);
    StdDraw.text(screenWidth * 0.75, screenHeight / 2.0 - (screenHeight * 0.6) / 2 - 40, "REMAINING CARDS: " + player2Cards);

    
    //declaring winner
    StdDraw.setFont(bold);
    StdDraw.setPenColor(StdDraw.GREEN);
    if (player1Card.getValue() > player2Card.getValue()) {
        StdDraw.text(screenWidth * 0.25, screenHeight / 2.0 - (screenHeight * 0.6) / 2 - 75, "PLAYER 1 WINS");
        StdDraw.show();
    } else if (player1Card.getValue() < player2Card.getValue()) {
        StdDraw.text(screenWidth * 0.75, screenHeight / 2.0 - (screenHeight * 0.6) / 2 - 75, "PLAYER 2 WINS");
        StdDraw.show();
    }




    StdDraw.show();
    
    
    StdDraw.pause(delay);
  }
   
  public static void displayWar(Card player1PlayedCard, Card player2PlayedCard, Card player1Card, Card player2Card) {
    int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    StdDraw.setCanvasSize(screenWidth, screenHeight);
    StdDraw.setXscale(0, screenWidth);
    StdDraw.setYscale(0, screenHeight);
    StdDraw.enableDoubleBuffering();
    StdDraw.clear();
    Font bold = new Font("Arial", Font.BOLD, 36);
    StdDraw.setFont(bold);
    StdDraw.text(screenWidth / 2.0, screenHeight / 2.0, "WAR");

    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.text(screenWidth * 0.25, screenHeight / 2.0 - (screenHeight * 0.6) / 2 + 10, "PLAYER 1");
    StdDraw.text(screenWidth * 0.75, screenHeight / 2.0 - (screenHeight * 0.6) / 2 + 10, "PLAYER 2");

    StdDraw.picture(screenWidth * 0.25 - 75, screenHeight * 0.60, "Cards/" + player1PlayedCard.getKey() + ".png", screenWidth * 0.225, screenHeight * 0.6); //player1playedcard
    StdDraw.show();
    StdDraw.pause(1000);
    StdDraw.picture(screenWidth * 0.25, screenHeight * 0.60, "playing_card_back(1).png", screenWidth * 0.30, screenHeight * 0.62); //facedown card
    StdDraw.show();
    StdDraw.pause(1000);
    StdDraw.picture(screenWidth * 0.25 + 75, screenHeight * 0.60, "Cards/" + player1Card.getKey() + ".png", screenWidth * 0.225, screenHeight * 0.6); //player1Card
    StdDraw.show();
    StdDraw.pause(1000);
    StdDraw.picture(screenWidth * 0.75 - 75, screenHeight * 0.60, "Cards/" + player2PlayedCard.getKey() + ".png", screenWidth * 0.225, screenHeight * 0.6); //player2playedcard
    StdDraw.show();
    StdDraw.pause(1000);
    StdDraw.picture(screenWidth * 0.75, screenHeight * 0.60, "playing_card_back(1).png", screenWidth * 0.30, screenHeight * 0.62); //facedown card
    StdDraw.show();
    StdDraw.pause(1000);
    StdDraw.picture(screenWidth * 0.75 + 75, screenHeight * 0.60, "Cards/" + player2Card.getKey() + ".png", screenWidth * 0.225, screenHeight * 0.6); //player2Card
    StdDraw.show();
    StdDraw.pause(1000);
    
    
    //StdDraw.pause(2500);
    StdDraw.setPenColor(StdDraw.GREEN);
    
    if(player1Card.getValue() > player2Card.getValue()) {
        StdDraw.text(screenWidth * 0.25, screenHeight / 2.0 - (screenHeight * 0.6) / 2 - 35, "PLAYER 1 WINS");
        StdDraw.show();
    }
    else if (player1Card.getValue() < player2Card.getValue()) {
        StdDraw.text(screenWidth * 0.75, screenHeight / 2.0 - (screenHeight * 0.6) / 2 - 35, "PLAYER 2 WINS");
        StdDraw.show();
    }
    else {
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(screenWidth * 0.5, screenHeight * 0.5 - 35, "TIE");
        StdDraw.show();
    }

    StdDraw.pause(2500);
}

   public static void gameOver() {
    Font bold = new Font("Arial", Font.BOLD, 36); 
    int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    StdDraw.setCanvasSize(screenWidth, screenHeight);
    StdDraw.setXscale(0, screenWidth);
    StdDraw.setYscale(0, screenHeight);
    StdDraw.setTitle("WAR OUTPUT");
    StdDraw.setFont(bold);
    StdDraw.text(screenWidth / 2.0, screenHeight / 2.0, "GAME OVER");
    StdDraw.show();
   }

   public static void playerTwoWins() {
       Font bold = new Font("Arial", Font.BOLD, 36); // Adjust font size here
       int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
       int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
       StdDraw.setCanvasSize(screenWidth, screenHeight);
       StdDraw.setXscale(0, screenWidth);
       StdDraw.setYscale(0, screenHeight);
       StdDraw.setTitle("WAR OUTPUT");
       StdDraw.setFont(bold);
       StdDraw.text(screenWidth / 2.0, screenHeight / 2.0, "PLAYER TWO WINS");
       StdDraw.show();
   }

   public static void playerOneWins() {
       Font bold = new Font("Arial", Font.BOLD, 36); // Adjust font size here
       int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
       int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
       StdDraw.setCanvasSize(screenWidth, screenHeight);
       StdDraw.setXscale(0, screenWidth);
       StdDraw.setYscale(0, screenHeight);
       StdDraw.setTitle("WAR OUTPUT");
       StdDraw.setFont(bold);
       StdDraw.text(screenWidth / 2.0, screenHeight / 2.0, "PLAYER ONE WINS");
       StdDraw.show();
   }



    public Card[] getDeck() {
        return this.deck;
    }

    public int getSize() {
        return this.size;
    }


    public String toString() {
        String string = "";
        for(int i = 0; i < deck.length; i++) {
            string = string + " " + deck[i].toString() + " ";
        }
        return string;
    }


    public String printDeck() {
        String string = "";
        for(int i = 0; i < deck.length; i++) {
            string = string + " " + deck[i].printCard() + " ";
        }
        return string; 
    }
}
