package agh.edu.layoutCreation;


import agh.edu.model.UserConfig;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class TextCreator {
    private UserConfig userConfig;
    private int windowHeight;

    public TextCreator(UserConfig userConfig, int windowHeight) {
        this.userConfig = userConfig;
        this.windowHeight = windowHeight;
    }
    public TextFlow getGreetingText() {
        Text greetingText = new Text("Hello, " + userConfig.getUserName() + "!\n");
        greetingText.getStyleClass().add("greetingText");
        Text followupText = new Text("        Your current nutritional balance: "); // Spaces for alignment, lol
        followupText.getStyleClass().add("followupText");

        TextFlow textFlow = new TextFlow(greetingText, followupText);
        textFlow.setTextAlignment(TextAlignment.LEFT);
        textFlow.setLineSpacing(15);
        textFlow.setMinHeight(windowHeight / 5);

        return textFlow;
    }
}
