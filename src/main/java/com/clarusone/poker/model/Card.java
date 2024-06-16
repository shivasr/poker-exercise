package com.clarusone.poker.model;

/**
 * Model class to hold a card
 */
public class Card {
    public Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
    }

    String value;

    String suit;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSuit() {
        return suit;
    }

    public void setSuit(String suit) {
        this.suit = suit;
    }
}
