package com.clarusone.poker.model;

/**
 * Categories listed in decreasing order of their weightage.
 * Eg: FIVE_OF_A_KIND is higher rank than STRAIGHT_FLUSH
 */
public enum Category {
    ROYAL_FLUSH,
    STRAIGHT_FLUSH,
    FOUR_OF_A_KIND,
    FULL_HOUSE,
    FLUSH,
    STRAIGHT,
    THREE_OF_A_KIND,
    TWO_PAIR,
    ONE_PAIR,
    HIGH_CARD
}
