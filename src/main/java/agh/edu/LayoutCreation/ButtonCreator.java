package agh.edu.LayoutCreation;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class ButtonCreator {

    public ButtonCreator() {

    }

    public List<Button> getButtons() {
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(createDayChangeButton());
        buttons.add(createAddButton());
        buttons.add(createStatsButton());
        buttons.add(createOptionsButton());

        return buttons;
    }

    // TODO: Add all the logic
    private Button createDayChangeButton() {
        Button changeDay = new Button("Change day");
        return changeDay;
    }
    private Button createAddButton() {
        Button addMeal = new Button("Add meal");
        return addMeal;
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
