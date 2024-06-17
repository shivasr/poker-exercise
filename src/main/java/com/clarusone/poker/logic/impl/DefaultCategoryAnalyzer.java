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

        if (cardsGroup.size() == 1) {
            return processAllSameSuit(hand.getSortedCards());
        } else if (!cardsSameKind.isEmpty()) {
            return processCardsWithSameKind(cardsSameKind);
        }

        return Category.HIGH_CARD;
    }

    private Category processAllSameSuit(SequencedSet<Card> cardsList) {
        var maxSum = Card.VALUE_TO_NUMBER_LOOKUP.get("A")
                + Card.VALUE_TO_NUMBER_LOOKUP.get("K")
                + Card.VALUE_TO_NUMBER_LOOKUP.get("Q")
                + Card.VALUE_TO_NUMBER_LOOKUP.get("J")
                + Card.VALUE_TO_NUMBER_LOOKUP.get("T");

        Map<String, Integer> cardsSuitToSum = cardsList.stream()
                .collect(Collectors.groupingBy(Card::getSuit,
                        Collectors.summingInt(Card::getInternalValue)));

        LinkedList<Integer> sequencesSet = CardUtils.findSequencesSet(cardsList);

        if (sequencesSet.size() >= 2) {
            return Category.FLUSH;
        }

        int sum = cardsSuitToSum.values().stream().findFirst().orElse(0);

        if (cardsSuitToSum.size() == 1 && sum == maxSum) {
            return Category.ROYAL_FLUSH;
        } else if (sequencesSet.size() == 1 && sum != maxSum) {
            return Category.STRAIGHT_FLUSH;
        }

        return Category.ONE_PAIR;
    }

    private Category processCardsWithSameKind(Map<String, List<Card>> cardsList) {
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
        }

        return Category.HIGH_CARD;
    }
}
