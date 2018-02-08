package truckstationsa.truckstation;

/**
 * Created by manal on 2/8/2018.
 */

public class PreOrderItem {
    private  int PreID  ;
    private int ItemID , quantity ;

    public PreOrderItem (int PreID , int ItemID , int quantity ){
        this.setItemID(ItemID);
        this.setPreID(PreID);
        this.setQuantity(quantity);
    }

    public void setItemID(int itemID) {
        ItemID = itemID;
    }

    public void setPreID(int preID) {
        PreID = preID;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public  int getPreID() {
        return PreID;
    }

    public int getItemID() {
        return ItemID;
    }

    public int getQuantity() {
        return quantity;
    }
}
