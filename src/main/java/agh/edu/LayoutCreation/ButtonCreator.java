package agh.edu.LayoutCreation;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
        Button changeDay = new Button("Change day");
        Image calendarImage = new Image(getClass().getClassLoader().getResourceAsStream(imagePath + "calendar.png"));
        changeDay.setGraphic(new ImageView(calendarImage));
        return changeDay;
    }
    private Button createAddButton() {
        Button addMeal = new Button("Add meal");
        Image addImage = new Image(getClass().getClassLoader().getResourceAsStream(imagePath + "add2.png"));
        addMeal.setGraphic(new ImageView(addImage));
        return addMeal;
    }
    private Button createStatsButton() {
        Button showStats = new Button("Statistics");
        Image graphImage = new Image(getClass().getClassLoader().getResourceAsStream(imagePath + "stats.png"));
        showStats.setGraphic(new ImageView(graphImage));
        return showStats;
    }
    private Button createOptionsButton() {
        Button showOptions = new Button("Show options");
        Image optionsImage = new Image(getClass().getClassLoader().getResourceAsStream(imagePath + "options.png"));
        showOptions.setGraphic(new ImageView(optionsImage));
        return showOptions;
    }
}
