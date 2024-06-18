package com.clarusone.poker.logic.impl;

import com.clarusone.poker.PokerHand;
import com.clarusone.poker.logic.CategoryAnalyzer;
import com.clarusone.poker.model.Card;
import com.clarusone.poker.model.Category;
import com.clarusone.poker.utils.CardUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SequencedSet;
import java.util.stream.Collectors;

/**
 * Default implementation of the Category Analyzer
 */
public class DefaultCategoryAnalyzer implements CategoryAnalyzer {

    @Override
    public Category analyzeHand(PokerHand hand) {
        var cardsGroup = hand.getSortedCards().stream()
                .collect(Collectors.groupingBy(Card::getSuit));

        var cardsSameKind = hand.getSortedCards().stream()
                .collect(Collectors.groupingBy(Card::getValue));

        LinkedList<Integer> sequencesSet = CardUtils.findSequencesSet(hand.getSortedCards());

        if (cardsGroup.size() == 1) {
            return processAllSameSuit(hand.getSortedCards(), sequencesSet);
        } else if (!cardsSameKind.isEmpty()) {
            return processCardsWithSameKind(cardsSameKind, sequencesSet);
        }

        return Category.HIGH_CARD;
    }

    private Category processAllSameSuit(SequencedSet<Card> cardsList, LinkedList<Integer> sequencesSet) {


        Map<String, Integer> cardsSuitToSum = cardsList.stream()
                .collect(Collectors.groupingBy(Card::getSuit,
                        Collectors.summingInt(Card::getInternalValue)));

        if (sequencesSet.size() >= 2) {
            return Category.FLUSH;
        }

        int sum = cardsSuitToSum.values().stream().findFirst().orElse(0);

        if (cardsSuitToSum.size() == 1 && sum == Card.getMaxSumOfFive()) {
            return Category.ROYAL_FLUSH;
        } else if (sequencesSet.size() == 1 && sum != Card.getMaxSumOfFive()) {
            return Category.STRAIGHT_FLUSH;
        }

        return Category.ONE_PAIR;
    }

    private Category processCardsWithSameKind(Map<String, List<Card>> cardsList, LinkedList<Integer> sequencesSet) {
        List<Integer> counts = cardsList.values().stream()
                .map(List::size)
                .toList();

        if (counts.contains(4)) {
            return Category.FOUR_OF_A_KIND;
        } else if (counts.contains(3) && counts.contains(2)) {
            return Category.FULL_HOUSE;
        } else if (counts.contains(3)) {
            return Category.THREE_OF_A_KIND;
        } else if (counts.size() == 3) {
            return Category.TWO_PAIR;
        } else if (counts.size() == 4) {
            return Category.ONE_PAIR;
        } else if (sequencesSet.size() == 1) {
            return Category.STRAIGHT;
        }

        return Category.HIGH_CARD;
    }
}
