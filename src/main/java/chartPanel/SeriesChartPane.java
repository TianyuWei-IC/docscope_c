package chartPanel;
import master.Patient;
import java.util.List;
/* reference 1
   the structure and implementation of multi-threading, SwingWorker and XYchart that built SeriesChartPane, inputData
   and UpdateWorker is inspired from:
   https://stackoverflow.com/questions/42662611/adding-multiple-real-time-graphs-from-xchart-onto-a-jpanel
   end of reference 1
*/

public class SeriesChartPane extends Recording_Panel  {
    Patient patient;
    public SeriesChartPane(inputData dataInput,long time,String type, Patient patient, String title,String mode){
        super(dataInput,time,type,title,mode);
        this.patient = patient;
        this.worker = new UpdateWorker(this);
        worker.execute();
    }
    public inputData getDataInput() {
        return dataInput;
    }
    public void updateData(List[] data) {
        chart.updateXYSeries("former", data[0], data[1], null);
        chart.updateXYSeries("latter", data[2], data[3], null);
        repaint();
    }

    public Patient find_patient(){
        return this.patient;
    }
}