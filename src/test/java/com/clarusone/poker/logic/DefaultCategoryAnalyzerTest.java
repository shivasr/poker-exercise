package com.clarusone.poker.logic;

import com.clarusone.poker.PokerHand;
import com.clarusone.poker.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

class DefaultCategoryAnalyzerTest {

    CategoryAnalyzer categoryAnalyzer;

    @BeforeEach
    public void setup() {
        categoryAnalyzer = new DefaultCategoryAnalyzer();
    }

    @Test
    void analyzeHandRoyalFlushes() {
        String hand = "AS QS TS KS JS";
        Category category = categoryAnalyzer.analyzeHand(new PokerHand(hand));
        Objects.requireNonNull(category);
        assert Category.ROYAL_FLUSH == category;
    }

    @Test
    void analyzeHandStraightFlushes() {
        String hand = "KD QD JD TD 9D";
        Category category = categoryAnalyzer.analyzeHand(new PokerHand(hand));
        assert category == Category.STRAIGHT_FLUSH;
    }

    @Test
    void analyzeHandFourOfAKind() {
        String hand = "7H 7C 7D 7S JH";
        Category category = categoryAnalyzer.analyzeHand(new PokerHand(hand));
        assert category == Category.FOUR_OF_A_KIND;
    }

    @Test
    void analyzeHandFullHouse() {
        String hand = "2S AH 2H AS AC";
        Category category = categoryAnalyzer.analyzeHand(new PokerHand(hand));
        assert category == Category.FULL_HOUSE;
    }

    @Test
    void analyzeThreeOfAKind() {
        String hand = "2S AH 2H AS AC";
        Category category = categoryAnalyzer.analyzeHand(new PokerHand(hand));
        assert category == Category.THREE_OF_A_KIND;
    }

    @Test
    void analyzeTwoPairs() {
        String hand = "2S 2H 4H 5S 4C";
        Category category = categoryAnalyzer.analyzeHand(new PokerHand(hand));
        assert category == Category.TWO_PAIR;
    }

    @Test
    void analyzeSinglePair() {
        String hand = "AH AC 5H 6H 7S";
        Category category = categoryAnalyzer.analyzeHand(new PokerHand(hand));
        assert category == Category.TWO_PAIR;
    }
}