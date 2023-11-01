import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.List;

public class MainTest {

    @Disabled
    @Test
    @Timeout(5)
    public void test_main_ended_before_timeout() {
        List<Horse> horses = new ArrayList<>();
        double initValue = 3;
        for (int i = 0; i < initValue; i++) {
            horses.add(new Horse(String.valueOf(i), initValue, initValue));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        for (int i = 0; i < initValue; i++) {
            try {
                hippodrome.move();
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
