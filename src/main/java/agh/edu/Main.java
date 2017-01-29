package agh.edu;

import agh.edu.layoutCreation.MainLayoutCreator;
import agh.edu.model.FoodInfo;
import agh.edu.model.UserConfig;
import agh.edu.parsers.NutritionalDataParser;
import agh.edu.parsers.USDAParser;
import agh.edu.persistence.FoodInfoPersistence;
import agh.edu.persistence.StatisticPersistence;
import agh.edu.persistence.UserConfigPersistence;
import agh.edu.persistence.inMemory.FoodInfoAggregator;
import agh.edu.persistence.inMemory.StatisticAggregator;
import agh.edu.persistence.inMemory.UserConfigFilePersister;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;

public class Main extends Application {
    private UserConfigPersistence userConfigPersistence;
    private StatisticPersistence statisticPersistence;
    private FoodInfoPersistence foodInfoPersistence;

    @Override
    public void init() {
        userConfigPersistence = new UserConfigFilePersister();
        statisticPersistence = new StatisticAggregator();
        foodInfoPersistence = new FoodInfoAggregator();

        userConfigPersistence.initializePersistence();
        statisticPersistence.intializePersistence();
        foodInfoPersistence.initializePersistence();

        if(!userConfigPersistence.configExists()) {
            // First application run
            createFoodDatabase(foodInfoPersistence);
            createDefaultConfig(userConfigPersistence);
        }
    }

    @Override
    public void start(Stage stage) {
        //TODO: VBox for menu buttons
        //TODO: Flow pane for main windows
        int initialWidth = 1024;
        int initialHeight = 768;

        MainLayoutCreator mainLayoutCreator = new MainLayoutCreator(initialWidth, initialHeight);

        Scene primaryScene = new Scene(mainLayoutCreator.createMainLayout(), initialWidth, initialHeight);
        primaryScene.getStylesheets().add("stylesheets/style.css");
        stage.setScene(primaryScene);
        stage.setTitle("CalorieTracker");
        stage.show();
    }

    private void createFoodDatabase(FoodInfoPersistence foodInfoPersistence) {
        NutritionalDataParser parser = new USDAParser();
        List<FoodInfo> foodData = parser.parse("foodData/");
        for(FoodInfo info : foodData) {
            foodInfoPersistence.save(info);
        }
    }

    private void createDefaultConfig(UserConfigPersistence userConfigPersistence) {
        userConfigPersistence.save(new UserConfig());
    }
}
