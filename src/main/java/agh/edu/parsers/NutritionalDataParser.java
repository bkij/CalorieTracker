package agh.edu.parsers;

import agh.edu.model.FoodInfo;

import java.util.List;

public interface NutritionalDataParser {
    List<FoodInfo> parse(String filePath);
}
