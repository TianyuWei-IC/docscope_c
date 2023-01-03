package chartPanel;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.util.List;

public class SeriesChartPane extends JPanel  {

    public inputData dataInput;
    private XYChart chart;
    public long time;


    public SeriesChartPane(inputData dataInput,long time,String data_type) {
        this.dataInput = dataInput;
        this.time=time;
        chart = new XYChartBuilder().width(1000).height(120).title("master.Monitor").build();

        chart.addSeries("ecg_former", dataInput.partData[0], dataInput.partData[1]);
        chart.addSeries("ecg_latter", dataInput.partData[0], dataInput.partData[1]);

        chart.getStyler().setChartBackgroundColor(new Color(0xFFFFFF));
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setMarkerSize(0);
        chart.getStyler().setXAxisTicksVisible(false);
        chart.getStyler().setXAxisMin(0.0);
        chart.getStyler().setSeriesColors(new Color[]{new Color(0x395F40),new Color(0x395F40)});

        if(data_type=="ecg1"){
            chart.getStyler().setYAxisMax(1.5);
            chart.getStyler().setYAxisMin(-0.5);
//            chart.getStyler().setXAxisMax(15.0);
        }


        XChartPanel<XYChart> chartPane = new XChartPanel<>(chart);
        add(chartPane);

        UpdateWorker worker = new UpdateWorker(this);
        worker.execute();
    }


    public inputData getDataInput() {
        return dataInput;
    }


    public void updateData(List[] data) {
        chart.updateXYSeries("ecg_former", data[0], data[1], null);
        chart.updateXYSeries("ecg_latter", data[2], data[3], null);

        repaint();
    }

}