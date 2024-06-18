package com.clarusone.poker.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Model class to hold a card
 */
public class Card {

    private static final Map<String, Integer> VALUE_TO_NUMBER_LOOKUP = HashMap.newHashMap(13);

    static {
        VALUE_TO_NUMBER_LOOKUP.put("2", 2);
        VALUE_TO_NUMBER_LOOKUP.put("3", 3);
        VALUE_TO_NUMBER_LOOKUP.put("4", 4);
        VALUE_TO_NUMBER_LOOKUP.put("5", 5);
        VALUE_TO_NUMBER_LOOKUP.put("6", 6);
        VALUE_TO_NUMBER_LOOKUP.put("7", 7);
        VALUE_TO_NUMBER_LOOKUP.put("8", 8);
        VALUE_TO_NUMBER_LOOKUP.put("9", 9);
        VALUE_TO_NUMBER_LOOKUP.put("T", 10);
        VALUE_TO_NUMBER_LOOKUP.put("J", 11);
        VALUE_TO_NUMBER_LOOKUP. put("Q", 12);
        VALUE_TO_NUMBER_LOOKUP.put("K", 13);
        VALUE_TO_NUMBER_LOOKUP.put("A", 14);
    }

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

    public static int getMaxSumOfFive() {
        return Card.VALUE_TO_NUMBER_LOOKUP.get("A")
                + Card.VALUE_TO_NUMBER_LOOKUP.get("K")
                + Card.VALUE_TO_NUMBER_LOOKUP.get("Q")
                + Card.VALUE_TO_NUMBER_LOOKUP.get("J")
                + Card.VALUE_TO_NUMBER_LOOKUP.get("T");
    }
}
