package com.clarusone.poker.utils;

import com.clarusone.poker.model.Card;

import java.util.LinkedList;
import java.util.SequencedSet;

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
}
