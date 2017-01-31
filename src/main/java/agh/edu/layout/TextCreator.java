package agh.edu.layout;


import agh.edu.model.UserConfig;
import javafx.geometry.Insets;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;


public class TextCreator {
    private UserConfig userConfig;
    private Insets parentPadding;
    private int parentHeight;
    private int parentWidth;

    public TextCreator(UserConfig userConfig, Insets parentPadding, int parentHeight, int parentWidth) {
        this.userConfig = userConfig;
        this.parentHeight = parentHeight;
        this.parentWidth = parentWidth;
        this.parentPadding = parentPadding;
    }
    public TextFlow getGreetingText() {
        Text greetingText = new Text("Hello, " + userConfig.getUserName() + "!\n");
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
