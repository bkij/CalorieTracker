package agh.edu.parsers;

import agh.edu.model.FoodInfo;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class USDAParserTest {
    USDAParser parser = new USDAParser();

    @Test
    public void testParse() {
        List<FoodInfo> parsedInfo = parser.parse("testFoodData/");
        assertEquals(1, parsedInfo.size());

        FoodInfo butterWithSalt = parsedInfo.get(0);

        assertEquals(717.0, butterWithSalt.getKcal(), 0.1);     // Parsed from beggining
        assertEquals(0.0, butterWithSalt.getC(), 0.01);         // Parsed from middle
        assertEquals(7.0, butterWithSalt.getK(), 0.1);          // Parsed from end
    }
}