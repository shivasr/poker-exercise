package com.clarusone.poker.utils;

import com.clarusone.poker.model.Card;

import java.util.Deque;
import java.util.LinkedList;
import java.util.SequencedSet;

public class CardUtils {

    private CardUtils() {
        // Private Constructor
    }

    public static Deque<Integer> findSequencesSet(SequencedSet<Card> cardsList) {
        Deque<Integer> sequencesSet = new LinkedList<>();

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
