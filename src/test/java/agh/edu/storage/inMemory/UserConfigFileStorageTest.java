package agh.edu.storage.inMemory;

import agh.edu.model.UserConfig;
import agh.edu.util.Utils;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserConfigFileStorageTest {
    private UserConfigFileStorage userConfigFileStorage;
    private final String testDir = "src/main/resources/testPersistenceData/";
    private final String testName = "testConfig.cfg";

    @Test
    public void testNoExceptionsOnNoFilePresent() {
        userConfigFileStorage = new UserConfigFileStorage("testPersistenceData/", "random");
        userConfigFileStorage.initializeStorage();
    }

    @Test
    public void testGetReturnsSaved() {
        userConfigFileStorage = new UserConfigFileStorage("a", "b");
        UserConfig userConfig = new UserConfig();
        userConfigFileStorage.save(userConfig);
        assertEquals(userConfig, userConfigFileStorage.get());
    }

    @Test
    public void testWritesToFile() {

        userConfigFileStorage = new UserConfigFileStorage(testDir, testName);
        userConfigFileStorage.save(new UserConfig());
        userConfigFileStorage.finalizeStorage();
        assertTrue(Utils.fileExists(testDir, testName));
        try {
            Files.deleteIfExists(Paths.get(testDir + testName));
        } catch(IOException e) {
            throw new RuntimeException("Something's wrong with the test code - can't delete file");
        }
    }
}