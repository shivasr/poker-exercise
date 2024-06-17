package com.clarusone.poker.logic.impl;

import com.clarusone.poker.PokerHand;
import com.clarusone.poker.logic.CategoryWeightageProvider;
import com.clarusone.poker.logic.HandComparator;
import com.clarusone.poker.model.Card;

import java.util.Objects;
import java.util.stream.Collectors;

public class DefaultHandComparator implements HandComparator {

    // Weightage provider
    private final CategoryWeightageProvider provider;

    public DefaultHandComparator() {
        provider = new DefaultCategoryWeightageProvider();
    }

    /**
     * Compare two Poker Hands
     * @param leftHand Left Hand
     * @param rightHand Right Hand
     *
     * @return
     *          > 0 if this hand ranks higher than the opponent
     *          = 0 if this hand ranks same as the opponent
     *          < 0 if this hand ranks lesser than the opponent
     */
    @Override
    public int compareHands(PokerHand leftHand, PokerHand rightHand) {

        Objects.requireNonNull(leftHand.getHandCategory());
        Objects.requireNonNull(rightHand.getHandCategory());

        var thisHandWeightage = provider.getWeightage(leftHand.getHandCategory());
        var opponentHandWeightage = provider.getWeightage(rightHand.getHandCategory());

        var comparison = Integer.compare(thisHandWeightage, opponentHandWeightage);

        if (comparison == 0) { // If both hands score the same, then check the highest value
            return deepCompareInternalCards(leftHand, rightHand);
        }

        return comparison;
    }

    /**
     * Compare the rank of the highest value and return the comparison
     * @param thisHand Left Hand
     * @param opponentHand right Hand
     *
     * @return comparison by comparing the highest values
     */
    private int compareHighestRankMembers(PokerHand thisHand, PokerHand opponentHand) {

        int sumOfInternalValueThisHand = thisHand.getSortedCards().stream()
                .mapToInt(Card::getInternalValue)
                .sum();

        int sumOfInternalValueOpponentHand = opponentHand.getSortedCards().stream()
                .mapToInt(Card::getInternalValue)
                .sum();
        return Integer.compare(sumOfInternalValueThisHand,
                sumOfInternalValueOpponentHand);
    }

    /**
     * Method to compare two hands with the same rank.
     *
     * @param thisHand Left Hand
     * @param opponentHand right Hand
     *
     * @return comparison by comparing the internal cards
     */
    private int deepCompareInternalCards(PokerHand thisHand, PokerHand opponentHand) {
        var cardsSameKind = thisHand.getSortedCards().stream()
                .collect(Collectors.groupingBy(Card::getValue));

        int highestInternalValue;

        if (cardsSameKind.size() < 5) { // There are at least 1 group with with same kind
            int size = 5 - cardsSameKind.size();  // As per pigeon hole principle, if groups are lesser, at lease pne bin has more than one

            highestInternalValue = cardsSameKind.keySet().stream()
                    .filter(key -> cardsSameKind.get(key).size() >= size)
                    .mapToInt(key -> cardsSameKind.get(key).getLast().getInternalValue())
                    .findFirst().orElse(0);
        } else {
            highestInternalValue = compareHighestRankMembers(thisHand, opponentHand);
        }

        return highestInternalValue;
    }
}
