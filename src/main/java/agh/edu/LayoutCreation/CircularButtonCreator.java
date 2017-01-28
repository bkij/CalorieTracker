package agh.edu.LayoutCreation;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class CircularButtonCreator implements ButtonCreator {
    public static final int BUTTON_WIDTH = 150;

    public CircularButtonCreator() {
    }

    public List<Button> getButtons() {
        List<Button> buttons = new ArrayList<>();
        buttons.add(createDayChangeButton());
        buttons.add(createAddButton());
        buttons.add(createStatsButton());
        buttons.add(createOptionsButton());

        return buttons;
    }

    public List<Text> getButtonText() {
        List<Text> texts = new ArrayList<>();
        texts.add(new Text("Change day"));
        texts.add(new Text("Add meal"));
        texts.add(new Text("Statistics"));
        texts.add(new Text("Options"));

        for(Text text : texts) {
            text.setFill(Color.web("#f4f4f9"));
        }

        return texts;
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
