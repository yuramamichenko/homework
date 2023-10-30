import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MainTest {

    @Test
    @Disabled
    public void test_main() {
        List<Horse> horses = List.of(
                new Horse("Bucephalus", 2.4),
                new Horse("Ace of Spades", 2.5),
                new Horse("Zephyr", 2.6),
                new Horse("Blaze", 2.7),
                new Horse("Lobster", 2.8),
                new Horse("Pegasus", 2.9),
                new Horse("Cherry", 3)
        );
        Hippodrome hippodrome = new Hippodrome(horses);

        for (int i = 0; i < 100; i++) {
            hippodrome.move();
        }
        String winnerName = hippodrome.getWinner().getName();
        Assertions.assertNotNull(winnerName);
    }
}
