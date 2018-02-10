package truckstationsa.truckstation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;

public class MainActivity extends AppCompatActivity {
Button customer , owner ;
TextView viseter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customer = (Button) findViewById(R.id.customer);
       owner =(Button) findViewById(R.id.owner);
        viseter = (TextView) findViewById(R.id.visitor);


    }

    public void goToVisitorHomePage (View view){
        Intent intent = new Intent(MainActivity.this , VisitorHomePage.class );
        startActivity(intent);

    }


    public void goToCustomerLogInPage (View view){
        Intent intent = new Intent(MainActivity.this , CustomerLogInPage.class );
        startActivity(intent);

    }

   public void goToOwnerLogInPage (View view){
        Intent intent = new Intent(MainActivity.this , goToOwnerLogInPage.class );
        startActivity(intent);

    }


}
