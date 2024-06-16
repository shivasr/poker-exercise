package com.clarusone.poker.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Model class to hold a card
 */
public class Card {

    public static final Map<String, Integer> VALUE_TO_NUMBER_LOOKUP = new HashMap<>(){{
        put("2", 2);
        put("3", 3);
        put("4", 4);
        put("5", 5);
        put("6", 6);
        put("7", 7);
        put("8", 8);
        put("9", 9);
        put("T", 10);
        put("J", 11);
        put("Q", 12);
        put("K", 13);
        put("A", 14);
    }};

    final String value;

    final int internalValue;

    final String suit;

    public Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
        this.internalValue = VALUE_TO_NUMBER_LOOKUP.get(value);
    }

    public String getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return String.format("%s%s", getValue(), getSuit());
    }

    public int getInternalValue() {
        return internalValue;
    }
}
