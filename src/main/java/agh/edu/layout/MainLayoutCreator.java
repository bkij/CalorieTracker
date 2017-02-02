package agh.edu.layout;

import agh.edu.model.observable.CurrentInfo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import org.controlsfx.control.action.ActionMap;
import org.controlsfx.control.action.ActionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class MainLayoutCreator {
    private final int windowWidth;
    private final int windowHeight;
    private CurrentInfo currentInfo;

    private final VBox menu = new VBox();
    private final FlowPane mainWindow = new FlowPane();
    private final FlowPane statisticsWindow = new FlowPane();
    private final FlowPane addMealWindow = new FlowPane();
    private final FlowPane changeDayWindow = new FlowPane();
    private final BorderPane mainLayout = new BorderPane();

    public MainLayoutCreator(CurrentInfo currentInfo, int windowWidth, int windowHeight) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        this.currentInfo = currentInfo;
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

        int hgap = 30;                  // TODO: Again, think about dynamic
        int vgap = 50;
        mainWindow.setHgap(hgap);
        mainWindow.setVgap(vgap);

        TextCreator textCreator = new TextCreator(currentInfo, padding, windowHeight, (windowWidth * 4) / 5);
        DateLabelCreator dateLabelCreator = new DateLabelCreator(currentInfo, windowWidth, windowHeight, padding);
        NutritionInfoContainerCreator nutritionInfoContainerCreator = new SoftRectangleNutritionContainer(currentInfo, padding, windowHeight, (windowWidth * 4) / 5, vgap, hgap);


        mainWindow.getChildren().add(textCreator.getGreetingText());
        mainWindow.getChildren().add(dateLabelCreator.getDateLabel());
        mainWindow.getChildren().addAll(
                nutritionInfoContainerCreator.getCalorieInfo(),
                nutritionInfoContainerCreator.getMacroInfo(),
                nutritionInfoContainerCreator.getMicroInfo()
        );

        mainWindow.getStyleClass().add("mainWindow");
    }

    private void createChangeDayWindow() {
        Text dateText = new Text("Enter date: ");
        dateText.getStyleClass().add("genericBoldText");
        TextFlow textBox = new TextFlow(dateText);
        textBox.setMinWidth(4 * windowWidth / 5);
        textBox.setTextAlignment(TextAlignment.CENTER);
        DatePicker datePicker = new DatePicker(LocalDate.now());
        datePicker.setMinWidth(windowWidth / 5);
        Button okButton = ActionUtils.createButton(ActionMap.action("changeDayOK"));
        okButton.getStyleClass().add("okButton");
        Button cancelButton = ActionUtils.createButton(ActionMap.action("changeDayCancel"));
        cancelButton.getStyleClass().add("cancelButton");

        changeDayWindow.getChildren().addAll(textBox, datePicker, okButton, cancelButton);
        changeDayWindow.setMargin(datePicker, new Insets(0, 2 * windowWidth / 5 - 50, 0, 2 * windowWidth / 5 - 50));
        changeDayWindow.setAlignment(Pos.TOP_CENTER);
        changeDayWindow.setPadding(new Insets(50));
        changeDayWindow.setHgap(10);
        changeDayWindow.setVgap(20);
    }

    private void createStatsWindow() {

    }


    public BorderPane createMainLayout() {
        createChangeDayWindow();
        createStatsWindow();
        createMenuBox();
        createMainWindow();
        mainLayout.setLeft(menu);
        mainLayout.setCenter(mainWindow);

        mainLayout.getStyleClass().add("mainLayout");

        return mainLayout;
    }

    public List<FlowPane> getWindowsReferences() {
        List<FlowPane> windows = new ArrayList<>();
        windows.add(mainWindow);
        windows.add(changeDayWindow);
        windows.add(addMealWindow);
        windows.add(statisticsWindow);
        return windows;
    }

    public BorderPane getLayoutReference() {
        return mainLayout;
    }
}
