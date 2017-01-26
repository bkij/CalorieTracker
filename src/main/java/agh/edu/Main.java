package agh.edu;

import javafx.application.Application;
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
        stage.setTitle("CalorieTracker");
        stage.show();
    }
}
