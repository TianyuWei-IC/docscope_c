package chartPanel;

import user_interface.GUI_test;
import master.Patient;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Chart_Label_DisplayTest {

    // test the constructor
    @Test
    void constructor_test(){
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
        GUI_test mainGUI = new GUI_test();
        Patient current_patient = new Patient("Person","current","normal_2","male",2002,35.0,38.0,50,110,85,145,55,90,12,20, mainGUI);

        Chart_Label_Display chart_label = new Chart_Label_Display(current_patient, input, time, "ecg1", "title");
        // check if the chartPane has been added into the display_chart
        assertEquals(1, chart_label.display_chart.getComponentCount());


    }

    // test on updateData
    @Test
    void updateData_test() {
        double a = 0.0;
        long time = new Timestamp(System.currentTimeMillis()).getTime();
        double period = 0.01;
        // create initialValue for inputting into the class inputData
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        // create inputData for inputting into Chart_Label_Display
        inputData input = new inputData(initialValue, time, period);
        List<Double> newData =  Arrays.asList(10.0,11.0,12.0,13.0,14.0);
        List[] data = input.getData(newData,2);
        // create a patient
        GUI_test mainGUI = new GUI_test();
        Patient current_patient = new Patient("Person","current","abnormal_patient","male",2002,35.0,38.0,50,110,85,145,55,90,12,20, mainGUI);

        Chart_Label_Display chart_label = new Chart_Label_Display(current_patient, input, time, "ecg1", "title");
        String previous_value = chart_label.chart.getSeriesMap().values().stream().toString();
        try {
            chart_label.updateData(data);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        String updated_values = chart_label.chart.getSeriesMap().values().stream().toString();
        // compare the previous chart and the updated chart

        assertFalse(previous_value==updated_values);
        // the text showed on the label was set to have two decimal places
        assertEquals("14.00",chart_label.value_label.getText());
    }

    // test on urgent_or_warning

    // value instant > high threshold and pop_or_not = true
    @Test
    void higherValueInstant_pop() {
        // instantiate a Chart_Label_Display
        double a = 0.0;
        long time = new Timestamp(System.currentTimeMillis()).getTime();
        double period = 0.01;
        // create initialValue for inputting into the class inputData
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        // create inputData for inputting into Chart_Label_Display
        inputData input = new inputData(initialValue, time, period);
        // create a patient
        GUI_test mainGUI = new GUI_test();
        Patient current_patient = new Patient("Person","current","abnormal_patient","male",2002,35.0,38.0,50,110,85,145,55,90,12,20, mainGUI);
        Chart_Label_Display chart_label = new Chart_Label_Display(current_patient, input, time, "body temperature", "title");
        chart_label.pop_or_not = true;
        chart_label.urgent_or_warning(40.0);// check if there is a popup Urgent frame
        // test the label color and the pop_or_not
        assertEquals(new Color(255,0,0),chart_label.value_label.getForeground());
        assertFalse(chart_label.pop_or_not);
    }

    // value instant > high threshold and pop_or_not = false
    @Test
    void higherValueInstant_notpop() {
        // instantiate a Chart_Label_Display
        double a = 0.0;
        long time = new Timestamp(System.currentTimeMillis()).getTime();
        double period = 0.01;
        // create initialValue for inputting into the class inputData
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        // create inputData for inputting into Chart_Label_Display
        inputData input = new inputData(initialValue, time, period);
        // create a patient
        GUI_test mainGUI = new GUI_test();
        Patient current_patient = new Patient("Person","current","abnormal_patient","male",2002,35.0,38.0,50,110,85,145,55,90,12,20, mainGUI);
        Chart_Label_Display chart_label = new Chart_Label_Display(current_patient, input, time, "body temperature", "title");
        chart_label.pop_or_not = false;
        chart_label.urgent_or_warning(40.0);// check no window is popped up
        assertEquals(new Color(255,0,0),chart_label.value_label.getForeground());
        assertFalse(chart_label.pop_or_not);
    }

    // value instant < low threshold and pop_or_not = true
    @Test
    void lowerValueInstant_pop() {
        // instantiate a Chart_Label_Display
        double a = 0.0;
        long time = new Timestamp(System.currentTimeMillis()).getTime();
        double period = 0.01;
        // create initialValue for inputting into the class inputData
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        // create inputData for inputting into Chart_Label_Display
        inputData input = new inputData(initialValue, time, period);
        // create a patient
        GUI_test mainGUI = new GUI_test();
        Patient current_patient = new Patient("Person","current","abnormal_patient","male",2002,35.0,38.0,50,110,85,145,55,90,12,20, mainGUI);
        Chart_Label_Display chart_label = new Chart_Label_Display(current_patient, input, time, "body temperature", "title");
        chart_label.pop_or_not = true;
        chart_label.urgent_or_warning(34.0);// check if there is a popup Urgent frame
        // test the label color and the pop_or_not
        assertEquals(new Color(255,0,0),chart_label.value_label.getForeground());
        assertFalse(chart_label.pop_or_not);
    }

    // value instant < low threshold and pop_or_not = false
    @Test
    void lowerValueInstant_notpop() {
        // instantiate a Chart_Label_Display
        double a = 0.0;
        long time = new Timestamp(System.currentTimeMillis()).getTime();
        double period = 0.01;
        // create initialValue for inputting into the class inputData
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        // create inputData for inputting into Chart_Label_Display
        inputData input = new inputData(initialValue, time, period);
        // create a patient
        GUI_test mainGUI = new GUI_test();
        Patient current_patient = new Patient("Person","current","abnormal_patient","male",2002,35.0,38.0,50,110,85,145,55,90,12,20, mainGUI);
        Chart_Label_Display chart_label = new Chart_Label_Display(current_patient, input, time, "body temperature", "title");
        chart_label.pop_or_not = false;
        chart_label.urgent_or_warning(34.0);// check no window is popped up
        assertEquals(new Color(255,0,0),chart_label.value_label.getForeground());
        assertFalse(chart_label.pop_or_not);
    }

    // value instant >= high threshold and > warning threshold
    @Test
    void warning_closetoHigh() {
        // instantiate a Chart_Label_Display
        double a = 0.0;
        long time = new Timestamp(System.currentTimeMillis()).getTime();
        double period = 0.01;
        // create initialValue for inputting into the class inputData
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        // create inputData for inputting into Chart_Label_Display
        inputData input = new inputData(initialValue, time, period);
        // create a patient
        GUI_test mainGUI = new GUI_test();
        Patient current_patient = new Patient("Person","current","abnormal_patient","male",2002,35.0,38.0,50,110,85,145,55,90,12,20, mainGUI);
        Chart_Label_Display chart_label = new Chart_Label_Display(current_patient, input, time, "body temperature", "title");
        // 37.5 is within the range of warning
        chart_label.urgent_or_warning(37.5);
        // test the color of the text on the label
        assertEquals(new Color(228, 217, 34),chart_label.value_label.getForeground());
    }

    // value instant >= high threshold and < warning threshold
    @Test
    void warning_closetoLow() {
        // instantiate a Chart_Label_Display
        double a = 0.0;
        long time = new Timestamp(System.currentTimeMillis()).getTime();
        double period = 0.01;
        // create initialValue for inputting into the class inputData
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        // create inputData for inputting into Chart_Label_Display
        inputData input = new inputData(initialValue, time, period);
        // create a patient
        GUI_test mainGUI = new GUI_test();
        Patient current_patient = new Patient("Person","current","abnormal_patient","male",2002,35.0,38.0,50,110,85,145,55,90,12,20, mainGUI);
        Chart_Label_Display chart_label = new Chart_Label_Display(current_patient, input, time, "body temperature", "title");
        // 35.5 is within the range of warning
        chart_label.urgent_or_warning(35.5);
        // test the color of the text on the label
        assertEquals(new Color(228, 217, 34),chart_label.value_label.getForeground());
    }

    // value instant in within the normal range
    @Test
    void notWarning_or_Urgent_whenNormal() {
        // instantiate a Chart_Label_Display
        double a = 0.0;
        long time = new Timestamp(System.currentTimeMillis()).getTime();
        double period = 0.01;
        // create initialValue for inputting into the class inputData
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        // create inputData for inputting into Chart_Label_Display
        inputData input = new inputData(initialValue, time, period);
        // create a patient
        GUI_test mainGUI = new GUI_test();
        Patient current_patient = new Patient("Person","current","abnormal_patient","male",2002,35.0,38.0,50,110,85,145,55,90,12,20, mainGUI);
        Chart_Label_Display chart_label = new Chart_Label_Display(current_patient, input, time, "body temperature", "title");
        // 35.5 is within the range of warning
        chart_label.urgent_or_warning(36);
        // test the color of the text on the label
        assertEquals(new Color(57, 95, 64),chart_label.value_label.getForeground());
        assertTrue(chart_label.pop_or_not);
    }
}
