package agh.edu.layout;

import agh.edu.model.observable.CurrentInfo;
import agh.edu.model.observable.ObservableStatistic;
import javafx.beans.binding.Binding;
import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.fxmisc.easybind.EasyBind;

import java.time.format.DateTimeFormatter;

public class DateLabelCreator {
    private int parentWidth;
    private int parentHeight;
    private Insets parentPadding;
    private Binding<String> dateTextBinding;

    public DateLabelCreator(CurrentInfo currentInfo, int parentWidth, int parentHeight, Insets parentPadding) {
        this.parentWidth = parentWidth;
        this.parentHeight = parentHeight;
        this.parentPadding = parentPadding;

        dateTextBinding = EasyBind.monadic(currentInfo.observableStatisticProperty())
                .selectProperty(ObservableStatistic::getDateProperty)
                .map(date -> DateTimeFormatter.ofPattern("dd MMMM yyyy").format(date));
    }

    public Label getDateLabel() {
        Label dateLabel = new Label();
        dateLabel.textProperty().bind(dateTextBinding);

        Image smallCalendarIcon = new Image(getClass().getClassLoader().getResourceAsStream("images/smallCalendar.png"));
        dateLabel.setGraphic(new ImageView(smallCalendarIcon));

        dateLabel.setMinWidth((parentWidth - parentPadding.getLeft() - parentPadding.getRight()) / 5);
        dateLabel.setContentDisplay(ContentDisplay.LEFT);
        dateLabel.setPadding(new Insets(0, 0, 70, 60)); //Alignment workaround, TODO: fix?

        return dateLabel;
    }
}
