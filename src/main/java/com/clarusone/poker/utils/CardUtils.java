package com.clarusone.poker.utils;

import com.clarusone.poker.PokerHand;
import com.clarusone.poker.model.Card;

import java.util.*;
import java.util.stream.Collectors;

public class CardUtils {

    public static LinkedList<Integer> findSequencesSet(SequencedSet<Card> cardsList) {
        LinkedList<Integer> sequencesSet = new LinkedList<>();

        int numberOfCardsInSequence = 1;
        var iterator = cardsList.iterator();
        int current = iterator.next().getInternalValue();

        while (iterator.hasNext()) {
            int next = iterator.next().getInternalValue();

            if (next - current == 1) {
                numberOfCardsInSequence ++;
            } else {
                sequencesSet.add(numberOfCardsInSequence);
                numberOfCardsInSequence = 1;
            }

            current = next;
        }

        sequencesSet.add(numberOfCardsInSequence);
        return sequencesSet;
    }

    private static int compareHighestRankMembers(PokerHand thisHand, PokerHand opponentHand) {

        int sumOfInternalValueThisHand = thisHand.getSortedCards().stream()
                .mapToInt(Card::getInternalValue)
                .sum();

        int sumOfInternalValueOpponentHand = opponentHand.getSortedCards().stream()
                .mapToInt(Card::getInternalValue)
                .sum();
        return Integer.compare(sumOfInternalValueThisHand,
                sumOfInternalValueOpponentHand);
    }

    public static int deepCompareInternalCards(PokerHand thisHand, PokerHand opponentHand) {
        var cardsSameKind = thisHand.getSortedCards().stream()
                .collect(Collectors.groupingBy(Card::getValue));



        int highestInternalValue;

        if (cardsSameKind.size() < 5) { // There are at least 3 cards with same rank
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
