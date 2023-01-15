package chartPanel;
import java.util.ArrayList;
import java.util.List;
/* reference 1
   the structure and implementation of multi-threading, SwingWorker and XYchart that built SeriesChartPane, inputData
   and UpdateWorker is inspired from:
   https://stackoverflow.com/questions/42662611/adding-multiple-real-time-graphs-from-xchart-onto-a-jpanel
   end of reference 1
*/

/**
 *
 */
public class inputData {
    public List[] partData;
    private int position = 0;
    int size;
    public long dataBaseInitialTime;
    public inputData(List<Double> initialValue,long dataBaseInitialTime, double period){
        this.dataBaseInitialTime=dataBaseInitialTime;
        size=initialValue.size();
        List<Double> initialTime = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            initialTime.add((double)i*period);
        }
        partData= new List[]{initialTime, initialValue};
    }

    /**
     *
     * @param newData
     * @param whiteSpace
     * @return
     */
    public List[] getData(List<Double> newData,int whiteSpace){
        for(double data:newData) {
            // change to the next 'actual' value depending on the step, this can be changed to a command that grab one data
            // from server at that time instant
            partData[1].set(position, data);
            position++;
            if (position>=size) {
                position=0;
            }
        }
        if (position+whiteSpace<size-1){
            List valueFormer=partData[1].subList(position+whiteSpace, size);
            List timeFormer=partData[0].subList(position+whiteSpace, size);
            List valueLatter=partData[1].subList(0,position);
            List timeLatter=partData[0].subList(0,position);
            return new List[]{timeFormer,valueFormer,timeLatter,valueLatter};
        }else{
            List valueFormer=new ArrayList<>();
            List timeFormer=new ArrayList<>();
            valueFormer.add(partData[1].get(size-1));
            timeFormer.add(partData[0].get(size-1));
            List valueLatter=partData[1].subList(0,position);
            List timeLatter=partData[0].subList(0,position);
            return new List[]{timeFormer,valueFormer,timeLatter,valueLatter};
        }
    }
}
