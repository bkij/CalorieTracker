package agh.edu.layout.customComponents;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class PercentProgressIndicator extends StackPane {
    private final DoubleProperty requirementsSatisfied = new SimpleDoubleProperty();
    private final DoubleProperty totalRequirements = new SimpleDoubleProperty();
    private final ProgressBar progressBar = new ProgressBar();
    private final Text percentDone = new Text();

    public PercentProgressIndicator(Property<Number> requirementsToBind, Property<Number> totalToBind) {
        requirementsSatisfied.bind(requirementsToBind);
        totalRequirements.bind(totalToBind);

        progressBar.setMaxWidth(Double.MAX_VALUE);  // Let progress bar be filled
        progressBar.progressProperty().bind(Bindings.divide(requirementsSatisfied, totalRequirements));
        percentDone.textProperty().bind(
                Bindings.concat(Bindings.convert(Bindings.divide(requirementsSatisfied, totalRequirements)), "%")
        );
        this.getChildren().addAll(progressBar, percentDone);
    }
}
