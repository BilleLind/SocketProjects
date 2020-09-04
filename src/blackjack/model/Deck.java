package blackjack.model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> cards;

    // the count of how many cards that have been drawn from the deck
    public static int index = 0;

    public Deck(){
        cards = new ArrayList<>();

        // by having created enum Ranks and Suits, i can use the for loop to populate the cards arraylist
        for(Suits suit : Suits.values()) { // for each Suits they are created with each Ranks.
            for(Ranks rank : Ranks.values()) {
                cards.add(new Card(rank, suit));
            }
        }
    }
    public synchronized void shuffle() {
        Collections.shuffle(cards);
    }

    public synchronized Card draw() {
        Card = card = cards.get(0);
        cards.remove(0);
        index =+ 1;
        return card;
    }

    public ArrayList<Card> getCards(){
        return cards;
    }



}
