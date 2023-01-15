package netRelated;

import java.util.List;

/**
 * class to store values and last time got from database
 */
public class responsePack {
    /**
     * values gained from the database
     */
    public List<Double> valueList;
    /**
     * time corresponding to the last value from valueList
     */
    public long lastTime=0;
}
