package truckstationsa.truckstation;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Created by manal on 2/8/2018.
 */

public class Request {
    private static int RID = 0;
    private String CID , FID , RStatus , RDate ;
    private DateTimeFormatter dtf ;
     @RequiresApi(api = Build.VERSION_CODES.O)
     public Request (String CID , String FID  ){
         dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         LocalDateTime now = LocalDateTime.now();
        // System.out.println(dtf.format(now));
         this.setRDate(dtf.format(now));
         this.setCID(CID);
         this.setFID(FID);
         this.setRID(RID++);
     }


    public void setFID(String FID) {
        this.FID = FID;
    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public void setRDate(String RDate) {
        this.RDate = RDate;
    }

    public void setRID(int RID) {
        Request.RID = RID;
    }

    public void setRStatus(String RStatus) {
        this.RStatus = RStatus;
    }
}
