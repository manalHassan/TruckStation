package truckstationsa.truckstation;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Created by manal on 2/8/2018.
 */

public class Request {
    private String  RID;
    private String CID , FID , RStatus , RDate ;
    private DateTimeFormatter dtf ;
     @RequiresApi(api = Build.VERSION_CODES.O)
     public Request (String CID , String FID  ,String  RID,String RDate){
         //dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         //LocalDateTime now = LocalDateTime.now();
        // System.out.println(dtf.format(now));
         this.setRDate(RDate);
         this.setCID(CID);
         this.setFID(FID);
         this.setRID(RID);
         this.setRStatus("wait");
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

    public void setRID(String  RID) {
        this.RID = RID;
    }

    public void setRStatus(String RStatus) {
        this.RStatus = RStatus;
    }

    public String getFID() {
        return FID;
    }

    public String getCID() {
        return CID;
    }


    public String getRDate() {
        return RDate;
    }

    public String getRID() {
        return RID;
    }

    public String getRStatus() {
        return RStatus;
    }
}

