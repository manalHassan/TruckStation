package truckstationsa.truckstation;

/**
 * Created by manal on 2/8/2018.
 */

public class Category {
    private String MID ;
    private String   CatID  ;
    private String CatName ;

    public Category (String CatName , String  MID , String   CatID){
        this.setCatID(CatID);
        this.setCatName(CatName);
        this.setMID(MID);
    }

    public void setMID(String  MID) {
        this.MID = MID;
    }

    public  void setCatID(String  catID) {
        CatID = catID;
    }

    public void setCatName(String catName) {
        CatName = catName;
    }

    public  String  getCatID() {
        return CatID;
    }

    public String  getMID() {
        return MID;
    }

    public String getCatName() {
        return CatName;
    }
}
