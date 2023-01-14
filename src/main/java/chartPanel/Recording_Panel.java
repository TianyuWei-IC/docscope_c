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
    public String type;
    public Recording_Panel(inputData dataInput,long time,String type, String title, String mode) {
        if (mode=="real time") {
            this.type = type;
            this.dataInput = dataInput;
            this.time = time;
            if (type == "ecg1" | type == "ecg2") {
                chart = new XYChartBuilder().width(1350).height(195).title(title).build();
            } else if (type == "resp") {
                chart = new XYChartBuilder().width(670).height(140).title(title).build();
            }

        } else if(mode=="recording"){
            this.type = type;
            this.dataInput = dataInput;
            this.time = time;
            chart = new XYChartBuilder().width(1000).height(300).title(title).build();
        }

        chart.addSeries("former", dataInput.partData[0], dataInput.partData[1]);
        chart.addSeries("latter", dataInput.partData[0], dataInput.partData[1]);

        chart.getStyler().setChartBackgroundColor(new Color(0xFFFFFF));
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setMarkerSize(0);
        chart.getStyler().setXAxisTicksVisible(true);
        chart.getStyler().setXAxisMin(0.1);
        chart.getStyler().setSeriesColors(new Color[]{new Color(0x395F40), new Color(0x395F40)});

        if (type == "ecg1") {
            chart.getStyler().setYAxisMax(1.5);
            chart.getStyler().setXAxisMin(0.1);
            chart.getStyler().setYAxisMin(-0.5);
        } else if (type == "ecg2") {
            chart.getStyler().setYAxisMax(1.5);
            chart.getStyler().setXAxisMin(0.1);
            chart.getStyler().setYAxisMin(-0.5);
        } else if (type == "resp") {
            chart.getStyler().setYAxisMax(1.0);
            chart.getStyler().setXAxisMin(0.1);
            chart.getStyler().setYAxisMin(-1.0);
        } if (type=="body temperature"){
            chart.getStyler().setYAxisMax(42.0);
            chart.getStyler().setXAxisMin(0.1);
            chart.getStyler().setYAxisMin(35.0);
        }else if(type=="heart rate"){
            chart.getStyler().setYAxisMax(200.0);
            chart.getStyler().setXAxisMin(0.1);
            chart.getStyler().setYAxisMin(0.0);
        }else if(type=="systolic blood pressure"){
            chart.getStyler().setYAxisMax(150.0);
            chart.getStyler().setXAxisMin(0.1);
            chart.getStyler().setYAxisMin(70.0);
        }else if(type=="diastolic blood pressure"){
            chart.getStyler().setYAxisMax(100.0);
            chart.getStyler().setXAxisMin(0.1);
            chart.getStyler().setYAxisMin(40.0);
        }else if(type=="respiratory rate"){
            chart.getStyler().setYAxisMax(30.0);
            chart.getStyler().setXAxisMin(0.1);
            chart.getStyler().setYAxisMin(0.0);
        }


        XChartPanel<XYChart> chartPane = new XChartPanel<>(chart);
        add(chartPane);
    }




}
