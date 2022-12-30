package chartPanel;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;




public class Chart_Label_Display {
    private inputData dataInput;
    private XYChart chart;
    public Timestamp time;
    public JPanel display_chart;
    public JLabel value_label;
    private DecimalFormat df;

    public Chart_Label_Display(inputData dataInput, Timestamp time, String data_type) {
        this.dataInput = dataInput;
        this.time=time;
        chart = new XYChartBuilder().width(1000).height(120).title("master.Monitor").build();

        chart.addSeries("former", dataInput.partData[0], dataInput.partData[1]);
        chart.addSeries("latter", dataInput.partData[0], dataInput.partData[1]);
        chart.getStyler().setChartBackgroundColor(new Color(0xFFFFFF));
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setMarkerSize(0);
        chart.getStyler().setXAxisTicksVisible(false);
        chart.getStyler().setSeriesColors(new Color[]{new Color(0x395F40),new Color(0x395F40)});


        if (data_type=="temperature"){
            chart.getStyler().setYAxisMax(42.0);
            chart.getStyler().setYAxisMin(35.0);
        //chart.getStyler().setXAxisMax(4.0);
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


        XChartPanel<XYChart> chartPane = new XChartPanel<>(chart);
        display_chart = new JPanel();
        display_chart.add(chartPane);

        value_label = new JLabel();

        this.dataInput = dataInput;
        this.df=new DecimalFormat("0.00000");

        Chart_Label_Update updater = new Chart_Label_Update(this);
        updater.execute();
    }


    public inputData getDataInput() {
        return dataInput;
    }


    //public void updateData(List[] data) {
    public void updateData(List[] data) throws InterruptedException {
        chart.updateXYSeries("former", data[0], data[1], null);
        chart.updateXYSeries("latter", data[2], data[3], null);

        this.value_label.setText(df.format(data[3].get(data[3].size()-1)));

        display_chart.repaint();
        value_label.repaint();
    }

}
