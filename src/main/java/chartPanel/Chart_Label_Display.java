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


public class Chart_Label_Display {
    private Patient patient;
    public inputData dataInput;
    private XYChart chart;
    public long time;
    public JPanel display_chart;
    public JLabel value_label;
    private DecimalFormat df;
    private Color color;
    private Double threshold_double_high;
    private Double threshold_double_low;
    private Double baseline_value;
    private String data_type;


    public Chart_Label_Display(Patient patient,inputData dataInput, long time, String data_type) {
        this.patient = patient;
        this.dataInput = dataInput;
        this.time=time;
        this.data_type = data_type;
        getThreshold();

        chart = new XYChartBuilder().width(1000).height(120).title("master.Monitor").build();
        setChartStyle(data_type);
        XChartPanel<XYChart> chartPane = new XChartPanel<>(chart);
        display_chart = new JPanel();
        display_chart.add(chartPane);


        this.color = new Color(0x1D7926);
        value_label = new JLabel();
        value_label.setForeground(this.color);
        this.dataInput = dataInput;
        this.df=new DecimalFormat("0.00000");

        Chart_Label_Update updater = new Chart_Label_Update(this);
        updater.execute();
    }


    public inputData getDataInput() {
        return dataInput;
    }

    public void updateData(List[] data) throws InterruptedException {
        chart.updateXYSeries("former", data[0], data[1], null);
        chart.updateXYSeries("latter", data[2], data[3], null);

        double value_instant = (double) data[3].get(data[3].size()-1);

        this.value_label.setText(df.format(value_instant));

//        urgent_or_not(value_instant);
//        warning_or_not(value_instant);

        display_chart.repaint();
        value_label.repaint();
    }

    public void setChartStyle(String data_type) {
        chart.addSeries("former", dataInput.partData[0], dataInput.partData[1]);
        chart.addSeries("latter", dataInput.partData[0], dataInput.partData[1]);
        chart.getStyler().setChartBackgroundColor(new Color(0xFFFFFF));
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setMarkerSize(0);
        chart.getStyler().setXAxisTicksVisible(false);
        chart.getStyler().setSeriesColors(new Color[]{new Color(0x395F40),new Color(0x395F40)});

        if (data_type=="body temperature"){
            chart.getStyler().setYAxisMax(42.0);
            chart.getStyler().setYAxisMin(35.0);
//            chart.getStyler().setXAxisMax(10.0);
        }else if(data_type=="heart rate"){
            chart.getStyler().setYAxisMax(200.0);
            chart.getStyler().setYAxisMin(0.0);
        }else if(data_type=="systolic blood pressure"){
            chart.getStyler().setYAxisMax(150.0);
            chart.getStyler().setYAxisMin(70.0);
        }else if(data_type=="diastolic blood pressure"){
            chart.getStyler().setYAxisMax(100.0);
            chart.getStyler().setYAxisMin(40.0);
        }else if(data_type=="respiratory rate"){
            chart.getStyler().setYAxisMax(30.0);
            chart.getStyler().setYAxisMin(0.0);
        }

    }

    public void getThreshold(){
        if (data_type=="body temperature"){
            this.threshold_double_low= patient.temp_min ;
            this.threshold_double_high= patient.temp_max;
            this.baseline_value = 36.5;
        }else if(data_type=="heart rate"){
            this.threshold_double_low=  (double) patient.hr_min;
            this.threshold_double_high= (double) patient.hr_max;
            this.baseline_value = 75.0;
        }else if(data_type=="systolic blood pressure"){
            this.threshold_double_low= (double) patient.sys_min;
            this.threshold_double_high= (double) patient.sys_min;
            this.baseline_value = 105.0;
        }else if(data_type=="diastolic blood pressure"){
            this.threshold_double_low= (double) patient.dia_min;
            this.threshold_double_high= (double) patient.dia_max;
            this.baseline_value = 70.0;
        }else if(data_type=="respiratory rate"){
            this.threshold_double_low= (double) patient.resp_min;
            this.threshold_double_high= (double) patient.resp_max;
            this.baseline_value = 15.0;
        }
    }

    public void urgent_or_not(double value_instant){
        if(data_type == "heart rate" | data_type == "systolic blood pressure" |
                data_type == "diastolic blood pressure" | data_type == "respiratory rate" | data_type == "body temperature") {

            if (value_instant > this.threshold_double_high) {
                Urgent urgent_window = new Urgent();
                urgent_window.setVisible(true);
                urgent_window.patient_name.setText(patient.first_name+" "+patient.last_name);
                urgent_window.abnormal_signal.setText(data_type.toUpperCase());
                urgent_window.high_low.setText("HIGH");
                this.value_label.setForeground(new Color(255,0,0));

            }else if(value_instant < this.threshold_double_low){
                Urgent urgent_window = new Urgent();
                urgent_window.setVisible(true);
                urgent_window.patient_name.setText(patient.first_name+" "+patient.last_name);
                urgent_window.abnormal_signal.setText(data_type.toUpperCase());
                urgent_window.high_low.setText("LOW");
                this.value_label.setForeground(new Color(255,0,0));
            }
        }
    }

    public void warning_or_not(double value_instant) {
        if (data_type == "heart rate" | data_type == "systolic blood pressure" |
                data_type == "diastolic blood pressure" | data_type == "respiratory rate" | data_type == "body temperature") {

            if (value_instant < this.threshold_double_high & value_instant > (0.6*this.threshold_double_high+0.4*this.baseline_value)) {
                this.value_label.setForeground(new Color(228, 217, 34));

            } else if (value_instant > this.threshold_double_low & value_instant < (0.6*this.threshold_double_low+0.4*this.baseline_value)) {
                this.value_label.setForeground(new Color(228, 217, 34));
            }
        }
    }
}



