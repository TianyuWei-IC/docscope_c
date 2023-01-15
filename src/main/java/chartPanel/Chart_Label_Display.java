package chartPanel;
import Interface.Urgent;
import master.Patient;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;

/**
 * This function creates a continuous XYChart plot and number display for resp rate, heart rate, blood pressure and body temperature
 * (HIGHLY ADVISED to use load_chartLabel() in master.Patient to create this object).
 * */
public class Chart_Label_Display {
    public Patient patient;
    public inputData dataInput;
    private XYChart chart;

    public long time;
    public Display_Chart display_chart;
    public JLabel value_label;
    private DecimalFormat df;
    private Color color;
    private Double threshold_double_high;
    private Double threshold_double_low;
    private Double baseline_value;

    public String type;
    private Boolean pop_or_not;
    private Timestamp time_urgent = new Timestamp(System.currentTimeMillis());
    private long time_milli;
    public Chart_Label_Update updater;
    public int WhiteSpace;

    /**
     * This function creates a continuous XYChart plot and number display for resp rate, heart rate, blood pressure and body temperature
     * (HIGHLY ADVISED to use load_chartLabel() in master.Patient to create this object).
     * @param patient requires Patient object
     * @param dataInput data received from server
     * @param time display time length setting
     * @param type "respiratory rate","heart rate","diastolic blood pressure","systolic blood pressure","body temperature"
     * @param title the title for the XYChart
     */
    public Chart_Label_Display(Patient patient, inputData dataInput, long time, String type, String title) {
        // patient where means the which patient does this Chart_Label_Display belongs to
        this.patient = patient;
        // dataInput is the data request from server
        this.dataInput = dataInput;
        // time indicates the instant at which Chart_Label_Display is created, this helps pulling the data from server
        this.time=time;
        // which type of signal is displaying
        this.type = type;
        // default whitespace value, this indicates the white gap on the plot;
        this.WhiteSpace = 10;

        /* get threshold obtains the critical value for the vital signs, this method set the threshold_high and
         threshold_low in the field, that is frequently used when finding whether the patient is in urgent/warning
         condition.
         */
        getThreshold();

        // create a XYChart that display the continuous plot signal
        chart = new XYChartBuilder().width(670).height(140).title(title).build();
        setChartStyle(type);
        XChartPanel<XYChart> chartPane = new XChartPanel<>(chart);
        display_chart = new Display_Chart(this);
        display_chart.add(chartPane);

        // create a JLabel that display the reading of the signal, this updates at the same time with the continuous plot
        this.color = new Color(0x1D7926);
        value_label = new JLabel();
        value_label.setForeground(this.color);
        this.dataInput = dataInput;
        this.df=new DecimalFormat("0.00");

        /* time_milli and pop_or_not are combined to serves as a time reference of whether the urgent windows
         should be pop out. The end goal is to achieve if a certain signal reaches its urgent threshold value, at that
         moment, the window will be shown but only once. The next time the urgent window pop up would be, when the signal
         returns to its normal state and hits the threshold value again.
         */
        this.time_milli = time_urgent.getTime();
        this.pop_or_not = true;

        // updater allows the continuous data update from server
        this.updater = new Chart_Label_Update(this);
        updater.execute();
    }

    /**
     * updateDate continuously update the graphs by obtaining the new data from server.
     * @param data list of values obtained from the server
     * @throws InterruptedException
     */
    public void updateData(List[] data) throws InterruptedException {


        // XYSeries are set to have a former and latter part of the data, this helps to create the moving 'white space'
        chart.updateXYSeries("former", data[0], data[1], null);
        chart.updateXYSeries("latter", data[2], data[3], null);

        // value_instant is the most recent data from the server and is set to the JLabel we created
        double value_instant = (double) data[3].get(data[3].size()-1);
        this.value_label.setText(df.format(value_instant));
        this.value_label.setFont(new Font("Calibri", Font.BOLD, 30));
        this.value_label.setHorizontalTextPosition((int) Component.CENTER_ALIGNMENT);

        // every time the new data comes in, we check if it needs to be warned or generate a urgent pop up window.
        urgent_or_warning(value_instant);

        // repaint the chart to show th updated data
        display_chart.repaint();
        value_label.repaint();
    }

