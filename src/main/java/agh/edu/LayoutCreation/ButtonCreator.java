package agh.edu.LayoutCreation;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class ButtonCreator {
    private final String imagePath = "images/";

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
        Button changeDay = new Button();
        changeDay.getStyleClass().add("changeDayButton");
        return changeDay;
    }
    private Button createAddButton() {
        Button addMeal = new Button();
        addMeal.getStyleClass().add("addButton");
        return addMeal;
    }
    private Button createStatsButton() {
        Button showStats = new Button();
        showStats.getStyleClass().add("statsButton");

        return showStats;
    }
    private Button createOptionsButton() {
        Button showOptions = new Button();
        showOptions.getStyleClass().add("optionsButton");
        return showOptions;
    }
}
