package netRelated;

import org.junit.jupiter.api.Test;


import java.sql.Timestamp;

class netActionTest {

    @Test
    void recordData() {
        netAction net = new netAction();
        long currentTime=new Timestamp(System.currentTimeMillis()).getTime();
        responsePack pack=net.recordFastData(currentTime-100,currentTime,net.getInitialTime(),"ecg1");
        System.out.println(pack.valueList);
    }
}