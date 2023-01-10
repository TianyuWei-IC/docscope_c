package netRelated;

import org.junit.jupiter.api.Test;


import java.sql.Timestamp;

class netActionTest {

    @Test
    void recordData() {
        netAction net = new netAction();
        long currentTime=new Timestamp(System.currentTimeMillis()).getTime();
        responsePack pack=net.recordData(currentTime-50,currentTime,net.getInitialTime(),"resp",2);
        System.out.println(pack.valueList);
    }
}