package truckstationsa.truckstation;

/**
 * Created by manal on 2/8/2018.
 */

public class Menu {
    private static int MID = 0 ;
    private String FID ;
public Menu (String FID ){
    this.setFID(FID);
    this.setMID(MID++);
}
    public void setFID(String FID) {
        this.FID = FID;
    }

    public void setMID(int MID) {
        Menu.MID = MID;
    }

    public String getFID() {
        return FID;
    }

    public  int getMID() {
        return MID;
    }
}
