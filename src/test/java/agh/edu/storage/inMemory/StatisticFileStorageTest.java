package agh.edu.storage.inMemory;

import org.junit.Test;

//TODO: Add more tests
public class StatisticFileStorageTest {
    private final String testDir = "src/main/resources/testPersistenceData/";
    private final String testName = "testConfig.cfg";
    private StatisticFileStorage statisticFileStorage;

    @Test
    public void noExceptionsOnNoFilePresent() {
        statisticFileStorage = new StatisticFileStorage("testPersistenceData/", "keks");
        statisticFileStorage.initializeStorage();
    }

}