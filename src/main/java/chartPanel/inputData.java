package chartPanel;


import java.util.ArrayList;
import java.util.List;

public class inputData {
    public List[] partData;
    private int position = 0;
    int size;
    public long dataBaseInitialTime;
    public inputData(List<Double> initialValue,long dataBaseInitialTime){
        this.dataBaseInitialTime=dataBaseInitialTime;
        size=initialValue.size();
        List<Double> initialTime = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            initialTime.add(0.002*i);
        }
        partData= new List[]{initialTime, initialValue};
        System.out.println("initial size "+size);
    }


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
            List valueLatter=partData[1].subList(0,position+1);
            List timeLatter=partData[0].subList(0,position+1);
            return new List[]{timeFormer,valueFormer,timeLatter,valueLatter};
        }else{
//            List valueFormer=partData[1].subList(position, partData[1].size());
//            List timeFormer=partData[0].subList(position, partData[1].size());
            List valueFormer=new ArrayList<>();
            List timeFormer=new ArrayList<>();
            valueFormer.add(partData[1].get(size-1));
            timeFormer.add(partData[0].get(size-1));
            List valueLatter=partData[1].subList(0,position+1);
            List timeLatter=partData[0].subList(0,position+1);
            return new List[]{timeFormer,valueFormer,timeLatter,valueLatter};
        }
    }
}
