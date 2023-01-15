package chartPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class inputDataTest {
    //tests on the fields in the constructor
    @Test
    void constructor_sizeTest() {
        double a = 0.0;
        long time = 0;
        double period = 0.01;
        List<Double> initialValue = new ArrayList<>();
        // create a list of initialValues for inputting into the class inputData
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }

        inputData input = new inputData(initialValue, time, period);
        // expected size is the length of initialValue = 10
        assertEquals(10,input.size);
    }


    @Test
    void constructor_partData0Test() {
        double a = 0.0;
        long time = 0;
        double period = 0.01;
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            initialValue.add(a);
            a++;
        }
        // create a list of initialTime with the same size as the list of initialValue
        List<Double> initialTime = Arrays.asList(0.0,0.01,0.02);

        inputData input = new inputData(initialValue, time, period);
        List[] expected_output = new List[]{initialTime, initialValue};
        // test partData[0]
        assertEquals(expected_output[0],input.partData[0]);
    }

    @Test
    void constructor_partData1Test() {
        double a = 0.0;
        long time = 0;
        double period = 0.01;
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            initialValue.add(a);
            a++;
        }
        List<Double> initialTime = Arrays.asList(0.0,0.01,0.02);
        inputData input = new inputData(initialValue, time, period);

        List[] expected_output = new List[]{initialTime, initialValue};
        // test partData[0]
        assertEquals(expected_output[1],input.partData[1]);
    }

    // tests on the getData method
    // when the latest data+white space have reached the end of the chart
    @Test
    void Whitespace_notReachEndoftheChart_positionTest() {
        double a = 0.0;
        long time = 0;
        double period = 0.01;
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        // create a newData for inputting into getData method
        List<Double> newData =  Arrays.asList(10.0,11.0,12.0,13.0,14.0);
        inputData input = new inputData(initialValue, time, period);
        List[] output = input.getData(newData, 2);
        // test timeFormer
        // the expected position should be the same as the size of newData
        assertEquals(5,input.position);
    }
    @Test
    void Whitespace_notReachEndoftheChart_timeFormer() {
        double a = 0.0;
        long time = 0;
        double period = 0.01;
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        // create a newData for inputting into getData method
        List<Double> newData =  Arrays.asList(10.0,11.0,12.0,13.0,14.0);
        inputData input = new inputData(initialValue, time, period);
        List[] output = input.getData(newData, 2);
        //now the position should be 5
        //so that position + whitespace = 7 < 10

        List<Double> expected_output = Arrays.asList(0.07,0.08,0.09);
        // test timeFormer
        assertEquals(expected_output,output[0]);
    }

    @Test
    void Whitespace_notReachEndoftheChart_valueFormer() {
        double a = 0.0;
        long time = 0;
        double period = 0.01;
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        List<Double> newData =  Arrays.asList(10.0,11.0,12.0,13.0,14.0);
        inputData input = new inputData(initialValue, time, period);
        List[] output = input.getData(newData, 2);
        List<Double> expected_output = Arrays.asList(7.0,8.0,9.0);
        //test valueFormer
        assertEquals(expected_output,output[1]);
    }

    @Test
    void Whitespace_notReachEndoftheChart_timeLatter() {
        double a = 0.0;
        long time = 0;
        double period = 0.01;
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        List<Double> newData =  Arrays.asList(10.0,11.0,12.0,13.0,14.0);
        inputData input = new inputData(initialValue, time, period);
        List[] output = input.getData(newData, 2);
        // the expected output should be the first n elements (n = position, i.e size of newData)
        List<Double> expected_output = Arrays.asList(0.0,0.01,0.02,0.03,0.04);
        // test timeLatter
        assertEquals(expected_output,output[2]);
    }

    @Test
    void Whitespace_notReachEndoftheChart_valueLatter() {
        double a = 0.0;
        long time = 0;
        double period = 0.01;
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        List<Double> newData =  Arrays.asList(10.0,11.0,12.0,13.0,14.0);
        inputData input = new inputData(initialValue, time, period);
        List[] output = input.getData(newData, 2);
        // the expected output should be exactly the newData
        List<Double> expected_output = newData;
        assertEquals(expected_output,output[3]);
    }

    // when the latest data+white space just reached the end of the chart
    @Test
    void WhitespacejustReachesEndoftheChart_timeFormer() {
        double a = 0.0;
        long time = 0;
        double period = 0.01;
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        inputData input = new inputData(initialValue, time, period);
        input.position = 3;
        List<Double> newData =  Arrays.asList(10.0,11.0,12.0,13.0,14.0);
        List[] output = input.getData(newData, 2);
        // now position = 3+5 = 8
        // position+whitespace = 10, just equals the size

        // expected output should be the last value of the list of initialTime
        List<Double> expected_output = Arrays.asList(0.09);
        // test timeFormer
        assertEquals(expected_output,output[0]);
    }

    @Test
    void WhitespacejustReachesEndoftheChart_ValueFormer() {
        double a = 0.0;
        long time = 0;
        double period = 0.01;
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        inputData input = new inputData(initialValue, time, period);
        input.position = 3;
        List<Double> newData =  Arrays.asList(10.0,11.0,12.0,13.0,14.0);
        List[] output = input.getData(newData, 2);
        // expected output should be the last value of the list of initialValue
        List<Double> expected_output = Arrays.asList(9.0);
        // test valueFormer
        assertEquals(expected_output,output[1]);
    }

    @Test
    void WhitespacejustReachesEndoftheChart_timeLatter() {
        double a = 0.0;
        long time = 0;
        double period = 0.01;
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        inputData input = new inputData(initialValue, time, period);
        input.position = 3;
        List<Double> newData =  Arrays.asList(10.0,11.0,12.0,13.0,14.0);
        List[] output = input.getData(newData, 2);
        // the expected output should be the first n elements (n = position = 8)
        List<Double> expected_output = Arrays.asList(0.0,0.01,0.02,0.03,0.04,0.05,0.06,0.07);
        // test timeLatter
        assertEquals(expected_output,output[2]);
    }

    @Test
    void WhitespacejustReachesEndoftheChart_valueLatter() {
        double a = 0.0;
        long time = 0;
        double period = 0.01;
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        inputData input = new inputData(initialValue, time, period);
        input.position = 3;
        List<Double> newData =  Arrays.asList(10.0,11.0,12.0,13.0,14.0);
        List[] output = input.getData(newData, 2);
        // the expected output should be the first 3 elements of initialValue + newData
        List<Double> expected_output = Arrays.asList(0.0,1.0,2.0,10.0,11.0,12.0,13.0,14.0);
        // test valueLatter
        assertEquals(expected_output,output[3]);
    }

    // when Data just reaches the end of the chart
    @Test
    void Data_justReachEndoftheChart_positiontest() {
        double a = 0.0;
        long time = 0;
        double period = 0.01;
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        inputData input = new inputData(initialValue, time, period);
        input.position = 5;

        List<Double> newData =  Arrays.asList(10.0,11.0,12.0,13.0,14.0);
        // now position = 10, just equals the size
        List[] output = input.getData(newData, 2);
        assertEquals(0,input.position);
    }
}