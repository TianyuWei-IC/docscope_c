package netRelated;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class responsePack {
//    public Map<Short,List<Double>> values= new HashMap<Short,List<Double>>();
//    public responsePack(List<Short> type,List<List<Double>> valueList){
//        for(int i = 0; i < type.size(); i++){
//            values.put(type.get(i),valueList.get(i));
//        }
//    }
public List<Double> valueList;
    public long lastTime=0;

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public void setValueList(List<Double> valueList) {
        this.valueList = valueList;
    }
}
