package agh.edu.LayoutCreation;

import javafx.geometry.Rectangle2D;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;


public class MainLayoutCreator {
    private int windowWidth;
    private int windowHeight;
    private final VBox menu = new VBox();
    private final FlowPane mainWindow = new FlowPane();

    public MainLayoutCreator(int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    private void createMenuBox() {
        // TODO: Change this placeholder placing?
        ButtonCreator buttonCreator = new ButtonCreator();

        menu.setSpacing(10);
        menu.setFillWidth(true);
        menu.getChildren().addAll(buttonCreator.getButtons());
    }

    private void createMainWindow() {

    }

    public BorderPane createMainLayout() {
        BorderPane mainLayout = new BorderPane();

        createMenuBox();
        mainLayout.setLeft(menu);


        mainLayout.setCenter(mainWindow);

        return mainLayout;
    }
}
