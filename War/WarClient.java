
public class WarClient {

    public static void visualWar() {
        int delay = 1500;
        String[] ids = {
                "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "10S", "JS", "QS", "KS", "AS",

                "2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "10H", "JH", "QH", "KH", "AH",

                "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "10D", "JD", "QD", "KD", "AD",

                "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "10C", "JC", "QC", "KC", "AC"
        };

        int[] vals = {
                2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,

                2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,

                2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,

                2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
        };

        Card[] cards = new Card[52];
        for (int i = 0; i < ids.length; i++) {
            cards[i] = new Card(ids[i], vals[i]);

        }
        Deck decky = new Deck(cards);

        decky.Shuffle();

        Deck player1;
        Deck player2;

        Deck[] yoink = decky.Deal();

        player1 = yoink[0];
        player2 = yoink[1];

        int counter = 1;
        while ((player1.getSize() > 0) && (player2.getSize() > 0)) {
            Deck.displayCards(player1.getDeck()[0], player2.getDeck()[0], counter, delay, player1.getSize(), player2.getSize());
            Deck[] transfer = Deck.hand(player1, player2);
            player1 = transfer[0];
            player2 = transfer[1];
            counter++;
        }

        if (player1.getSize() == 0) {
            Deck.playerTwoWins();
        }

        else {
            Deck.playerOneWins();
        }
    }

    public static void fastWar(int d) {
        int delay = d;
        String[] ids = {
                "2S", "3S", "4S", "5S", "6S", "7S", "8S", "9S", "10S", "JS", "QS", "KS", "AS",

                "2H", "3H", "4H", "5H", "6H", "7H", "8H", "9H", "10H", "JH", "QH", "KH", "AH",

                "2D", "3D", "4D", "5D", "6D", "7D", "8D", "9D", "10D", "JD", "QD", "KD", "AD",

                "2C", "3C", "4C", "5C", "6C", "7C", "8C", "9C", "10C", "JC", "QC", "KC", "AC"
        };

        int[] vals = {
                2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,

                2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,

                2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,

                2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
        };

        Card[] cards = new Card[52];
        for (int i = 0; i < ids.length; i++) {
            cards[i] = new Card(ids[i], vals[i]);

        }
        Deck decky = new Deck(cards);

        decky.Shuffle();

        Deck player1;
        Deck player2;

        Deck[] yoink = decky.Deal();

        player1 = yoink[0];
        player2 = yoink[1];

        int counter = 1;
        while ((player1.getSize() > 0) && (player2.getSize() > 0)) {
            Deck.displayCards(player1.getDeck()[0], player2.getDeck()[0], counter, d, player1.getSize(), player2.getSize());
            Deck[] transfer = Deck.hand(player1, player2);
            player1 = transfer[0];
            player2 = transfer[1];
            counter++;
        }

        if (player1.getSize() == 0) {
            Deck.playerTwoWins();
        }

        else {
            Deck.playerOneWins();
        }
    }
    public static void main(String[] args) {
        //fastWar(100);
        visualWar();
    }
}



