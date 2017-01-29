package agh.edu.persistence.inMemory;

import org.junit.Test;

public class UserConfigFilePersisterTest {
    private UserConfigFilePersister userConfigFilePersister;

    @Test
    public void testNoExceptionsOnNoFilePresent() {
        userConfigFilePersister = new UserConfigFilePersister("testPersistenceData/", "random");
    }

}