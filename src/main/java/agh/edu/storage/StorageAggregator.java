package agh.edu.storage;

public class StorageAggregator {
    private UserConfigStorage userConfigStorage;
    private StatisticsStorage statisticsStorage;
    private FoodInfoStorage foodInfoStorage;

    public UserConfigStorage getUserConfigStorage() {
        return userConfigStorage;
    }

    public void setUserConfigStorage(UserConfigStorage userConfigStorage) {
        this.userConfigStorage = userConfigStorage;
    }

    public StatisticsStorage getStatisticsStorage() {
        return statisticsStorage;
    }

    public void setStatisticsStorage(StatisticsStorage statisticsStorage) {
        this.statisticsStorage = statisticsStorage;
    }

    public FoodInfoStorage getFoodInfoStorage() {
        return foodInfoStorage;
    }

    public void setFoodInfoStorage(FoodInfoStorage foodInfoStorage) {
        this.foodInfoStorage = foodInfoStorage;
    }

    public void initializeAll() {
        userConfigStorage.initializeStorage();
        statisticsStorage.initializeStorage();
        foodInfoStorage.initializeStorage();
    }
    public void finalizeAll() {
        userConfigStorage.finalizeStorage();
        statisticsStorage.finalizeStorage();
        foodInfoStorage.finalizeStorage();
    }
}
