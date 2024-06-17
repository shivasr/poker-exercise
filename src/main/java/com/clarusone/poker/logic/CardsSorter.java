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
    public static LinkedHashSet<Card> sortCards(String fiveCard) {
        Comparator<Card> comparator = (a, b) -> {
            if (Objects.equals(a.getValue(), b.getValue())) {
                Suit suitA = Suit.valueOf(a.getSuit());
                Suit suitB = Suit.valueOf(b.getSuit());

                return Integer.compare(suitA.ordinal(), suitB.ordinal());
            }

            return Integer.compare(a.getInternalValue(), b.getInternalValue());
        };

        return Arrays.stream(fiveCard.split(" "))
                .map(CardParser::toCard)
                .sorted(comparator)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
