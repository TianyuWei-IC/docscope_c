package chartPanel;

import netRelated.*;


import javax.swing.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class UpdateWorker extends SwingWorker<Void, List<Double>[]> {

    private SeriesChartPane chart;
    long previousTime;


    public UpdateWorker(SeriesChartPane chart) {
        this.chart = chart;
        this.previousTime=this.chart.time.getTime();
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (true) {
            Thread.sleep(100);
            long timestamp=new Timestamp(System.currentTimeMillis()).getTime();
            requestPack reqPack =new requestPack(previousTime,timestamp);
            responsePack respPack=netAction.postRequestData(reqPack,"http://localhost:8080/docScope_s/ecg1");
            List<Double> newData= respPack.valueList;
            if (newData.size()!=0){
                System.out.println(newData.size());
                chart.updateData(chart.getDataInput().getData(newData));
                if(respPack.lastTime==0) {
                    previousTime=timestamp;
                }
                    else previousTime=respPack.lastTime;
            }
        }
    }
}