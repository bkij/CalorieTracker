package agh.edu.persistence.inMemory;

import org.junit.Test;

public class FoodInfoAggregatorTest {
    private FoodInfoAggregator foodInfoAggregator;

    @Test
    public void testNoExceptionsOnNoFilePresent() {
        foodInfoAggregator = new FoodInfoAggregator("testPersistenceData/", "hihi");
    }


}