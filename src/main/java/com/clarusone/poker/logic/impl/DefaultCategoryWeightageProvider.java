package com.clarusone.poker.logic.impl;

import com.clarusone.poker.logic.CategoryWeightageProvider;
import com.clarusone.poker.model.Category;

import java.util.HashMap;
import java.util.Map;

import static com.clarusone.poker.model.Category.*;

public class DefaultCategoryWeightageProvider implements CategoryWeightageProvider {

    final Map<Category, Integer> mapOfCategoryToWeightage;

    public DefaultCategoryWeightageProvider() {
        mapOfCategoryToWeightage = new HashMap<>() {{
            put(ROYAL_FLUSH, 10);
            put(STRAIGHT_FLUSH, 9);
            put(FOUR_OF_A_KIND, 8);
            put(FULL_HOUSE, 7);
            put(FLUSH, 6);
            put(STRAIGHT, 5);
            put(THREE_OF_A_KIND, 4);
            put(TWO_PAIR, 3);
            put(ONE_PAIR, 2);
            put(HIGH_CARD, 1);
        }};
    }

    @Override
    public int getWeightage(Category category) {
        return mapOfCategoryToWeightage.get(category);
    }
}
