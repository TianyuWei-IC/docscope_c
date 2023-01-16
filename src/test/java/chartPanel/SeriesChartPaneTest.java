package chartPanel;

import user_interface.GUI_test;
import master.Patient;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SeriesChartPaneTest {

    @Test
    void getDataInput_test() {
            double a = 0.0;
            long time = new Timestamp(System.currentTimeMillis()).getTime();
            double period = 0.01;
            // create initialValue for inputting into the class inputData
            List<Double> initialValue = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                initialValue.add(a);
                a++;
            }
            // create inputData for inputting into SerieschartPane
            inputData input = new inputData(initialValue, time, period);

            // create a patient
            GUI_test mainGUI = new GUI_test();
            Patient current_patient = new Patient("Person","current","abnormal_patient","male",2002,35.0,38.0,50,110,85,145,55,90,12,20, mainGUI);

            SeriesChartPane chartPane = new SeriesChartPane(input,time,"ecg1", current_patient,"title","real time");
            assertEquals(input,chartPane.getDataInput());
    }

    @Test
    void updateData() {
        double a = 0.0;
        long time = new Timestamp(System.currentTimeMillis()).getTime();
        double period = 0.01;
        // create initialValue for inputting into the class inputData
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        // create inputData for inputting into SerieschartPane
        inputData input = new inputData(initialValue, time, period);
        List<Double> newData =  Arrays.asList(10.0,11.0,12.0,13.0,14.0);
        List[] data = input.getData(newData,2);
        // create a patient
        GUI_test mainGUI = new GUI_test();
        Patient current_patient = new Patient("Person","current","abnormal_patient","male",2002,35.0,38.0,50,110,85,145,55,90,12,20, mainGUI);

        SeriesChartPane chartPane = new SeriesChartPane(input,time,"ecg1", current_patient,"title","real time");
        String previous_values = chartPane.chart.getSeriesMap().values().stream().toString();
        chartPane.updateData(data);
        String updated_values = chartPane.chart.getSeriesMap().values().stream().toString();
        // compare the previous chart with the updated one
        assertFalse(previous_values == updated_values);
    }

    @Test
    void find_patient() {
        double a = 0.0;
        long time = new Timestamp(System.currentTimeMillis()).getTime();
        double period = 0.01;
        // create initialValue for inputting into the class inputData
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        // create inputData for inputting into SerieschartPane
        inputData input = new inputData(initialValue, time, period);
        // create a patient
        GUI_test mainGUI = new GUI_test();
        Patient current_patient = new Patient("Person","current","abnormal_patient","male",2002,35.0,38.0,50,110,85,145,55,90,12,20, mainGUI);

        SeriesChartPane chartPane = new SeriesChartPane(input,time,"ecg1", current_patient,"title","real time");
        // test find_patient method
        assertEquals(current_patient, chartPane.find_patient());
    }
}