package com.clarusone.poker.logic;

import com.clarusone.poker.model.Category;

/**
 * Provider class to get weightage for Hand comparison
 */
public interface CategoryWeightageProvider {

    /**
     * Get weightage for a given category of hand
     * @param category Category of hand
     *
     * @return int weightage
     */
    int getWeightage(Category category);
}
