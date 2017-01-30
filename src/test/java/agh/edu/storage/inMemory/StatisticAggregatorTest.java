package agh.edu.storage.inMemory;

import org.junit.Test;

//TODO: Add more tests
public class StatisticAggregatorTest {
    private StatisticFileStorage statisticFileStorage;
    @Test
    public void noExceptionsOnNoFilePresent() {
        statisticFileStorage = new StatisticFileStorage("testPersistenceData/", "keks");
    }

}