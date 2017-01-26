package agh.edu.LayoutCreation;

import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class MainLayoutCreator {
    final Rectangle2D screenBound = Screen.getPrimary().getVisualBounds();

    MainLayoutCreator() {

    }

    VBox createMenuBox() {
        // TODO: Change this placeholder placing?
        VBox menu = new VBox(10);
        menu.setFillWidth(true);

        menu.getChildren().addAll(
                createDayChangeButton(),
                createAddButton(),
                createStatsButton(),
                createOptionsButton()
        );
        return menu;
    }

    Button createAddButton() {
        Button addMeal = new Button("Add meal");
        return addMeal;
    }
    Button createDayChangeButton() {
        Button changeDay = new Button("Change day");
        return changeDay;
    }
    Button createStatsButton() {
        Button showStats = new Button("Statistics");
        return showStats;
    }
    Button createOptionsButton() {
        Button showOptions = new Button("Show options");
        return showOptions;
    }
}
