import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import java.util.ArrayList;
import java.util.List;

public class HippodromeTest {

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
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("id: " + i, i));
        }
        Assertions.assertEquals(horses, new Hippodrome(horses).getHorses());
        horses.remove(0);
        Assertions.assertEquals(horses, new Hippodrome(horses).getHorses());
    }

    @Test
    public void test_move() {
        List<Horse> horses = new ArrayList<>();
        double distance = 1;
        for (int i = 0; i < 50; i++) {
            horses.add(new Horse(String.valueOf(i), 2, distance));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (int i = 0; i < horses.size(); i++) {
            Assertions.assertTrue(hippodrome.getHorses().get(i).getDistance() > distance);
        }
    }

    @Test
    public void test_getWinner() {
        List<Horse> horses = new ArrayList<>();
        double distance = 1;
        for (int i = 0; i < 10; i++) {
            horses.add(new Horse(String.valueOf(i), 2, distance));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        for (int i = 0; i < 10; i++) {
            hippodrome.move();
        }
        Horse winner = new Horse("Amigo", 2, distance);
        for (Horse horse : hippodrome.getHorses()) {
            if (horse.getDistance() > winner.getDistance()) {
                winner = horse;
            }
        }
        Assertions.assertEquals(winner, hippodrome.getWinner());
    }
}