import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.util.ArrayList;
import java.util.List;

public class HippodromeTest {

    private final double initValue = 3;

    @ParameterizedTest
    @NullSource
    public void test_list_null(List<Horse> horses) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
    }

    @ParameterizedTest
    @NullSource
    public void test_list_null_message(List<Horse> horses) {
        String expected = "Horses cannot be null.";
        try {
            new Hippodrome(horses);
        } catch (Exception e) {
            Assertions.assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void test_list_empty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    public void test_list_empty_message() {
        String expected = "Horses cannot be empty.";
        try {
            new Hippodrome(new ArrayList<>());
        } catch (Exception e) {
            Assertions.assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void test_getHorses() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < initValue; i++) {
            horses.add(new Horse(String.valueOf(i), initValue));
        }

        Assertions.assertEquals(horses, new Hippodrome(horses).getHorses());
        Assertions.assertEquals(horses.size(), new Hippodrome(horses).getHorses().size());
    }

    @Test
    public void test_horses_moved() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < initValue; i++) {
            horses.add(new Horse(String.valueOf(i), initValue, initValue));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (int i = 0; i < horses.size(); i++) {
            Assertions.assertTrue(hippodrome.getHorses().get(i).getDistance() > initValue);
        }
    }

    @Test
    public void test_getWinner() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < initValue; i++) {
            horses.add(new Horse(String.valueOf(i), initValue, initValue));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        Horse winner = new Horse("Amigo", initValue, initValue);
        for (Horse horse : hippodrome.getHorses()) {
            if (horse.getDistance() > winner.getDistance()) {
                winner = horse;
            }
        }

        Assertions.assertEquals(winner, hippodrome.getWinner());
    }
}