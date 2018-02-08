package truckstationsa.truckstation;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Created by manal on 2/8/2018.
 */

public class Post {
    private static int PID = 0 ;
    private String Post , FID ;
    private DateTimeFormatter dtf ;
    private String PDate , Time ;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Post (String Post , String  FID  ){
   setFID(FID);
   setPost(Post);
   dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
   LocalDateTime now = LocalDateTime.now();
   System.out.println(dtf.format(now)); //2016/11/16 12:08:43
   setTime(dtf.format(now).substring(dtf.format(now).indexOf(" ")));
   setPDate(dtf.format(now).substring(0,dtf.format(now).indexOf(" ")));
    }

    public void setFID(String FID) {
        this.FID = FID;
    }

    public void setPDate(String PDate) {
        this.PDate = PDate;
    }

    public void setTime(String time) {
        this.Time = time;
    }

    public void setPost(String post) {
        this.Post = post;
    }

    public String getFID() {
        return FID;
    }

    public String getPDate() {
        return PDate;
    }

    public String getTime() {
        return Time;
    }

    public String getPost() {
        return Post;
    }

}


