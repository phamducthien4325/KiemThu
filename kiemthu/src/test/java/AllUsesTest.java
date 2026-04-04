import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class AllUsesTest {

    static Stream<Arguments> oxygenTestCases() {
        return Stream.of(
                // [HR, SpO2, ExpectedFlow, ExpectException]

                Arguments.of(120.0, 80.0, 24.0, false),
                Arguments.of(39.0, 80.0, 0.0, true),
                Arguments.of(140.0, 90.0, 14.0, false),
                Arguments.of(110.0, 75.0, 27.5, false),
                Arguments.of(120.0, 69.0, 0.0, true),
                Arguments.of(135.0, 85.0, 20.25, false),
                Arguments.of(120.0, 97.0, 2.0, false),
                Arguments.of(132.0, 83.0, 22.44, false),
                Arguments.of(170.0, 92.0, 13.6, false)
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