package chartPanel;
import java.util.ArrayList;
import java.util.List;

/**
 * class to manage the structure of the data to plot the graph. Each object of this belongs to one specific graph.
 * When creating the graph, the initial values are plotted. Updating the object will change some values stored.
 * The user will see two lines. And the blank between them moving from right to left as time going.
 * The rightmost value of the line on the left is the newest value.
 */
public class inputData {
    /**
     * List with two sublist storing the time-axis and y-axis.
     * Values of y-axis will be updated from the first value to the last value each time.
     */
    public List[] partData;
    /**
     * index of the y-axis values of partData that should be updated
     */
    private int position = 0;
    /**
     * number of the y-axis values
     */
    int size;
    /**
     * the corresponding time of the first value from the database
     */
    public long dataBaseInitialTime;

    /**
     * create the object based on the initialValue
     * @param initialValue values to be plotted at first time
     * @param dataBaseInitialTime the corresponding time of the first value from the database
     * @param period sampling interval of the signal in milliseconds
     */
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
     * method to update the object by replacing values in y-axis of partData.
     * Separate the partData into two lines at the latest point.
     * Thus, the right side line (latter) have more recent values than the left side line (former).
     * @param newData new values from the database
     * @param whiteSpace blank between two lines in samples
     * @return values of time-axes and y-axes of two lines
     */
    public List[] getData(List<Double> newData,int whiteSpace){
        for(double data:newData) {
            // reset the values of y-axis of partData at position
            partData[1].set(position, data);
            position++;
            if (position>=size) {
                //start over when the position exceed the size
                position=0;
            }
        }
        if (position+whiteSpace<size-1){
            // case when position + witheSpace is not beyond the size
            List valueFormer=partData[1].subList(position+whiteSpace, size);
            List timeFormer=partData[0].subList(position+whiteSpace, size);
            List valueLatter=partData[1].subList(0,position);
            List timeLatter=partData[0].subList(0,position);
            return new List[]{timeFormer,valueFormer,timeLatter,valueLatter};
        }else{
            // case when position + witheSpace is beyond the size
            // the right side line should be empty,
            // but a value is needed to keep the size of the graph constant
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
