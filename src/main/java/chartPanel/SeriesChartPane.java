package chartPanel;

import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.util.List;

public class SeriesChartPane extends Recording_Panel  {
    public SeriesChartPane(inputData dataInput,long time,String data_type){
        super(dataInput,time,data_type);

        this.worker = new UpdateWorker(this);
        worker.execute();
    }
    public inputData getDataInput() {
        return dataInput;
    }


    public void updateData(List[] data) {
        chart.updateXYSeries("ecg_former", data[0], data[1], null);
        chart.updateXYSeries("ecg_latter", data[2], data[3], null);
        System.out.println("still running");
        repaint();
    }
}