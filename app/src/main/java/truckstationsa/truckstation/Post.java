package truckstationsa.truckstation;
import android.os.Build;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import android.support.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by manal on 2/8/2018.
 */

public class Post {
    private String image  ;
    private String desc  ;
    private String name ;
    private String username;
    private String user_id;
    //private String post_id;
    //private DateTimeFormatter dtf ;
    //private String PDate , Time ;//


    public Post(){

    }
    @RequiresApi(api = Build.VERSION_CODES.O)

    public Post(String image , String desc   , String name , String username ,String user_id){
    this.image=image;
    this.desc=desc;
    this.name=name;
    this.username=username;
    this.user_id=user_id;
       // dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        //LocalDateTime now = LocalDateTime.now();
        // System.out.println(dtf.format(now)); //2016/11/16 12:08:43
        //this.setTime(dtf.format(now).substring(dtf.format(now).indexOf(" ")));
        //this.setPDate(dtf.format(now).substring(0,dtf.format(now).indexOf(" ")));

    }

    public Post(Post value) {

    }


    public void setImage(String image) {
        this.image = image;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setName(String name) {
        this.name = name;
    }

    //public void setPDate(String PDate) {
    //    this.PDate = PDate;
    //}

    //public void setTime(String time) {
     //   this.Time = time;
    //}

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUser_id(String user_id) { this.user_id=user_id; }
    //public String getPDate() {
      //  return PDate;
   // }

    //public String getTime() {
    //    return Time;
   // }


    public String getImage() {
        return image;
    }

    public String getDesc() { return desc; }

    public String getName() { return name; }

    public String getUsername() {
        return username;
    }

    public String getUser_id() {return user_id; }

}


