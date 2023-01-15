package chartPanel;

import netRelated.netAction;
import netRelated.responsePack;

import javax.swing.*;
import java.sql.Timestamp;
import java.util.List;

public class Chart_Label_Update extends SwingWorker<Void, List<Double>[]> {

    private Chart_Label_Display chartLabel;
    long previousTime;
    String type;

    /**
     * This class communicate with the server and feed the data constantly into the Chart_Label_Display
     * @param chartLabel the Chart_Label_Display that requires updates
     */
    public Chart_Label_Update(Chart_Label_Display chartLabel) {
        this.chartLabel = chartLabel;
        this.previousTime=this.chartLabel.time;
        this.type=this.chartLabel.type;
    }


    /**
     * communicating with the server once 1000 milliseconds to obtain the data and pass into the Chart_Label_Display
     * for data updates
     */
    @Override
    protected Void doInBackground() throws Exception {
        while (true) {
            Thread.sleep(1000);
            long currentTime=new Timestamp(System.currentTimeMillis()).getTime();

            responsePack respPack=netAction.recordData(previousTime,
                    currentTime,
                    this.chartLabel.dataInput.dataBaseInitialTime,
                    type,
                    1000,this.chartLabel.patient.reference_value);
            List<Double> newData= respPack.valueList;
            if (newData.size()!=0){
                chartLabel.updateData(chartLabel.dataInput.getData(newData,10));
            }
            previousTime=respPack.lastTime;
        }
    }
}

