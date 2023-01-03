package chartPanel;

import netRelated.netAction;
import netRelated.requestPack;
import netRelated.responsePack;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.List;

public class Chart_Label_Update extends SwingWorker<Void, List<Double>[]> {

    private Chart_Label_Display chartLabel;
    long previousTime;

    public Chart_Label_Update(Chart_Label_Display chartLabel) {
        this.chartLabel = chartLabel;
        this.previousTime=this.chartLabel.time.getTime();
    }

    @Override
    protected Void doInBackground() throws Exception {
//        while (true) {
//            Thread.sleep(1100);
//            long timestamp=new Timestamp(System.currentTimeMillis()).getTime();
//            requestPack reqPack =new requestPack(previousTime,timestamp);
//            responsePack respPack= netAction.postRequestData(reqPack,"http://localhost:8080/docScope_s/recorder");
//            List<Double> newData= respPack.valueList;
//            if (newData.size()!=0){
//                System.out.println(newData.size());
//                chartLabel.updateData(chartLabel.getDataInput().getData(newData));
//                if(respPack.lastTime==0) {
//                    previousTime=timestamp;
//                }
//                else previousTime=respPack.lastTime;
//            }
//        }
        return null;
    }
}

