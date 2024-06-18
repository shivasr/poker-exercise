package com.clarusone.poker.logic.impl;

import com.clarusone.poker.logic.CategoryWeightageProvider;
import com.clarusone.poker.model.Category;

import java.util.EnumMap;

import static com.clarusone.poker.model.Category.*;

public class DefaultCategoryWeightageProvider implements CategoryWeightageProvider {

    final EnumMap<Category, Integer> mapOfCategoryToWeightage;

    public DefaultCategoryWeightageProvider() {
        mapOfCategoryToWeightage = new EnumMap<>(Category.class);
        mapOfCategoryToWeightage.put(ROYAL_FLUSH, 10);
        mapOfCategoryToWeightage.put(STRAIGHT_FLUSH, 9);
        mapOfCategoryToWeightage.put(FOUR_OF_A_KIND, 8);
        mapOfCategoryToWeightage.put(FULL_HOUSE, 7);
        mapOfCategoryToWeightage.put(FLUSH, 6);
        mapOfCategoryToWeightage.put(STRAIGHT, 5);
        mapOfCategoryToWeightage.put(THREE_OF_A_KIND, 4);
        mapOfCategoryToWeightage.put(TWO_PAIR, 3);
        mapOfCategoryToWeightage.put(ONE_PAIR, 2);
        mapOfCategoryToWeightage.put(HIGH_CARD, 1);
    }

    @Override
    public int getWeightage(Category category) {
        return mapOfCategoryToWeightage.get(category);
    }
}
