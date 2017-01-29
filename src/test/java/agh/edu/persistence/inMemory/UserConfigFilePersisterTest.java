package agh.edu.persistence.inMemory;

import agh.edu.model.UserConfig;
import agh.edu.util.Utils;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

//TODO: Add more tests
public class UserConfigFilePersisterTest {
    private UserConfigFilePersister userConfigFilePersister;
    private final String testDir = "src/main/resources/testPersistenceData/";
    private final String testName = "testConfig.cfg";

    @Test
    public void testNoExceptionsOnNoFilePresent() {
        userConfigFilePersister = new UserConfigFilePersister("testPersistenceData/", "random");
    }

    @Test
    public void testGetReturnsSaved() {
        userConfigFilePersister = new UserConfigFilePersister("a", "b");
        UserConfig userConfig = new UserConfig();
        userConfigFilePersister.save(userConfig);
        assertEquals(userConfig, userConfigFilePersister.get());
    }

    @Test
    public void testWritesToFile() {
        try {
            Files.deleteIfExists(Paths.get(testDir + testName));
        } catch(IOException e) {}
        userConfigFilePersister = new UserConfigFilePersister(testDir, testName);
        userConfigFilePersister.save(new UserConfig());
        userConfigFilePersister.finalizePersistence();
        assertTrue(Utils.fileExists(testDir, testName));
    }
}