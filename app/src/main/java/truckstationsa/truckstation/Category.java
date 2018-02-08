package truckstationsa.truckstation;

/**
 * Created by manal on 2/8/2018.
 */

public class Category {
    private int MID ;
    private static int CatID = 0 ;
    private String CatName ;

    public Category (String CatName , int MID){
        this.setCatID(CatID++);
        this.setCatName(CatName);
        this.setMID(MID);
    }

    public void setMID(int MID) {
        this.MID = MID;
    }

    public  void setCatID(int catID) {
        CatID = catID;
    }

    public void setCatName(String catName) {
        CatName = catName;
    }

    public  int getCatID() {
        return CatID;
    }

    public int getMID() {
        return MID;
    }

    public String getCatName() {
        return CatName;
    }
}
