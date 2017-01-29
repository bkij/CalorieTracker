package agh.edu.persistence.inMemory;

import org.junit.Test;

public class StatisticAggregatorTest {
    private StatisticAggregator statisticAggregator;
    @Test
    public void noExceptionsOnNoFilePresent() {
        statisticAggregator = new StatisticAggregator("testPersistenceData/", "keks");
    }

}