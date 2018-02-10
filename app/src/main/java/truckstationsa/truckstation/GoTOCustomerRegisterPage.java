package truckstationsa.truckstation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

/**
 * Created by manal on 2/9/2018.
 */

public class GoTOCustomerRegisterPage  extends AppCompatActivity  {
    String address;
    Context context;
    int PLACE_PICKER_REQUEST = 1;
    TextView location ;
    EditText username , password , phone , email , Lname , Fname ;
    Button rigister ;
    DatabaseReference fdb;
    double x =0 , y =0 ;
    private ProgressDialog mProgress;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.go_to_customer_register_page);
        context = this;
        location = (TextView) findViewById(R.id.location);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.pass);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        Lname = (EditText) findViewById(R.id.lastname);
        Fname = (EditText) findViewById(R.id.firatname);
        rigister = (Button) findViewById(R.id.singup);
        mProgress = new ProgressDialog(this);
        fdb = FirebaseDatabase.getInstance().getReference("Cusomer");

       location.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                // finish();
                Intent intent;
                try {
                    address = "  ";
                    intent = builder.build((Activity)  context);
                    startActivityForResult(intent,PLACE_PICKER_REQUEST);

                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        }); }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);

                x = place.getLatLng().latitude;
                y = place.getLatLng().longitude;


            }
        }
    }
public  void chickInfo(View view ){
registerme();
}

public void registerme (){
    final String  UserName =    username.getText().toString().trim();
    final String  pass =    password.getText().toString().trim();
    final String  phoneN =    phone.getText().toString().trim();
    final String  emailc =    email.getText().toString().trim();
    final String  fname =    Fname.getText().toString().trim();
    final String  lname =    Lname.getText().toString().trim();

    if (!TextUtils.isEmpty(UserName) && !TextUtils.isEmpty(emailc) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(phoneN)) {


        mProgress.setMessage("Registering, please wait...");
        mProgress.show();
        String id = fdb.push().getKey();
        Customer customer = new Customer(UserName , pass ,emailc,fname,lname, Integer.parseInt(phoneN) , x , y);
        fdb.child(id).setValue(customer);
        mProgress.dismiss();

    }else
        Toast.makeText(this , "تاكد ان تملا جميع الفراغات" , Toast.LENGTH_LONG).show();


}
       }
