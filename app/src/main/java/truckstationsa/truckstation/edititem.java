package truckstationsa.truckstation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by wafaa7maD on 21/02/18.
 */

public class edititem extends AppCompatActivity {

    String id="-L5hPscXmLNDYe8_3KNL" ;
    DatabaseReference databaseref;
String tid="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edititem);
        Bundle b = getIntent().getExtras();
        tid = b.getString("tid");
        //start


    }}