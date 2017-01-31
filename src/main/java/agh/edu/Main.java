package agh.edu;

import agh.edu.layout.MainLayoutCreator;
import agh.edu.model.FoodInfo;
import agh.edu.model.UserConfig;
import agh.edu.parsers.NutritionalDataParser;
import agh.edu.parsers.USDAParser;
import agh.edu.storage.FoodInfoStorage;
import agh.edu.storage.StatisticsStorage;
import agh.edu.storage.UserConfigStorage;
import agh.edu.storage.inMemory.FoodInfoFileStorage;
import agh.edu.storage.inMemory.StatisticFileStorage;
import agh.edu.storage.inMemory.UserConfigFileStorage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {
    private UserConfigStorage userConfigStorage;
    private StatisticsStorage statisticsStorage;
    private FoodInfoStorage foodInfoStorage;

    @Override
    public void init() {
        String persistenceDir = "./";
        userConfigStorage = new UserConfigFileStorage(persistenceDir, "user.cfg");
        statisticsStorage = new StatisticFileStorage(persistenceDir, "stats.ctdb");
        foodInfoStorage = new FoodInfoFileStorage(persistenceDir, "food.ctdb");

        userConfigStorage.initializeStorage();
        statisticsStorage.initializeStorage();
        foodInfoStorage.initializeStorage();

        if(!userConfigStorage.configExists()) {
            // First application run
            createFoodDatabase(foodInfoStorage);
            createDefaultConfig(userConfigStorage);
        }
    }

    @Override
    public void start(Stage stage) {
        int initialWidth = 1024;
        int initialHeight = 768;

        UserConfig currentUserConfig = userConfigStorage.get();
        MainLayoutCreator mainLayoutCreator = new MainLayoutCreator(currentUserConfig, initialWidth, initialHeight);

        BorderPane mainLayout = mainLayoutCreator.createMainLayout();

        Scene primaryScene = new Scene(mainLayout, initialWidth, initialHeight);
        primaryScene.getStylesheets().add("stylesheets/style.css");
        stage.setScene(primaryScene);
        stage.setTitle("CalorieTracker");
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void stop() {
        userConfigStorage.finalizeStorage();
        statisticsStorage.finalizeStorage();
        foodInfoStorage.finalizeStorage();
    }

    private void createFoodDatabase(FoodInfoStorage foodInfoStorage) {
        NutritionalDataParser parser = new USDAParser("foodData/", "ABBREV.txt");
        List<FoodInfo> foodData = parser.parse();
        for(FoodInfo info : foodData) {
            foodInfoStorage.save(info);
        }
    }

    private void createDefaultConfig(UserConfigStorage userConfigStorage) {
        userConfigStorage.save(new UserConfig());
    }
}
