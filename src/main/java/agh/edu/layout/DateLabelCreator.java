package agh.edu.layout;

import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateLabelCreator {
    private int parentWidth;
    private int parentHeight;
    private Insets parentPadding;

    public DateLabelCreator(int parentWidth, int parentHeight, Insets parentPadding) {
        this.parentWidth = parentWidth;
        this.parentHeight = parentHeight;
        this.parentPadding = parentPadding;
    }

    public Label getDateLabel() {
        LocalDate currentDate = LocalDate.now();
        String dateString = DateTimeFormatter.ofPattern(" dd MMMM yyyy\n").format(currentDate);
        Label dateLabel = new Label(dateString);

        Image smallCalendarIcon = new Image(getClass().getClassLoader().getResourceAsStream("images/smallCalendar.png"));
        dateLabel.setGraphic(new ImageView(smallCalendarIcon));

        dateLabel.setMinWidth((parentWidth - parentPadding.getLeft() - parentPadding.getRight()) / 5);
        dateLabel.setContentDisplay(ContentDisplay.LEFT);
        dateLabel.setPadding(new Insets(0, 0, 70, 60)); //Alignment workaround, TODO: fix?

        return dateLabel;
    }
}
