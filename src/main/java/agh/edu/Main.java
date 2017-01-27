package agh.edu;

import agh.edu.LayoutCreation.MainLayoutCreator;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void init() {
        //TODO: Add info parsing on first use(?) and possibly database connection
    }
    @Override
    public void start(Stage stage) {
        //TODO: VBox for menu buttons
        //TODO: Flow pane for main windows
        int initialWidth = 1024;
        int initialHeight = 768;

        MainLayoutCreator mainLayoutCreator = new MainLayoutCreator(initialWidth, initialHeight);

        Scene primaryScene = new Scene(mainLayoutCreator.createMainLayout(), initialWidth, initialHeight);
        stage.setScene(primaryScene);
        stage.setTitle("CalorieTracker");
        stage.show();
    }
}
