package com.clarusone.poker.logic;

import com.clarusone.poker.PokerHand;

/**
 * Interface to abstravec comparison logic.
 */
public interface HandComparator {
    int compareHands(PokerHand leftHand, PokerHand rightHAnd);
}
