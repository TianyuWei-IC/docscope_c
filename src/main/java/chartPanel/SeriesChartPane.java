package chartPanel;

import master.Patient;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import javax.swing.*;
import java.awt.*;
import java.sql.Timestamp;
import java.util.List;

public class SeriesChartPane extends Recording_Panel  {
    Patient patient;
    public SeriesChartPane(inputData dataInput,long time,String type, Patient patient, String title){
        super(dataInput,time,type,title);
        this.patient = patient;
        this.worker = new UpdateWorker(this);
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

    public Patient find_patient(){
        return this.patient;
    }
}