package com.clarusone.poker.logic;

import com.clarusone.poker.model.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardParserTest {

    @Test
    void toCard() {
        String cardStr = "2S";

        Card card = CardParser.toCard(cardStr) ;
        assertEquals("2", card.getValue());
        assertEquals("S", card.getSuit());
    }
}