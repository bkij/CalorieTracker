package agh.edu;

import agh.edu.controllers.ButtonsController;
import agh.edu.layout.MainLayoutCreator;
import agh.edu.model.FoodInfo;
import agh.edu.model.Statistic;
import agh.edu.model.UserConfig;
import agh.edu.model.observable.CurrentInfo;
import agh.edu.parsers.NutritionalDataParser;
import agh.edu.parsers.USDAParser;
import agh.edu.storage.FoodInfoStorage;
import agh.edu.storage.StorageAggregator;
import agh.edu.storage.UserConfigStorage;
import agh.edu.storage.inMemory.FoodInfoFileStorage;
import agh.edu.storage.inMemory.StatisticFileStorage;
import agh.edu.storage.inMemory.UserConfigFileStorage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;

public class Main extends Application {
    private StorageAggregator storageAggregator;
    private ButtonsController buttonsController;
    private CurrentInfo currentInfo;

    @Override
    public void init() {
        String persistenceDir = "./";

        storageAggregator = new StorageAggregator();
        storageAggregator.setUserConfigStorage(new UserConfigFileStorage(persistenceDir, "user.cfg"));
        storageAggregator.setStatisticsStorage(new StatisticFileStorage(persistenceDir, "stats.ctdb"));
        storageAggregator.setFoodInfoStorage(new FoodInfoFileStorage(persistenceDir, "food.ctdb"));

        storageAggregator.initializeAll();

        if(!storageAggregator.getUserConfigStorage().configExists()) {
            // First application run
            createFoodDatabase(storageAggregator.getFoodInfoStorage());
            createDefaultConfig(storageAggregator.getUserConfigStorage());
        }

        if(storageAggregator.getStatisticsStorage().getByPredicate(stat -> stat.getDate().equals(LocalDate.now())).isEmpty()) {
            storageAggregator.getStatisticsStorage().save(new Statistic());    // Creates new statistic for today
        }
    }

    @Override
    public void start(Stage stage) {
        int initialWidth = 1024;
        int initialHeight = 768;

        Statistic currentDayStats = storageAggregator.getStatisticsStorage().getByPredicate(stat -> stat.getDate().equals(LocalDate.now())).first();
        UserConfig currentUserConfig = storageAggregator.getUserConfigStorage().get();
        currentInfo = new CurrentInfo(currentDayStats, currentUserConfig);

        MainLayoutCreator mainLayoutCreator = new MainLayoutCreator(currentInfo, initialWidth, initialHeight);

        /*
         * A bit of a hack here - buttons controller MUST be created before buttons are,
         * so actions can be attached to the buttons. Null pointers incoming if the
         * order is not respected
         */
        buttonsController = new ButtonsController(
                mainLayoutCreator.getWindowsReferences(),
                mainLayoutCreator.getLayoutReference(),
                storageAggregator,
                currentInfo
        );
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
        storageAggregator.finalizeAll();
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
