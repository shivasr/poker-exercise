package com.clarusone.poker.logic;

import com.clarusone.poker.PokerHand;
import com.clarusone.poker.model.Category;

/**
 * Interface to analyse the categories
 */
public interface CategoryAnalyzer {
    Category analyzeHand(PokerHand hand);
}
