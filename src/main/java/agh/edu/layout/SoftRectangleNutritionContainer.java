package agh.edu.layout;

import agh.edu.layout.customComponents.PercentProgressIndicator;
import agh.edu.model.observable.CurrentInfo;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

// TODO: Refactor this for the love of god
public class SoftRectangleNutritionContainer implements NutritionInfoContainerCreator {
    /* Properties */
    private DoubleProperty BMR;
    private DoubleProperty protRequirement;
    private DoubleProperty carbRequirement;
    private DoubleProperty fatRequirement;

    private DoubleProperty currentCalories;
    private DoubleProperty currentProt;
    private DoubleProperty currentCarb;
    private DoubleProperty currentFat;

    /* Non-properties */
    private CurrentInfo currentInfo;
    private Insets parentPadding;
    private int parentHeight;
    private int parentWidth;
    private int parentVgap;
    private int parentHGap;
    private int containerAreaWidth;
    private int calorieBoxWidth;
    private int calorieBoxHeight;
    private int macroBoxWidth;
    private int macroBoxHeight;
    private final int BOXES_PADDING = 10;

    public SoftRectangleNutritionContainer(CurrentInfo currentInfo, Insets parentPadding, int parentHeight, int parentWidth, int vgap, int hgap) {
        this.currentInfo = currentInfo;
        this.parentHeight = parentHeight;
        this.parentWidth = parentWidth;
        this.parentPadding = parentPadding;
        this.parentVgap = vgap;
        this.parentHGap = hgap;

        this.containerAreaWidth = parentWidth - (int)parentPadding.getLeft() - (int)parentPadding.getRight();

    }

    @Override
    public VBox getCalorieInfo() {
        VBox calorieContainer = getBasicStyledVBox();
        calorieBoxWidth = (containerAreaWidth / 4) - parentHGap;
        calorieBoxHeight = parentHeight / 6;

        TextFlow calorieLabel = new TextFlow(new Text("Calories:"));
        calorieLabel.setMinWidth(calorieBoxWidth);
        calorieLabel.setMinHeight(calorieBoxHeight / 3);
        calorieLabel.setTextAlignment(TextAlignment.CENTER);
        calorieLabel.getStyleClass().add("nutritionLabelText");

        Text currentCaloriesText = new Text();
        currentCaloriesText.textProperty().bind(Bindings.convert(currentInfo.getObservableStatistic().getKcalProperty()));
        Text calorieRequirementsText = new Text();
        calorieRequirementsText.textProperty().bind(
                Bindings.concat(
                    " / ",
                    Bindings.convert(currentInfo.getObservableConfig().getBMRProperty()),
                    " kcal "
                )
        );
        TextFlow calorieValues = new TextFlow(currentCaloriesText, calorieRequirementsText);
        calorieValues.setTextAlignment(TextAlignment.CENTER);
        calorieValues.getStyleClass().add("nutritionValuesText");

        StackPane progressIndicator = new PercentProgressIndicator(
                currentInfo.getObservableStatistic().getKcalProperty(),
                currentInfo.getObservableConfig().getBMRProperty()
        );

        calorieContainer.getChildren().addAll(calorieLabel, calorieValues, progressIndicator);
        calorieContainer.setMinHeight(calorieBoxHeight);
        calorieContainer.setMinHeight(calorieBoxWidth);
        return calorieContainer;
    }

