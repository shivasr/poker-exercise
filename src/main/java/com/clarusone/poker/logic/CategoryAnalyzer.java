package com.clarusone.poker.logic;

import com.clarusone.poker.PokerHand;
import com.clarusone.poker.model.Category;

/**
 * Interface to analyse the categories
 */
public interface CategoryAnalyzer {

    /**
     * Analyse hand and determine the hand category.
     *
     * @param hand PokerHand containing sorted cards
     * @return Category the hand falls under.
     *
     * @see Category
     */
    Category analyzeHand(PokerHand hand);
}
