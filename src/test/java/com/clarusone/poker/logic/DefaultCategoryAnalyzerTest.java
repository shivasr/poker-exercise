package com.clarusone.poker.logic;

import com.clarusone.poker.PokerHand;
import com.clarusone.poker.logic.impl.DefaultCategoryAnalyzer;
import com.clarusone.poker.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        assertEquals(Category.ROYAL_FLUSH, category);
    }

    @Test
    void analyzeHandStraightFlushes() {
        String hand = "KD QD JD TD 9D";
        Category category = categoryAnalyzer.analyzeHand(new PokerHand(hand));
        assertEquals(Category.STRAIGHT_FLUSH, category);
    }

    @Test
    void analyzeHandFlushes() {
        String hand = "AS 3S 4S 8S 2S";
        Category category = categoryAnalyzer.analyzeHand(new PokerHand(hand));
        assertEquals(Category.FLUSH, category);
    }

    @Test
    void analyzeHandAnotherFlushes() {
        String hand = "2H 3H 5H 6H 7H";
        Category category = categoryAnalyzer.analyzeHand(new PokerHand(hand));
        assertEquals(Category.FLUSH, category);
    }

    @Test
    void analyzeHandFourOfAKind() {
        String hand = "7H 7C 7D 7S JH";
        Category category = categoryAnalyzer.analyzeHand(new PokerHand(hand));
        assertEquals(Category.FOUR_OF_A_KIND, category);
    }

    @Test
    void analyzeHandFullHouse() {
        String hand = "2S AH 2H AS AC";
        Category category = categoryAnalyzer.analyzeHand(new PokerHand(hand));
        assertEquals(Category.FULL_HOUSE, category);
    }

    @Test
    void analyzeThreeOfAKind() {
        String hand = "AH AC 5H 6H AS";
        Category category = categoryAnalyzer.analyzeHand(new PokerHand(hand));
        assertEquals(Category.THREE_OF_A_KIND, category);
    }

    @Test
    void analyzeTwoPairs() {
        String hand = "2S 2H 4H 5S 4C";
        Category category = categoryAnalyzer.analyzeHand(new PokerHand(hand));
        assertEquals(Category.TWO_PAIR, category);
    }

    @Test
    void analyzeSinglePair() {
        String hand = "AH AC 5H 6H 7S";
        Category category = categoryAnalyzer.analyzeHand(new PokerHand(hand));
        assertEquals(Category.ONE_PAIR, category);
    }
}