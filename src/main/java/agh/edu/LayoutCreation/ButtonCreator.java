package agh.edu.LayoutCreation;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import java.util.List;

public class ButtonCreator {

    public ButtonCreator() {

    }

    public ObservableList<Button> getButtons() {
        // TODO: Check what must be done here
        return new List<Button>(createAddButton(), createDayChangeButton(), createStatsButton(), createOptionsButton());
    }
    // TODO: Add all the logic
    private Button createAddButton() {
        Button addMeal = new Button("Add meal");
        return addMeal;
    }
    private Button createDayChangeButton() {
        Button changeDay = new Button("Change day");
        return changeDay;
    }
    private Button createStatsButton() {
        Button showStats = new Button("Statistics");
        return showStats;
    }
    private Button createOptionsButton() {
        Button showOptions = new Button("Show options");
        return showOptions;
    }
}
