import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class TestC2 {

    static Stream<Arguments> oxygenTestCases() {
        return Stream.of(
                // [HR, SpO2, ExpectedFlow, ExpectException]

                Arguments.of(39.0, 80.0, 0.0, true),  // Path 1
                Arguments.of(120.0, 69.0, 0.0, true),  // Path 2
                Arguments.of(120.0, 97.0, 2.0, false),  // Path 3
                Arguments.of(120.0, 80.0, 24.0, false)  // Path 4
        );
    }

    @ParameterizedTest
    @MethodSource("oxygenTestCases")
    public void testOxygenFlow_DecisionTable(double hr, double spo2, double expectedFlow, boolean expectException) {
        if (expectException) {
            assertThrows(IllegalArgumentException.class, () ->
                    OxygenController.calculateOxygenFlow(hr, spo2)
            );
        } else {
            double actualFlow = OxygenController.calculateOxygenFlow(hr, spo2);
            assertEquals(expectedFlow, actualFlow, 0.01);
        }
    }
}