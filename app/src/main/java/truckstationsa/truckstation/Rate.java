package truckstationsa.truckstation;

/**
 * Created by manal on 2/8/2018.
 */

public class Rate {
    String FID , CID ;
    double ratingValue ;
    public Rate (String FID , String CID , double ratingValue){
        this.setCID(CID);
        this.setFID(FID);
        this.setRatingValue(ratingValue);

    }

    public void setCID(String CID) {
        this.CID = CID;
    }

    public void setFID(String FID) {
        this.FID = FID;
    }

    public void setRatingValue(double ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getCID() {
        return CID;
    }

    public String getFID() {
        return FID;
    }

    public double getRatingValue() {
        return ratingValue;
    }
}
