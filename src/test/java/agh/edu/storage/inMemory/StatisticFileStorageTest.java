package agh.edu.storage.inMemory;

import agh.edu.model.Statistic;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;
import java.util.SortedSet;

import static org.junit.Assert.*;

//TODO: Add more tests
public class StatisticFileStorageTest {
    private final String testDir = "src/main/resources/testPersistenceData/";
    private final String testName = "testStats.ctdb";
    private StatisticFileStorage statisticFileStorage;

    @Before
    public void initialize() {
        statisticFileStorage = new StatisticFileStorage(testDir, testName);
    }

    @Test
    public void noExceptionsOnNoFilePresent() {
        statisticFileStorage.initializeStorage();
    }

    @Test
    public void testGetByPredicateOnEmptyStorageReturnsEmpty() {
        Set<Statistic> emptyStats = statisticFileStorage.getByPredicate(stat -> true);
        assertNotNull(emptyStats);
        assertEquals(0, emptyStats.size());
    }

    @Test
    public void testGetByPredicate() {
        Statistic exampleStat = new Statistic();
        statisticFileStorage.save(exampleStat);
        SortedSet<Statistic> oneStat = statisticFileStorage.getByPredicate(stat -> true);
        assertNotNull(oneStat);
        assertEquals(1, oneStat.size());
        assertTrue(exampleStat.equals(oneStat.first()));
    }
}