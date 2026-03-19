import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoundaryValueTest {

    static Stream<Arguments> boundaryTestCases() {
        return Stream.of(
                // HR: 40 (Min), 41 (Min+), 110 (Nom), 179 (Max-), 180 (Max)
                // SpO2: 70 (Min), 70.1 (Min+), 85 (Nom), 99.9 (Max-), 100 (Max)

                Arguments.of(110.0, 85.0, 16.5), // (nomx, nomy)
                Arguments.of(110.0, 70.0, 33.0), // (nomx, miny)
                Arguments.of(110.0, 70.1, 32.89), // (nomx, miny+)
                Arguments.of(110.0, 99.9, 2.0), // (nomx, maxy-)
                Arguments.of(110.0, 100.0, 2.0), // (nomx, maxy)
                Arguments.of(40.0, 85.0, 6.0), // (minx, nomy)
                Arguments.of(41.0, 85.0, 6.15), // (minx+, nomy)
                Arguments.of(179.0, 85.0, 26.85), // (maxx-, nomy)
                Arguments.of(180.0, 85.0, 27.0) // (maxx, nomy)
        );
    }

    @ParameterizedTest
    @MethodSource("boundaryTestCases")
    public void testOxygenFlow_BoundaryValues(double hr, double spo2, double expectedFlow) {
        double actualFlow = OxygenController.calculateOxygenFlow(hr, spo2);
        assertEquals(expectedFlow, actualFlow, 0.01); // Sai số 0.01
    }
}