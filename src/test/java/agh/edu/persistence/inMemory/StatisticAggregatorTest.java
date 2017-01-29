package agh.edu.persistence.inMemory;

import org.junit.Test;

//TODO: Add more tests
public class StatisticAggregatorTest {
    private StatisticAggregator statisticAggregator;
    @Test
    public void noExceptionsOnNoFilePresent() {
        statisticAggregator = new StatisticAggregator("testPersistenceData/", "keks");
    }

}