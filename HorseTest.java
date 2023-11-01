import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class HorseTest {

    private final double initValue = 3;

    @ParameterizedTest
    @NullSource
    public void test_name_null(String name) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(name, initValue, initValue));
    }

    @ParameterizedTest
    @NullSource
    public void test_name_null_message(String name) {
        String expected = "Name cannot be null.";
        try {
            new Horse(name, initValue, initValue);
        } catch (Exception e) {
            Assertions.assertEquals(expected, e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", " ", "    ", "  ", "\t", "\n"})
    public void test_name_blank(String name) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(name, initValue, initValue));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", " ", "    ", "  ", "\t", "\n"})
    public void test_name_blank_message(String string) {
        String expected = "Name cannot be blank.";
        try {
            new Horse(string, initValue, initValue);
        } catch (Exception e) {
            Assertions.assertEquals(expected, e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.1d, -0.01d, -0.001d})
    public void test_speed_lessZero(Double speed) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("Amigo", speed, initValue));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.1d, -0.01d, -0.001d})
    public void test_speed_lesZero_message(Double speed) {
        String expected = "Speed cannot be negative.";
        try {
            new Horse("Amigo", speed, initValue);
        } catch (Exception e) {
            Assertions.assertEquals(expected, e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.1d, -0.01d, -0.001d})
    public void test_distance_lessZero(Double distance) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("Amigo", initValue, distance));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-0.1d, -0.01d, -0.001d})
    public void test_distance_lesZero_message(Double distance) {
        String expected = "Distance cannot be negative.";
        try {
            new Horse("Amigo", initValue, distance);
        } catch (Exception e) {
            Assertions.assertEquals(expected, e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"Amigo", "Diego", "Santiago"})
    public void test_getName(String name) {
        Horse horse = new Horse(name, initValue);
        Assertions.assertEquals(name, horse.getName());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1d, 1.0d, 10d,})
    public void test_getSpeed(Double speed) {
        Horse horse = new Horse("Amigo", speed);
        Assertions.assertEquals(speed, horse.getSpeed());
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1d, 1.0d, 10d,})
    public void test_getDistance(Double distance) {
        Horse amigo = new Horse("Amigo", initValue, distance);
        Assertions.assertEquals(distance, amigo.getDistance());
    }

    @Test
    public void test_getDistance_notSet() {
        Horse amigo = new Horse("Amigo", initValue);
        Assertions.assertEquals(0, amigo.getDistance());
    }

    @Test
    public void test_move_randomDouble() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            new Horse("Amigo", initValue, initValue).move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @Test
    public void test_move() {
        double returnValue = 0.7;
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(returnValue);
            Horse amigo = new Horse("Amigo", initValue, initValue);
            amigo.move();
            Assertions.assertEquals(initValue + returnValue * initValue, amigo.getDistance());
        }
    }
}
