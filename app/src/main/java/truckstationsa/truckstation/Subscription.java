package truckstationsa.truckstation;

import android.test.ServiceTestCase;

/**
 * Created by manal on 2/8/2018.
 */

public class Subscription {
    private String CID , FID ;

    public Subscription (String CID , String FID){
        this.setCID(CID);
        this.setFID(FID);
    }

    public void setFID(String FID) {
        this.FID = FID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public String getFID() {
        return FID;
    }

    public String getCID() {
        return CID;
    }
}
