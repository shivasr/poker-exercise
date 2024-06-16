package com.clarusone.poker.logic;

import com.clarusone.poker.model.Card;
import org.junit.jupiter.api.Test;

class CardParserTest {

    @Test
    void toCard() {
        String cardStr = "2S";

        Card card = CardParser.toCard(cardStr) ;
        assert "2".equals(card.getValue());
        assert "S".equals(card.getSuit());
    }
}