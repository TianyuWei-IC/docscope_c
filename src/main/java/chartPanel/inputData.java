package chartPanel;


import java.util.ArrayList;
import java.util.List;

public class inputData {
    public List[] partData;
    private Integer position = 0;
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
    }


    public List[] getData(List<Double> newData,int whiteSpace){
        for(double data:newData) {
            // change to the next 'actual' value depending on the step, this can be changed to a command that grab one data
            // from server at that time instant
            partData[1].set(position, data);
            position++;
            if (position==size){position=0;}
        }


        if (position+whiteSpace<size){
            List valueFormer=partData[1].subList(position+whiteSpace, partData[1].size()-1);
            List timeFormer=partData[0].subList(position+whiteSpace, partData[1].size()-1);
            List valueLatter=partData[1].subList(0,position);
            List timeLatter=partData[0].subList(0,position);
            return new List[]{timeFormer,valueFormer,timeLatter,valueLatter};
        }else{
//            List valueFormer=partData[1].subList(position, partData[1].size());
//            List timeFormer=partData[0].subList(position, partData[1].size());
            List valueFormer=new ArrayList<>();
            List timeFormer=new ArrayList<>();
            List valueLatter=partData[1].subList(0,position);
            List timeLatter=partData[0].subList(0,position);
            return new List[]{timeFormer,valueFormer,timeLatter,valueLatter};
        }
    }
}
