package chartPanel;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Recording_Panel extends JPanel {
    public inputData dataInput;
    public XYChart chart;

    public long time;
    public UpdateWorker worker;
    public Recording_Panel(inputData dataInput,long time,String data_type, String title) {
        this.dataInput = dataInput;
        this.time=time;
        if(data_type=="ecg1"|data_type=="ecg2") {
            chart = new XYChartBuilder().width(1350).height(195).title(title).build();
        }else if(data_type=="resp pattern"){
            chart = new XYChartBuilder().width(670).height(140).title(title).build();
        }
        chart.addSeries("ecg_former", dataInput.partData[0], dataInput.partData[1]);
        chart.addSeries("ecg_latter", dataInput.partData[0], dataInput.partData[1]);

        chart.getStyler().setChartBackgroundColor(new Color(0xFFFFFF));
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setMarkerSize(0);
        chart.getStyler().setXAxisTicksVisible(true);
        chart.getStyler().setXAxisMin(0.2);
        chart.getStyler().setSeriesColors(new Color[]{new Color(0x395F40),new Color(0x395F40)});

        if(data_type=="ecg1"){
            chart.getStyler().setYAxisMax(1.5);
            chart.getStyler().setXAxisMin(0.2);
            chart.getStyler().setYAxisMin(-0.5);
        } else if (data_type=="ecg2") {
            chart.getStyler().setYAxisMax(1.0);
            chart.getStyler().setXAxisMin(0.2);
            chart.getStyler().setYAxisMin(-0.5);
        } else if (data_type=="resp pattern"){
            chart.getStyler().setYAxisMax(40.0);
            chart.getStyler().setXAxisMin(0.2);
            chart.getStyler().setYAxisMin(34.0);
        }


        XChartPanel<XYChart> chartPane = new XChartPanel<>(chart);
        add(chartPane);
    }




}