    /**
     * this void function helps to set the style, size, xy-axis setting for the continuous plot graph.
     * @param data_type type of the data, e.g. heart rate
     */
    public void setChartStyle(String data_type) {

        chart.addSeries("former", dataInput.partData[0], dataInput.partData[1]);
        chart.addSeries("latter", dataInput.partData[0], dataInput.partData[1]);
        chart.getStyler().setChartBackgroundColor(new Color(0xFFFFFF));
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setMarkerSize(0);
        chart.getStyler().setXAxisTicksVisible(true);
        chart.getStyler().setSeriesColors(new Color[]{new Color(0x395F40),new Color(0x395F40)});


        // the xy-axis is set based on the type of the data
        if (data_type=="body temperature"){
            chart.getStyler().setYAxisMax(42.0);
            chart.getStyler().setXAxisMin(0.0);
            chart.getStyler().setYAxisMin(35.0);
        }else if(data_type=="heart rate"){
            chart.getStyler().setYAxisMax(200.0);
            chart.getStyler().setXAxisMin(0.0);
            chart.getStyler().setYAxisMin(0.0);
        }else if(data_type=="systolic blood pressure"){
            chart.getStyler().setYAxisMax(150.0);
            chart.getStyler().setXAxisMin(0.0);
            chart.getStyler().setYAxisMin(70.0);
        }else if(data_type=="diastolic blood pressure"){
            chart.getStyler().setYAxisMax(100.0);
            chart.getStyler().setXAxisMin(0.0);
            chart.getStyler().setYAxisMin(40.0);
        }else if(data_type=="respiratory rate"){
            chart.getStyler().setYAxisMax(30.0);
            chart.getStyler().setXAxisMin(0.0);
            chart.getStyler().setYAxisMin(0.0);
        }

    }

    /**
     * based on the type of the signal and the target patient, we can obtain its urgent threshold value.
     */
    public void getThreshold(){

        if (type =="body temperature"){
            this.threshold_double_low= patient.temp_min ;
            this.threshold_double_high= patient.temp_max;
            this.baseline_value = (this.threshold_double_low+this.threshold_double_high)/2;

        }else if(type =="heart rate"){
            this.threshold_double_low=  (double) patient.hr_min;
            this.threshold_double_high= (double) patient.hr_max;
            this.baseline_value = (this.threshold_double_low+this.threshold_double_high)/2;

        }else if(type =="systolic blood pressure"){
            this.threshold_double_low= (double) patient.sys_min;
            this.threshold_double_high= (double) patient.sys_max;
            this.baseline_value = (this.threshold_double_low+this.threshold_double_high)/2;

        }else if(type =="diastolic blood pressure"){
            this.threshold_double_low= (double) patient.dia_min;
            this.threshold_double_high= (double) patient.dia_max;
            this.baseline_value = (this.threshold_double_low+this.threshold_double_high)/2;

        }else if(type =="respiratory rate"){
            this.threshold_double_low= (double) patient.resp_min;
            this.threshold_double_high= (double) patient.resp_max;
            this.baseline_value = (this.threshold_double_low+this.threshold_double_high)/2;

        }
    }

    /**
     * this is the algorithm for giving the pop-up urgent window.
     * base on the type of the signal, find whether the most recent data got from the server hits the specified
     * @param value_instant most recent data from server
     */
    public void urgent_or_warning(double value_instant){
        if(type == "heart rate" | type == "systolic blood pressure" |
                type == "diastolic blood pressure" | type == "respiratory rate" | type == "body temperature") {

            // if above max threshold, set the display number to red
            if (value_instant > this.threshold_double_high) {
                this.value_label.setForeground(new Color(255,0,0));
                // pop_or_not is help to prevent the constant pop up during an urgent situation, this makes the window
                // only shown once when an urgent situation is found
                if (this.pop_or_not) {
                    // creates a urgent window and updates which patient is in critical condition and the corresponding
                    // state of his/her vital signs
                    Urgent urgent_window = new Urgent();
                    urgent_window.setVisible(true);
                    urgent_window.patient_name.setText(patient.first_name + " " + patient.last_name);
                    urgent_window.abnormal_signal.setText(type.toUpperCase());
                    urgent_window.high_low.setText("HIGH");
                    this.pop_or_not = false;
                }
            // if below min threshold, set the display number to red
            }else if(value_instant < this.threshold_double_low){
                this.value_label.setForeground(new Color(255,0,0));
                if (this.pop_or_not){
                    Urgent urgent_window = new Urgent();
                    urgent_window.setVisible(true);
                    urgent_window.patient_name.setText(patient.first_name+" "+patient.last_name);
                    urgent_window.abnormal_signal.setText(type.toUpperCase());
                    urgent_window.high_low.setText("LOW");
                    this.pop_or_not = false;
                }
            // warning if close to max/min threshold, set the display number to yellow
            } else if(value_instant < this.threshold_double_high & value_instant > ((0.6*this.threshold_double_high)+(0.4*this.baseline_value))) {
                this.value_label.setForeground(new Color(228, 217, 34));
            } else if(value_instant > this.threshold_double_low & value_instant < ((0.6*this.threshold_double_low)+(0.4*this.baseline_value))) {
                this.value_label.setForeground(new Color(228, 217, 34));
            // no urgent or warning, healthy at the moment, set the display number to green
            } else {
                this.value_label.setForeground(new Color(57, 95, 64));
                this.pop_or_not = true;
            }
        }
    }
}



