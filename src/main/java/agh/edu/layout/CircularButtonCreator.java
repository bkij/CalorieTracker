package agh.edu.layout;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import org.controlsfx.control.action.ActionMap;
import org.controlsfx.control.action.ActionUtils;

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

    public List<Text> getButtonsText() {
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
        Button changeDay = ActionUtils.createButton(ActionMap.action("changeDayButton"));
        changeDay.getStyleClass().add("changeDayButton");

        return changeDay;
    }
    private Button createAddButton() {
        Button addMeal = ActionUtils.createButton(ActionMap.action("addButton"));
        addMeal.getStyleClass().add("addButton");

        return addMeal;
    }
    private Button createStatsButton() {
        Button showStats = ActionUtils.createButton(ActionMap.action("statsButton"));
        showStats.getStyleClass().add("statsButton");

        return showStats;
    }
    private Button createOptionsButton() {
        Button showOptions = ActionUtils.createButton(ActionMap.action("optionsButton"));
        showOptions.getStyleClass().add("optionsButton");
        return showOptions;
    }
}
