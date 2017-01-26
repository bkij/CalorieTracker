package agh.edu.LayoutCreation;

import javafx.geometry.Rectangle2D;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;


public class MainLayoutCreator {
    private final Rectangle2D screenBound = Screen.getPrimary().getVisualBounds();
    private final VBox menu = new VBox();
    private final FlowPane mainWindow = new FlowPane();

    public MainLayoutCreator() {

    }

    public void createMenuBox() {
        // TODO: Change this placeholder placing?
        ButtonCreator buttonCreator = new ButtonCreator();

        menu.setSpacing(10);
        menu.setFillWidth(true);
        menu.getChildren().addAll(buttonCreator.getButtons());
    }

    public void createMainWindow() {

    }

    public BorderPane createMainLayout() {
        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(menu);
        mainLayout.setCenter(mainWindow);

        return mainLayout;
    }
}
