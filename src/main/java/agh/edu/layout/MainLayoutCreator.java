package agh.edu.layout;

import agh.edu.model.UserConfig;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.List;


public class MainLayoutCreator {
    private final int windowWidth;
    private final int windowHeight;
    private UserConfig userConfig;

    private final VBox menu = new VBox();
    private final FlowPane mainWindow = new FlowPane();

    public MainLayoutCreator(UserConfig userConfig, int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.userConfig = userConfig;
    }

    private void createMenuBox() {
        ButtonCreator buttonCreator = new CircularButtonCreator();

        int vBoxWidth = windowWidth / 5;
        int padding = (vBoxWidth - CircularButtonCreator.BUTTON_WIDTH) / 2;

        menu.getStyleClass().add("VBox");
        menu.setPrefWidth(vBoxWidth);
        menu.setSpacing(10);                // TODO: Think about dynamic spacing
        menu.setFillWidth(true);
        menu.setAlignment(Pos.TOP_CENTER);

        List<Button> buttons = buttonCreator.getButtons();
        List<Text> texts = buttonCreator.getButtonsText();
        for(int i = 0; i < buttons.size(); i++) {
            menu.getChildren().addAll(buttons.get(i), texts.get(i));
        }

        menu.setPadding(new Insets(padding));
    }

    private void createMainWindow() {
        int paddingLeft = 40;
        int paddingRight = 40;
        int paddingTop = 40;
        int paddingBottom = 20;
        Insets padding = new Insets(paddingTop, paddingRight, paddingBottom, paddingLeft);
        mainWindow.setPadding(padding); // TODO: Think about dynamic

        TextCreator textCreator = new TextCreator(userConfig, padding, windowHeight, (windowWidth * 4) / 5);
        NutritionInfoContainerCreator nutritionInfoContainerCreator = new SoftRectangleNutritionContainer(userConfig, padding, windowHeight, (windowWidth * 4) / 5);


        mainWindow.getChildren().add(textCreator.getGreetingText());
        mainWindow.getChildren().addAll(
                nutritionInfoContainerCreator.getCalorieInfo(),
                nutritionInfoContainerCreator.getMacroInfo(),
                nutritionInfoContainerCreator.getMicroInfo()
        );
    }

    public BorderPane createMainLayout() {
        BorderPane mainLayout = new BorderPane();

        createMenuBox();
        createMainWindow();
        mainLayout.setLeft(menu);
        mainLayout.setCenter(mainWindow);

        mainLayout.getStyleClass().add("mainLayout");

        return mainLayout;
    }
}
