package com.clarusone.poker.logic;

import com.clarusone.poker.PokerHand;
import com.clarusone.poker.model.Card;
import com.clarusone.poker.model.Category;
import com.clarusone.poker.model.Suit;
import com.clarusone.poker.utils.CardUtils;

import java.util.*;
import java.util.stream.Collectors;

public class DefaultCategoryAnalyzer implements CategoryAnalyzer {

    @Override
    public Category analyzeHand(PokerHand hand) {

        Comparator<Card> comparator = (a, b) -> {
            if (Objects.equals(a.getValue(), b.getValue())) {
                Suit suitA = Suit.valueOf(a.getSuit());
                Suit suitB = Suit.valueOf(b.getSuit());

                System.out.printf("A -> %s", suitA.ordinal());
                System.out.printf("B -> %s", suitB.ordinal());

                return Integer.compare(suitA.ordinal(), suitB.ordinal());
            }

            return Integer.compare(a.getInternalValue(), b.getInternalValue());
        };

        List<Card> cardsList = Arrays.stream(hand.getFiveCards().split(" "))
                .map(CardParser::toCard)
                .sorted(comparator)
                .toList();

        Map<String, List<Card>> cardsGroup = cardsList.stream()
                .collect(Collectors.groupingBy(Card::getSuit));

        Map<String, List<Card>> cardsSameKind = Arrays.stream(hand.getFiveCards().split(" "))
                .map(CardParser::toCard)
                .sorted(comparator)
                .collect(Collectors.groupingBy(Card::getValue));

        System.out.println(cardsGroup.size());

        if (cardsGroup.size() == 1) {
            return processAllSameSuit(cardsList);
        } else if (cardsSameKind.size() == 2) {
            return processCardsWithSameKind(cardsSameKind);
        }
        System.out.println(cardsSameKind.size());

        return Category.FOUR_OF_A_KIND;
    }

    private Category processCardsWithSameKind(Map<String, List<Card>> cardsList) {
        System.out.println("Mind the cards: " + cardsList);
        List<Integer> counts = cardsList.values().stream()
                .map(List::size)
                .toList();

        System.out.println("counts: " + counts);
        if (counts.contains(4)) {
            return Category.FOUR_OF_A_KIND;
        } else if (counts.contains(3)) {
            return Category.THREE_OF_A_KIND;
        } else if (counts.size() == 3) {
            return Category.TWO_PAIR;
        } else if (counts.size() == 4) {
            return Category.ONE_PAIR;
        }

        return Category.HIGH_CARD;
    }

    private static Category processAllSameSuit(List<Card> cardsList) {
        int maxSum = Card.VALUE_TO_NUMBER_LOOKUP.get("A")
                + Card.VALUE_TO_NUMBER_LOOKUP.get("K")
                + Card.VALUE_TO_NUMBER_LOOKUP.get("Q")
                + Card.VALUE_TO_NUMBER_LOOKUP.get("J")
                + Card.VALUE_TO_NUMBER_LOOKUP.get("T");
        
        Map<String, Integer> cardsSuitToSum = cardsList.stream()
                .collect(Collectors.groupingBy(Card::getSuit, Collectors.summingInt(Card::getInternalValue)));

        Set<Integer> sequencesSet = CardUtils.findSequencesSet(cardsList);

        if (sequencesSet.size() > 2) {
            return Category.FLUSH;
        }

        int sum = cardsSuitToSum.values().stream().findFirst().orElse(0);

        if (cardsSuitToSum.size() == 1 && sum == maxSum) {
            return Category.ROYAL_FLUSH;
        } else if (sequencesSet.size() == 1 && sum != maxSum) {
            return Category.STRAIGHT_FLUSH;
        } else if (sequencesSet.size() == 2) {
            return Category.TWO_PAIR;
        }

        return Category.ONE_PAIR;
    }
}
