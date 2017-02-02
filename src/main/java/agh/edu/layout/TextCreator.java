package agh.edu.layout;


import agh.edu.model.observable.CurrentInfo;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;


public class TextCreator {
    private CurrentInfo currentInfo;
    private Insets parentPadding;
    private int parentHeight;
    private int parentWidth;
    private StringProperty userNameProperty;

    public TextCreator(CurrentInfo currentInfo, Insets parentPadding, int parentHeight, int parentWidth) {
        this.currentInfo = currentInfo;
        this.parentHeight = parentHeight;
        this.parentWidth = parentWidth;
        this.parentPadding = parentPadding;

        this.userNameProperty = new SimpleStringProperty();
        this.userNameProperty.bind(currentInfo.getObservableConfig().getUserNameProperty());
    }
    public TextFlow getGreetingText() {
        // TODO: bind to username
        Text greetingText = new Text();
        greetingText.textProperty().bind(Bindings.concat("Hello, ", userNameProperty, "!\n"));
        greetingText.getStyleClass().add("greetingText");

        Text followupText = new Text("       Your nutritional balance for today: "); // Spaces for alignment, lol
        followupText.getStyleClass().add("followupText");

        TextFlow textFlow = new TextFlow(greetingText, followupText);
        textFlow.setTextAlignment(TextAlignment.LEFT);
        textFlow.setLineSpacing(10);
        textFlow.setMinHeight(parentHeight / 5);
        textFlow.setMinWidth(2 * (parentWidth - parentPadding.getLeft() - parentPadding.getRight()) / 3);

        return textFlow;
    }
}
