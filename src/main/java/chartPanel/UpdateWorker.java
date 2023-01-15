package chartPanel;
import netRelated.*;
import javax.swing.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * class for constant updating the data for SeriesChartPane
 */
public class UpdateWorker extends SwingWorker<Void, List<Double>[]> {

    private SeriesChartPane chart;
    /*
    the time of the data obtain prior to the latest data
     */
    long previousTime;
    String type;

    /**
     * class for constant updating the data for SeriesChartPane
     */
    public UpdateWorker(SeriesChartPane chart) {
        this.chart = chart;
        this.previousTime=this.chart.time;
        this.type=this.chart.type;
    }

    /*
    here doInBackground is used to pulling data from server at certain time interval
     */
    @Override
    protected Void doInBackground() throws Exception {
        while (true) {
            Thread.sleep(50);
            long currentTime=new Timestamp(System.currentTimeMillis()).getTime();
            // obtain data from server
            // previous time is corresponding time of the last value of the previous request.
            responsePack respPack=netAction.recordData(previousTime,
                    currentTime,
                    this.chart.dataInput.dataBaseInitialTime,
                    type, 2,this.chart.patient.reference_value);
            // cast into List<Double>
            List<Double> newData= respPack.valueList;
            // in case it is empty
            if (newData.size()!=0){
                chart.updateData(chart.getDataInput().getData(newData,100));
                // set the previous time for next request
                previousTime=respPack.lastTime;
            }
        }
    }
}