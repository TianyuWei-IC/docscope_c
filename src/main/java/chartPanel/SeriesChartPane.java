package chartPanel;
import master.Patient;
import java.util.List;

/**
 * panel specified for real time chart only
 */
public class SeriesChartPane extends Recording_Panel  {
    Patient patient;

    /**
     * @param patient JButton with all information of the patient
     * @param dataInput data structure to plot graph
     * @param time initial time
     * @param type signal type
     * @param title chart title
     * @param mode real time or recording
     */
    public SeriesChartPane(inputData dataInput,long time,String type, Patient patient, String title,String mode){
        super(dataInput,time,type,title,mode);
        this.patient = patient;
        //start the thread for real time plot
        this.worker = new UpdateWorker(this);
        worker.execute();
    }
    public inputData getDataInput() {
        return dataInput;
    }

    /**
     * method to update the chart
     * @param data values gained from the database
     */
    public void updateData(List[] data) {
        // update two lines
        chart.updateXYSeries("former", data[0], data[1], null);
        chart.updateXYSeries("latter", data[2], data[3], null);
        repaint();
    }

    public Patient find_patient(){
        return this.patient;
    }
}