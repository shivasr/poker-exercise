package com.clarusone.poker;

import com.clarusone.poker.logic.CardsSorter;
import com.clarusone.poker.logic.CategoryAnalyzer;
import com.clarusone.poker.logic.HandComparator;
import com.clarusone.poker.logic.impl.DefaultCategoryAnalyzer;
import com.clarusone.poker.logic.impl.DefaultHandComparator;
import com.clarusone.poker.model.Card;
import com.clarusone.poker.model.Category;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.SequencedSet;

public class PokerHand implements Comparable<PokerHand> {

    private final SequencedSet<Card> sortedCards;

    private final CategoryAnalyzer categoryAnalyzer;

    private final HandComparator handComparator;

    private Category handCategory;

    /**
     * Constructor
     *
     * @param fiveCards Five Cards <Value><Suit> separated by space eg: 4S  5H 6H TS AC
     */
    public PokerHand(String fiveCards) {
        sortedCards = CardsSorter.sortCards(fiveCards);
        categoryAnalyzer = new DefaultCategoryAnalyzer();
        handComparator = new DefaultHandComparator();
    }

    /**
     * @see Comparator
     *
     * @param opponentHand the object to be compared.
     * @return
     *          > 0 if this hand ranks higher than the opponent
     *          = 0 if this hand ranks same as the opponent
     *          < 0 if this hand ranks lesser than the opponent
     */
    @Override
    public int compareTo(PokerHand opponentHand) {
        computeHandCategoryIfRequired(this);
        computeHandCategoryIfRequired(opponentHand);

        return handComparator.compareHands(this, opponentHand);
    }

    public SequencedSet<Card> getSortedCards() {
        return sortedCards;
    }

    public Category getHandCategory() {
        return handCategory;
    }

    public void computeHandCategoryIfRequired(PokerHand pokerHand) {
        if (Optional.ofNullable(pokerHand.handCategory).isEmpty()) {
            pokerHand.handCategory = categoryAnalyzer.analyzeHand(pokerHand);
        }
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof PokerHand opponentHand) {
            computeHandCategoryIfRequired(this);
            computeHandCategoryIfRequired(opponentHand);

            return this.handCategory == opponentHand.handCategory;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sortedCards, categoryAnalyzer, handComparator, handCategory);
    }
}
