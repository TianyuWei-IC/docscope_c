package chartPanel;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Recording_PanelTest {

    //realtime mode

    //type ecg1
    @Test
    void realtime_ecg1_charttest(){
        double a = 0.0;
        long time = 0;
        double period = 0.01;
        // create initialValue for inputting into the class inputData
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        inputData input = new inputData(initialValue, time, period);
        // tests on chart properties

        // if realtime
        Recording_Panel recording = new Recording_Panel(input,time,"ecg1","title","real time");
        assertEquals(1350,recording.chart.getWidth());
        assertEquals(195,recording.chart.getHeight());

        // for all types and modes
        assertEquals(2,recording.chart.getSeriesMap().size());
        assertEquals(new Color(0xFFFFFF),recording.chart.getStyler().getChartBackgroundColor());
        assertFalse(recording.chart.getStyler().isLegendVisible());
        assertEquals(0,recording.chart.getStyler().getMarkerSize());
        assertTrue(recording.chart.getStyler().isXAxisTicksVisible());
        Color[] seriesColor = recording.chart.getStyler().getSeriesColors();
        assertEquals(new Color(0x395F40),seriesColor[0]);
        assertEquals(new Color(0x395F40),seriesColor[1]);

        // if ecg1
        assertEquals(1.5,recording.chart.getStyler().getYAxisMax());
        assertEquals(-0.5,recording.chart.getStyler().getYAxisMin());
        // check if the chartPane is added into the recording_panel
        assertEquals(1,recording.getComponentCount());
    }

    // type ecg2
    @Test
    void realtime_ecg2_charttest(){
        double a = 0.0;
        long time = 0;
        double period = 0.01;
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        inputData input = new inputData(initialValue, time, period);

        // tests on chart properties
        // if real time
        Recording_Panel recording = new Recording_Panel(input,time,"ecg2","title","real time");
        assertEquals(1350,recording.chart.getWidth());
        assertEquals(195,recording.chart.getHeight());

        // if ecg2
        assertEquals(1.5,recording.chart.getStyler().getYAxisMax());
        assertEquals(-0.5,recording.chart.getStyler().getYAxisMin());
        // check if the chartPane is added into the recording_panel
        assertEquals(1,recording.getComponentCount());

    }

    // type respiratory pattern
    @Test
    void realtime_resp_charttest(){
        double a = 0.0;
        long time = 0;
        double period = 0.01;
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        inputData input = new inputData(initialValue, time, period);

        // tests on chart properties
        // if real time
        Recording_Panel recording = new Recording_Panel(input,time,"resp","title","real time");
        assertEquals(670,recording.chart.getWidth());
        assertEquals(140,recording.chart.getHeight());

        // if resp
        assertEquals(1.0,recording.chart.getStyler().getYAxisMax());
        assertEquals(-1.0,recording.chart.getStyler().getYAxisMin());
        // check if the chartPane is added into the recording_panel
        assertEquals(1,recording.getComponentCount());
    }

    // recording mode

    // type body temperature
    @Test
    void recording_bT_charttest(){
        double a = 0.0;
        long time = 0;
        double period = 0.01;
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        inputData input = new inputData(initialValue, time, period);

        // tests on chart properties
        Recording_Panel recording = new Recording_Panel(input,time,"body temperature","title","recording");
        assertEquals(1000,recording.chart.getWidth());
        assertEquals(300,recording.chart.getHeight());
        assertEquals(42.0,recording.chart.getStyler().getYAxisMax());
        assertEquals(35.0,recording.chart.getStyler().getYAxisMin());

        // check if the chartPane is added into the recording_panel
        assertEquals(1,recording.getComponentCount());
    }

    // type heart rate
    @Test
    void recording_hr_charttest(){
        double a = 0.0;
        long time = 0;
        double period = 0.01;
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        inputData input = new inputData(initialValue, time, period);

        // tests on chart properties
        Recording_Panel recording = new Recording_Panel(input,time,"heart rate","title","recording");
        assertEquals(1000,recording.chart.getWidth());
        assertEquals(300,recording.chart.getHeight());
        assertEquals(200.0,recording.chart.getStyler().getYAxisMax());
        assertEquals(0.0,recording.chart.getStyler().getYAxisMin());

        // check if the chartPane is added into the recording_panel
        assertEquals(1,recording.getComponentCount());
    }

    // type systolic blood pressure
    @Test
    void recording_sysbp_charttest(){
        double a = 0.0;
        long time = 0;
        double period = 0.01;
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        inputData input = new inputData(initialValue, time, period);

        // tests on chart properties
        Recording_Panel recording = new Recording_Panel(input,time,"systolic blood pressure","title","recording");
        assertEquals(1000,recording.chart.getWidth());
        assertEquals(300,recording.chart.getHeight());
        assertEquals(150.0,recording.chart.getStyler().getYAxisMax());
        assertEquals(70.0,recording.chart.getStyler().getYAxisMin());

        // check if the chartPane is added into the recording_panel
        assertEquals(1,recording.getComponentCount());
    }

    //type diastolic blood pressure
    @Test
    void recording_diabp_charttest(){
        double a = 0.0;
        long time = 0;
        double period = 0.01;
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        inputData input = new inputData(initialValue, time, period);

        // tests on chart properties
        Recording_Panel recording = new Recording_Panel(input,time,"diastolic blood pressure","title","recording");
        assertEquals(1000,recording.chart.getWidth());
        assertEquals(300,recording.chart.getHeight());
        assertEquals(100.0,recording.chart.getStyler().getYAxisMax());
        assertEquals(40.0,recording.chart.getStyler().getYAxisMin());

        // check if the chartPane is added into the recording_panel
        assertEquals(1,recording.getComponentCount());
    }

    //type respiratory rate
    @Test
    void recording_respRate_charttest(){
        double a = 0.0;
        long time = 0;
        double period = 0.01;
        List<Double> initialValue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            initialValue.add(a);
            a++;
        }
        inputData input = new inputData(initialValue, time, period);

        // tests on chart properties
        Recording_Panel recording = new Recording_Panel(input,time,"respiratory rate","title","recording");
        assertEquals(1000,recording.chart.getWidth());
        assertEquals(300,recording.chart.getHeight());
        assertEquals(30.0,recording.chart.getStyler().getYAxisMax());
        assertEquals(0.0,recording.chart.getStyler().getYAxisMin());

        // check if the chartPane is added into the recording_panel
        assertEquals(1,recording.getComponentCount());
    }
}