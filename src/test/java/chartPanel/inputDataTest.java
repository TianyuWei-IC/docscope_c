package chartPanel;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class inputDataTest {
    @Test
    void getData_test() {
        double a = 1.0;
        long time = 0;
        double period = 0.01;
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        inputData input = new inputData(initialValue, time, period);
        List[] output = input.getData(initialValue.subList(0,5), 2);
        List<Double> expected_output = Arrays.asList(0.07,0.08,0.09);
        assertEquals(expected_output,output[0]);
    }
}