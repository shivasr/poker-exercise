package com.clarusone.poker.logic;

import com.clarusone.poker.model.Card;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.SequencedSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CardsSorterTest {

    @Test
    void sortCards() {
        String testHand = "KS KH KD 2S 2C";

        SequencedSet<Card> cards = CardsSorter.sortCards(testHand);

        Objects.requireNonNull(cards);
        assertEquals(5, cards.size());
    }

    @Test
    void sortCardsInvalidHand() {
        String testHand = "KS KH KD 2S";

        Exception exception = assertThrows(RuntimeException.class, () -> CardsSorter.sortCards(testHand));

        assertEquals("A hand must exactly have 5 cards.", exception.getMessage());
    }
}