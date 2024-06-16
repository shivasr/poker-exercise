package com.clarusone.poker.logic;

import com.clarusone.poker.PokerHand;
import com.clarusone.poker.model.Categories;

/**
 * Interface to analyse the categories
 */
public interface CategoryAnalyzer {
    Categories analyzeHand(PokerHand hand);
}
