public class Card {
    private String key;
    private int value;

    //♠	 ♥	♦  ♣

    // ♠
    
    // ♥

    // ♦

    // ♣

    public Card(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }


    public String toString() {
        return("Key: " + this.key + " Value: " + this.value);
    }

    public String printCard() {
        return this.key;
    }

    //testing 
    // public static void main(String[] args) {
    //     Card card = new Card("4 of clubs", 4);
    //     System.out.println(card.getKey());
    //     System.out.println(card.getValue());
    // }


}


