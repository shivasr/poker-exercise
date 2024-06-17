package com.clarusone.poker.logic;

import com.clarusone.poker.model.Card;
import com.clarusone.poker.model.Suit;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class to sort the cards on the hand
 */
public class CardsSorter {

    public static final int REQUIRED_NUMBER_OF_CARDS = 5;

    public static LinkedHashSet<Card> sortCards(String fiveCard) {
        Comparator<Card> comparator = (a, b) -> {
            if (Objects.equals(a.getValue(), b.getValue())) {
                Suit suitA = Suit.valueOf(a.getSuit());
                Suit suitB = Suit.valueOf(b.getSuit());

                return Integer.compare(suitA.ordinal(), suitB.ordinal());
            }

            return Integer.compare(a.getInternalValue(), b.getInternalValue());
        };

        String[] cardTokens = fiveCard.split(" ");

        if (cardTokens.length != REQUIRED_NUMBER_OF_CARDS) {
            throw new RuntimeException(String.format("A hand must exactly have %s cards.", 5));
        }

        return Arrays.stream(cardTokens)
                .map(CardParser::toCard)
                .sorted(comparator)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
