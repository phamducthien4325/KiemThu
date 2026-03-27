import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class DecisionTableTest {

    static Stream<Arguments> oxygenTestCases() {
        return Stream.of(
                // [HR, SpO2, ExpectedFlow, ExpectException]

                Arguments.of(39.0, 69.0, 0.0, true),
                Arguments.of(120.0, 69.0, 0.0, true),
                Arguments.of(181.0, 69.0, 0.0, true),
                Arguments.of(39.0, 80.0, 0.0, true),
                Arguments.of(120.0, 80.0, 24.0, false),
                Arguments.of(181.0, 80.0, 0.0, true),
                Arguments.of(39.0, 97.0, 0.0, true),
                Arguments.of(120.0, 97.0, 2.0, false),
                Arguments.of(181.0, 97.0, 0.0, true),
                Arguments.of(39.0, 101.0, 0.0, true),
                Arguments.of(120.0, 101.0, 0.0, true),
                Arguments.of(181.0, 101.0, 0.0, true)
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