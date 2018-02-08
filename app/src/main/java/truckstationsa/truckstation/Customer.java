package truckstationsa.truckstation;

/**
 * Created by manal on 2/8/2018.
 */

public class Customer  {
    private  String CUsername  , CPassword , CEmail ,CFirstName, CLastName ;
    private  int CPoneNoumber ;
    private double XCLication;
    private double YCLocation;

    public Customer (String CUsername  ,  String CPassword ,String  CEmail , String CFirstName,String  CLastName ,int CPoneNoumber ,double  XCLication,double  YCLocation){
        this.setCEmail( CEmail);

        this.setCFirstName( CFirstName);

        this.setCLastName(CLastName);

        this.setCPassword(CPassword) ;

        this.setCPoneNoumber( CPoneNoumber) ;

        this.setCUsername( CUsername);

        this.setXCLication( XCLication);

        this.setYCLocation( YCLocation);
    }

    public void setCEmail(String CEmail) {
        this.CEmail = CEmail;
    }

    public void setCFirstName(String CFirstName) {
        this.CFirstName = CFirstName;
    }

    public void setCLastName(String CLastName) {
        this.CLastName = CLastName;
    }

    public void setCPassword(String CPassword) {
        this.CPassword = CPassword;
    }

    public void setCPoneNoumber(int CPoneNoumber) {
        this.CPoneNoumber = CPoneNoumber;
    }

    public void setCUsername(String CUsername) {
        this.CUsername = CUsername;
    }

    public void setXCLication(double XLication) {
        this.XCLication = XLication;
    }

    public void setYCLocation(double YLocation) {
        this.YCLocation = YLocation;
    }

    public double getXCLication() {
        return XCLication;
    }

    public double getYCLocation() {
        return YCLocation;
    }

    public int getCPoneNoumber() {
        return CPoneNoumber;
    }

    public String getCEmail() {
        return CEmail;
    }

    public String getCFirstName() {
        return CFirstName;
    }

    public String getCLastName() {
        return CLastName;
    }

    public String getCPassword() {
        return CPassword;
    }

    public String getCUsername() {
        return CUsername;
    }
}

