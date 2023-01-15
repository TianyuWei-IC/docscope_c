package netRelated;

import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;

class netActionTest {

    @Test
    void recordData() {
        netAction net = new netAction();
        long currentTime=new Timestamp(System.currentTimeMillis()).getTime();
        responsePack pack=net.recordData(currentTime-50,currentTime,net.getInitialTime(),"resp",2,"alpha");
        System.out.println(pack.valueList);
    }

    @Test
    void putReference() throws Exception {
//        netAction.putReference("alpha", Arrays.asList(100.0,35.0,100.0,40.0,140.0,90.0,90.0,60.0,35.0,10.0));
    }

    @Test
    void getReferences() {
//        System.out.println(netAction.getReferences());
    }

    @Test
    void postEmailAddress() {
        System.out.println(netAction.postEmailAddress("yaoshuyu0430@outlook.com"));
        System.out.println(netAction.postEmailAddress(null));
    }
}