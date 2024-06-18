package com.clarusone.poker.logic;

import com.clarusone.poker.exception.PokerException;
import com.clarusone.poker.model.Card;

import java.util.Objects;

/**
 * Card Parser for analysis
 */
public class CardParser {

    private CardParser() {
        // Private Constructor
    }

    public static Card toCard(String card) {
        Objects.requireNonNull(card);

        String[] tokens = card.split("");

        if (tokens.length == 2) {
            String value = tokens[0];
            String suit = tokens[1];

            return new Card(value, suit);
        }

        throw new PokerException("Invalid card: " + card);
    }
}
