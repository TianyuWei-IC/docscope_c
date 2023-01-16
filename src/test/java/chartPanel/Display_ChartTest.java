package chartPanel;

import user_interface.GUI_test;
import master.Patient;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Display_ChartTest {

    @Test
    void find_cl_displaytest() {
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

        Display_Chart display = new Display_Chart(chart_label);
        assertEquals(chart_label,display.find_cl_display());

    }
}
