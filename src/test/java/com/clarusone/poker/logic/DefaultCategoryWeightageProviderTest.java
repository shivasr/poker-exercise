package com.clarusone.poker.logic;

import com.clarusone.poker.logic.impl.DefaultCategoryWeightageProvider;
import com.clarusone.poker.model.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DefaultCategoryWeightageProviderTest {

    CategoryWeightageProvider provider = new DefaultCategoryWeightageProvider();

    @BeforeEach
    public void setup() {
        provider = new DefaultCategoryWeightageProvider();
    }

    @ParameterizedTest
    @CsvSource({"ROYAL_FLUSH, 10", "FULL_HOUSE, 7", "THREE_OF_A_KIND, 4", "HIGH_CARD, 1"})
    void getWeightage(String category, int expected) {

        int actualWeightage = provider.getWeightage(Category.valueOf(category));

        assert expected == actualWeightage;

    }
}