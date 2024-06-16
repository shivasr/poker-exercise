package com.clarusone.poker.logic;

import com.clarusone.poker.PokerHand;
import com.clarusone.poker.model.Categories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DefaultCategoryAnalyzerTest {

    CategoryAnalyzer categoryAnalyzer;

    @BeforeEach
    public void setup() {
        categoryAnalyzer = new DefaultCategoryAnalyzer();
    }

    @Test
    void analyzeHandRoyalFlushes() {
        String hand = "AS QS TS KS JS";
        Categories categories = categoryAnalyzer.analyzeHand(new PokerHand(hand));
        assert categories == Categories.ROYAL_FLUSH;
    }

    @Test
    void analyzeHandStraightFlushes() {
        String hand = "KD QD JD TD 9D";
        Categories categories = categoryAnalyzer.analyzeHand(new PokerHand(hand));
        assert categories == Categories.STRAIGHT_FLUSH;
    }

    @Test
    void analyzeHandFourOfAKind() {
        String hand = "7H 7C 7D 7S JH";
        Categories categories = categoryAnalyzer.analyzeHand(new PokerHand(hand));
        assert categories == Categories.FOUR_OF_A_KIND;
    }

    @Test
    void analyzeHandFullHouse() {
        String hand = "2S AH 2H AS AC";
        Categories categories = categoryAnalyzer.analyzeHand(new PokerHand(hand));
        assert categories == Categories.FULL_HOUSE;
    }

    @Test
    void analyzeThreeOfAKind() {
        String hand = "2S AH 2H AS AC";
        Categories categories = categoryAnalyzer.analyzeHand(new PokerHand(hand));
        assert categories == Categories.FULL_HOUSE;
    }

    @Test
    void analyzeTwoPairs() {
        String hand = "2S 2H 4H 5S 4C";
        Categories categories = categoryAnalyzer.analyzeHand(new PokerHand(hand));
        assert categories == Categories.TWO_PAIR;
    }

    @Test
    void analyzeSinglePair() {
        String hand = "AH AC 5H 6H 7S";
        Categories categories = categoryAnalyzer.analyzeHand(new PokerHand(hand));
        assert categories == Categories.TWO_PAIR;
    }
}