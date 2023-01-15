package chartPanel;
import netRelated.*;
import javax.swing.*;
import java.sql.Timestamp;
import java.util.List;

public class UpdateWorker extends SwingWorker<Void, List<Double>[]> {

    private SeriesChartPane chart;
    long previousTime;
    String type;


    public UpdateWorker(SeriesChartPane chart) {
        this.chart = chart;
        this.previousTime=this.chart.time;
        this.type=this.chart.type;
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (true) {
            Thread.sleep(50);
            long currentTime=new Timestamp(System.currentTimeMillis()).getTime();

            responsePack respPack=netAction.recordData(previousTime,
                    currentTime,
                    this.chart.dataInput.dataBaseInitialTime,
                    type, 2,this.chart.patient.reference_value);
            List<Double> newData= respPack.valueList;
            if (newData.size()!=0){
                chart.updateData(chart.getDataInput().getData(newData,100));
            }
            previousTime=respPack.lastTime;

            if (type == "ecg2"){

            }
        }
    }
}