package com.clarusone.poker.logic;

import com.clarusone.poker.model.Card;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class CardsSorterTest {

    @Test
    void sortCards() {
        String testHand = "KS KH KD 2S 2C";

        LinkedHashSet<Card> cards = CardsSorter.sortCards(testHand);

        Objects.requireNonNull(cards);
        assert cards.size() == 5;
    }

    @Test
    void sortCardsInvalidHand() {
        String testHand = "KS KH KD 2S";

        Exception exception = assertThrows(RuntimeException.class, () -> CardsSorter.sortCards(testHand));

        assertEquals("A hand must exactly have 5 cards.", exception.getMessage());
    }
}