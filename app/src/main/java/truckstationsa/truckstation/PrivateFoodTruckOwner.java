package truckstationsa.truckstation;

/**
 * Created by manal on 2/10/2018.
 */

public class PrivateFoodTruckOwner  {

    private  String FUsername  , FPassword , FEmail  , FWorkingHours , Fstatus , qusins , uid;
    private  int FPoneNoumber ;


    public PrivateFoodTruckOwner ( String FUsername  ,String FPassword ,String FEmail ,  int FPoneNoumber  ,String  qusins , String uid ){
        this.setFEmail(FEmail);

        this.setFPassword(FPassword);

        this.setFPoneNoumber(FPoneNoumber);

        this.setFUsername(FUsername);

        this.setQusins(qusins);

        this.setUid(uid);

    }

    public void setQusins(String qusins) {
        this.qusins = qusins;
    }

    public void setFEmail(String FEmail) {
        this.FEmail = FEmail;
    }

    public void setFPassword(String FPassword) {
        this.FPassword = FPassword;
    }

    public void setFPoneNoumber(int FPoneNoumber) {
        this.FPoneNoumber = FPoneNoumber;
    }


    public void setFstatus(String fstatus) {
        Fstatus = fstatus;
    }


    public void setFUsername(String FUsername) {
        this.FUsername = FUsername;
    }

    public void setFWorkingHours(String FWorkingHours) {
        this.FWorkingHours = FWorkingHours;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getFPoneNoumber() {
        return FPoneNoumber;
    }

    public String getFEmail() {
        return FEmail;
    }

    public String getFPassword() {
        return FPassword;
    }


    public String getFstatus() {
        return Fstatus;
    }


    public String getFUsername() {
        return FUsername;
    }

    public String getFWorkingHours() {
        return FWorkingHours;
    }

    public String getQusins() {
        return qusins;
    }

    public String getUid() {
        return uid;
    }
}
