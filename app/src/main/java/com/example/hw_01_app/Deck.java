package com.example.hw_01_app;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private int size;
    private ArrayList<Card> cards;

    public Deck(int size, ArrayList<Card> cards) {
        this.size = size;
        this.cards = cards;
    }

    public Deck(int size) {
        this.size = size;
        this.cards=new ArrayList<Card>();
    }

    public Deck(){
    }

    /**
     * This relevant for deck of 52 cards.
     * @param main - The activity including the drawable functions
     */
    public void addAllCards(MainActivity main){
        this.cards = new ArrayList<>();
        String name="";
        int drawableResourceId;
        for(int i=1;i<14;i++){
            name = "img_card_" + i + "a";
            drawableResourceId = main.getResources().getIdentifier(name, "drawable", main.getPackageName());
            Card c1 = new Card(drawableResourceId,i);
            this.cards.add(c1);
            name = "img_card_" + i + "b";
            drawableResourceId = main.getResources().getIdentifier(name, "drawable", main.getPackageName());
            Card c2 = new Card(drawableResourceId,i);
            this.cards.add(c2);
            name = "img_card_" + i + "c";
            drawableResourceId = main.getResources().getIdentifier(name, "drawable", main.getPackageName());
            Card c3 = new Card(drawableResourceId,i);
            this.cards.add(c3);
            name = "img_card_" + i + "d";
            drawableResourceId = main.getResources().getIdentifier(name, "drawable", main.getPackageName());
            Card c4 = new Card(drawableResourceId,i);
            this.cards.add(c4);
        }
    }



    public void shuffle() {
        Collections.shuffle(this.cards);
    }


    /**
     *
     * @param panda_cards - First empty. Gets 26 cards.
     * @param lion_cards- First empty. Gets 26 cards.
     * The function divides into 2 lists.
     */
    public void divideInto2(Deck panda_cards, Deck lion_cards) {
        for(int i=0;i<52;i += 2){
            panda_cards.addCard(this.cards.get(i));
            lion_cards.addCard(this.cards.get(i+1));
        }
    }

    public int getSize() {
        return size;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public Card takeCard(int i) {
        this.size -= 1 ;
        return this.cards.remove(i);
    }
}
