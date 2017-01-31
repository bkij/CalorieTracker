package agh.edu.layout;

import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.util.List;

public interface ButtonCreator {
    List<Button> getButtons();
    List<Text> getButtonsText();
}
