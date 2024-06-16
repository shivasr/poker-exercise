package com.clarusone.poker.logic;

import com.clarusone.poker.model.Card;

import java.util.Optional;

/**
 * Card Parser for analysis
 */
public class CardParser {

    public static Card toCard(String card) {
        assert Optional.ofNullable(card).isPresent();

        String[] tokens = card.split("");
        assert tokens.length == 2;

        String value = tokens[0];
        String suit = tokens[1];

        return new Card(value, suit);
    }
}