    @Override
    public HBox getMacroInfo() {
        HBox macroContainer = getBasicStyledHBox();
        macroBoxWidth = containerAreaWidth - calorieBoxWidth - (parentHGap * 3); // TODO: parentHGap *3 is a workaound, change?
        macroBoxHeight = calorieBoxHeight;
        int macroRegionWidth = (macroBoxWidth - (4 * BOXES_PADDING)) / 3;

        // PROTEIN PART
        TextFlow proteinLabel = new TextFlow(new Text("Protein:"));
        proteinLabel.setMinWidth(macroRegionWidth);
        proteinLabel.setMinHeight(macroBoxHeight / 3);
        proteinLabel.setTextAlignment(TextAlignment.CENTER);
        proteinLabel.getStyleClass().add("nutritionLabelText");

        Text currentProtText = new Text();
        currentProtText.textProperty().bind(Bindings.convert(currentInfo.getObservableStatistic().getProtProperty()));
        Text protRequirementsText = new Text();
        protRequirementsText.textProperty().bind(
                Bindings.concat(
                        " / ",
                        Bindings.convert(currentInfo.getObservableConfig().getProtProperty()),
                        " gram"
                )
        );
        TextFlow proteinValues = new TextFlow(currentProtText, protRequirementsText);
        proteinValues.setTextAlignment(TextAlignment.CENTER);
        proteinValues.getStyleClass().add("nutritionValuesText");

        PercentProgressIndicator proteinIndicator = new PercentProgressIndicator(
                currentInfo.getObservableStatistic().getProtProperty(),
                currentInfo.getObservableConfig().getProtProperty()
        );

        VBox proteinBox = getSubStyledVbox();
        proteinBox.setMinHeight(macroBoxHeight);
        proteinBox.getChildren().addAll(proteinLabel, proteinValues, proteinIndicator);

        // CARBS PART
        TextFlow carbsLabel = new TextFlow(new Text("Carbs:"));
        carbsLabel.setMinWidth(macroRegionWidth);
        carbsLabel.setMinHeight(macroBoxHeight / 3);
        carbsLabel.setTextAlignment(TextAlignment.CENTER);
        carbsLabel.getStyleClass().add("nutritionLabelText");

        Text currentCarbsText = new Text();
        currentCarbsText.textProperty().bind(Bindings.convert(currentInfo.getObservableStatistic().getCarbsProperty()));
        Text carbRequirementText = new Text();
        carbRequirementText.textProperty().bind(
                Bindings.concat(
                        " / ",
                        Bindings.convert(currentInfo.getObservableConfig().getCarbProperty()),
                        " gram"
                )
        );
        TextFlow carbValues = new TextFlow(currentCarbsText, carbRequirementText);
        carbValues.setTextAlignment(TextAlignment.CENTER);
        carbValues.getStyleClass().add("nutritionValuesText");

        PercentProgressIndicator carbsIndicator = new PercentProgressIndicator(
                currentInfo.getObservableStatistic().getCarbsProperty(),
                currentInfo.getObservableConfig().getCarbProperty()
        );

        VBox carbBox = getSubStyledVbox();
        carbBox.setMinHeight(macroBoxHeight);
        carbBox.getChildren().addAll(carbsLabel, carbValues, carbsIndicator);

        // FAT PART
        TextFlow fatLabel = new TextFlow(new Text("Fat:"));
        fatLabel.setMinWidth(macroRegionWidth);
        fatLabel.setMinHeight(macroBoxHeight / 3);
        fatLabel.setTextAlignment(TextAlignment.CENTER);
        fatLabel.getStyleClass().add("nutritionLabelText");

        Text currentFatText = new Text();
        currentFatText.textProperty().bind(Bindings.convert(currentInfo.getObservableStatistic().getFatProperty()));
        Text fatRequirementText = new Text();
        fatRequirementText.textProperty().bind(
                Bindings.concat(
                        " / ",
                        Bindings.convert(currentInfo.getObservableConfig().getFatProperty()),
                        " gram"
                )
        );
        TextFlow fatValues = new TextFlow(currentFatText, fatRequirementText);
        fatValues.setTextAlignment(TextAlignment.CENTER);
        fatValues.getStyleClass().add("nutritionValuesText");

        PercentProgressIndicator fatIndicator = new PercentProgressIndicator(
                currentInfo.getObservableStatistic().getFatProperty(),
                currentInfo.getObservableConfig().getFatProperty()
        );

        VBox fatBox = getSubStyledVbox();
        fatBox.setMinHeight(macroBoxHeight);
        fatBox.getChildren().addAll(fatLabel, fatValues, fatIndicator);

        // Adding everything together
        macroContainer.getChildren().addAll(proteinBox, carbBox, fatBox);
        macroContainer.setMinWidth(macroBoxWidth);
        macroContainer.setMinHeight(macroBoxHeight);
        return macroContainer;
    }

    @Override
    public HBox getMicroInfo() {
        // TODO: Bindings and stuff
        HBox microContainer = getBasicStyledHBox();
        int containerWidth = macroBoxWidth + calorieBoxWidth + parentHGap + 15; // TODO: FIX
        int containerHeight = 3 * calorieBoxHeight / 2;

        TextFlow microLabel = new TextFlow(new Text("Microelements:"));
        microLabel.setMinWidth(containerWidth);
        microLabel.setMinHeight(containerHeight / 3);
        microLabel.setTextAlignment(TextAlignment.CENTER);
        microLabel.getStyleClass().add("nutritionLabelText");

        microContainer.getChildren().addAll(microLabel);
        microContainer.setMinWidth(containerWidth);
        microContainer.setMinHeight(containerHeight);
        return microContainer;
    }

    private VBox getBasicStyledVBox() {
        VBox styledBox = new VBox();
        styledBox.getStyleClass().add("softRectVBox");
        styledBox.setAlignment(Pos.TOP_CENTER);
        styledBox.setPadding(new Insets(BOXES_PADDING));
        styledBox.setSpacing(10);
        return styledBox;
    }

    private HBox getBasicStyledHBox() {
        HBox styledBox = new HBox();
        styledBox.getStyleClass().add("softRectVBox");
        styledBox.setAlignment(Pos.TOP_CENTER);
        styledBox.setPadding(new Insets(BOXES_PADDING));
        styledBox.setSpacing(15);
        return styledBox;
    }

    private VBox getSubStyledVbox() {
        VBox subStyledBox = new VBox();
        subStyledBox.setAlignment(Pos.TOP_CENTER);
        subStyledBox.setSpacing(10);
        return subStyledBox;
    }
}
