package truckstationsa.truckstation;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by manal on 2/8/2018.
 */

@RequiresApi(api = Build.VERSION_CODES.O)
public class Comment {
    private String CID , FID , Comment ;
    private String CoID  ;
    private   DateTimeFormatter dtf ;
    private  String CoDate ;


///System.out.println(dtf.format(localDate)); //2016/11/16

   public Comment (String CID ,String FID ,String Comment ,String CoID  ){
       this.setCID(CID);
       dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       LocalDate now = LocalDate.now();
       this.setCoDate(dtf.format(now));
       this.setCoID(CoID);
       this.setFID(FID);
       this.setComment(Comment);
   }


    public void setCID(String CID) {
        this.CID = CID;
    }

    public void setCoDate( String  coDate) {
        CoDate = coDate;
    }

    public void setCoID(String  coID) {
        CoID = coID;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public void setFID(String FID) {
        this.FID = FID;
    }

    public  String  getCoDate() {
        return CoDate;
    }

    public String  getCoID() {
        return CoID;
    }

    public String getCID() {
        return CID;
    }

    public String getComment() {
        return Comment;
    }

    public String getFID() {
        return FID;
    }

}
