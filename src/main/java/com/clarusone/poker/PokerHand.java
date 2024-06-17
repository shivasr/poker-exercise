package com.clarusone.poker;

import com.clarusone.poker.logic.CardsSorter;
import com.clarusone.poker.logic.CategoryAnalyzer;
import com.clarusone.poker.logic.CategoryWeightageProvider;
import com.clarusone.poker.logic.impl.DefaultCategoryAnalyzer;
import com.clarusone.poker.logic.impl.DefaultCategoryWeightageProvider;
import com.clarusone.poker.model.Card;
import com.clarusone.poker.model.Category;
import com.clarusone.poker.utils.CardUtils;

import java.util.Comparator;
import java.util.SequencedSet;

public class PokerHand implements Comparable<PokerHand> {

    private final SequencedSet<Card> sortedCards;

    private final CategoryAnalyzer categoryAnalyzer;

    private final CategoryWeightageProvider provider;

    /**
     * Constructor
     *
     * @param fiveCards Five Cards <Value><Suit> separated by space eg: 4S  5H 6H TS AC
     */
    public PokerHand(String fiveCards) {
        sortedCards = CardsSorter.sortCards(fiveCards);
        categoryAnalyzer = new DefaultCategoryAnalyzer();
        provider = new DefaultCategoryWeightageProvider();
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
        Category thisHandCategory = categoryAnalyzer.analyzeHand(this);
        Category opponentHandCategory = categoryAnalyzer.analyzeHand(opponentHand);

        System.out.println(thisHandCategory + ":" + this.getSortedCards());
        System.out.println(opponentHandCategory + ":" + opponentHand.getSortedCards());

        var thisHandWeightage = provider.getWeightage(thisHandCategory);
        var opponentHandWeightage = provider.getWeightage(opponentHandCategory);

        var comparison = Integer.compare(thisHandWeightage, opponentHandWeightage);

        if (comparison == 0) { // If both hands score the same, then check the highest value
            return CardUtils.deepCompareInternalCards(this, opponentHand);
        }

        return comparison;
    }

    public SequencedSet<Card> getSortedCards() {
        return sortedCards;
    }
}
