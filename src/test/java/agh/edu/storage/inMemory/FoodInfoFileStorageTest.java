package agh.edu.storage.inMemory;

import org.junit.Test;

//TODO: Add more tests
public class FoodInfoFileStorageTest {
    private FoodInfoFileStorage foodInfoFileStorage;

    @Test
    public void testNoExceptionsOnNoFilePresent() {
        foodInfoFileStorage = new FoodInfoFileStorage("testPersistenceData/", "hihi");
    }


}