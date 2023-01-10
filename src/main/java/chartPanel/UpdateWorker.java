package chartPanel;

import netRelated.*;


import javax.swing.*;
import java.sql.Timestamp;
import java.util.List;


public class UpdateWorker extends SwingWorker<Void, List<Double>[]> {

    private SeriesChartPane chart;
    long previousTime;
    String type;


    public UpdateWorker(SeriesChartPane chart,String type) {
        this.chart = chart;
        this.previousTime=this.chart.time;
        this.type=type;
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (true) {
            Thread.sleep(50);
            long currentTime=new Timestamp(System.currentTimeMillis()).getTime();

            responsePack respPack=netAction.recordFastData(previousTime,
                    currentTime,
                    this.chart.dataInput.dataBaseInitialTime,type);
            List<Double> newData= respPack.valueList;
            if (newData.size()!=0){
                chart.updateData(chart.getDataInput().getData(newData,100));
            }
            previousTime=respPack.lastTime;
        }
    }
}