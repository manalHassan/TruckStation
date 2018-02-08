package truckstationsa.truckstation;

/**
 * Created by manal on 2/8/2018.
 */

public class Item {
    private static int ItemID =0;
    private int CatID ;
    private String  IName , IPicture , Idescription;
    private double IPrice ;
public Item (String IName ,String IPicture ,String Idescription , double IPrice  ,int CatID ){

    this.setCatID(CatID);
    this.setIdescription(Idescription);
    this.setIName(IName);
    this.setIPicture(IPicture);
    this.setItemID(ItemID++);
    this.setIPrice(IPrice);

}
    public void setCatID(int catID) {
        CatID = catID;
    }

    public void setIdescription(String idescription) {
        Idescription = idescription;
    }

    public void setIName(String IName) {
        this.IName = IName;
    }

    public void setIPicture(String IPicture) {
        this.IPicture = IPicture;
    }

    public void setIPrice(double IPrice) {
        this.IPrice = IPrice;
    }

    public  void setItemID(int itemID) {
        ItemID = itemID;
    }

    public double getIPrice() {
        return IPrice;
    }

    public int getCatID() {
        return CatID;
    }

    public  int getItemID() {
        return ItemID;
    }

    public String getIdescription() {
        return Idescription;
    }

    public String getIName() {
        return IName;
    }

    public String getIPicture() {
        return IPicture;
    }
}
