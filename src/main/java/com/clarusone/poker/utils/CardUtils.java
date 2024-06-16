package com.clarusone.poker.utils;

import com.clarusone.poker.model.Card;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CardUtils {

    public static Set<Integer> findSequencesSet(List<Card> cardsList) {
        Set<Integer> sequencesSet = new HashSet<>();
        int numberOfCardsInSequence = 0;

        for (int i = 1; i < cardsList.size(); i++) {
            if (cardsList.get(i).getInternalValue() - cardsList.get(i - 1).getInternalValue() == 1) {
                numberOfCardsInSequence ++;
            } else {
                sequencesSet.add(numberOfCardsInSequence);
                numberOfCardsInSequence = 0;
            }
        }

        sequencesSet.add(numberOfCardsInSequence);
        return sequencesSet;
    }
}
