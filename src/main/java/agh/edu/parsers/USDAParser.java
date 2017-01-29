package agh.edu.parsers;

import agh.edu.model.FoodInfo;

import java.util.LinkedList;
import java.util.List;

public class USDAParser implements NutritionalDataParser {
    @Override
    public List<FoodInfo> parse(String filesPath) {
        String fileName = "ABBREV.txt";
        List<FoodInfo> foodData = new LinkedList<>();

    }
}
