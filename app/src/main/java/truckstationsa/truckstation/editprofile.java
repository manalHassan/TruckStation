package truckstationsa.truckstation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class editprofile extends AppCompatActivity {

    // String id="-L5hPscXmLNDYe8_3KNL" ;
    DatabaseReference databaseref;
    EditText username;
    EditText mail;
    EditText phone;
    EditText passwor;
    EditText cusine;
    EditText workh;
    private String address;
    static double xx = 12.32;
    static double yy = 12.32;
    FirebaseAuth firebaseAuth;
    Context context;
    int PLACE_PICKER_REQUEST = 1;
    private double x = 0, y = 0;
    private TextView location ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);
        context = this;
        location = (TextView) findViewById(R.id.location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                // finish();
                Intent intent;
                try {
                    address = "  ";
                    intent = builder.build((Activity) context);
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);

                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });
        databaseref = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();//customer id is the same as rating id to make it easy to refer
        // Read from the database
        databaseref.child("PublicFoodTruckOwner").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                String name = dataSnapshot.child("fusername").getValue(String.class);
                int phonnum = dataSnapshot.child("fponeNoumber").getValue(int.class);
                String email = dataSnapshot.child("femail").getValue(String.class);
                String password = dataSnapshot.child("fpassword").getValue(String.class);
                String cusin = dataSnapshot.child("qusins").getValue(String.class);
                String whh = dataSnapshot.child("fworkingHours").getValue(String.class);
                // xx = dataSnapshot.child("xflication").getValue(Double.class);
                // yy = dataSnapshot.child("yflication").getValue(Double.class);

                username = (EditText) findViewById(R.id.name);
                username.setText(name);

                mail = (EditText) findViewById(R.id.email);
                mail.setText(email);

                phone = (EditText) findViewById(R.id.phone);
                phone.setText("" + phonnum);

                cusine = (EditText) findViewById(R.id.cusin);
                cusine.setText(cusin);

                passwor = (EditText) findViewById(R.id.pass);
                passwor.setText(password);

                workh = (EditText) findViewById(R.id.etFeedback);
                workh.setText(whh);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

                Toast.makeText(editprofile.this, "لايوجد اتصال بالانترنت", Toast.LENGTH_LONG).show();
            }
        });


        //end

    }


    public void chickInfo(View view) {
        final String pass = passwor.getText().toString().trim();
        final String phoneN = phone.getText().toString().trim();
        final String emailp = mail.getText().toString().trim();
        final String usern = username.getText().toString().trim();
        final String qusin = cusine.getText().toString().trim();
        final String wh = workh.getText().toString().trim();
        final double x = 12.321;
        final double y = 13.1;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();//customer id is the same as rating id to make it easy to refer
        if (!TextUtils.isEmpty(emailp) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(phoneN) && !TextUtils.isEmpty(qusin) && !TextUtils.isEmpty(usern) && !TextUtils.isEmpty(wh)) {

            databaseref = FirebaseDatabase.getInstance().getReference("PublicFoodTruckOwner").child(id);

            PublicFoodTruckOwner owner = new PublicFoodTruckOwner();
            owner.setFUsername(usern);
            owner.setFPassword(pass);
            owner.setFEmail(emailp);
            owner.setFPoneNoumber(Integer.parseInt(phoneN));
            owner.setXFLication(x);
            owner.setYFLocation(y);
            owner.setQusins(qusin);
            owner.setFWorkingHours(wh);
            databaseref.setValue(owner);
            Toast.makeText(editprofile.this, "تم حفظ التغييرات بنجاح!!", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(editprofile.this, "البريد الالكتروني مستخدم مسبقا", Toast.LENGTH_SHORT).show();


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);

                x = place.getLatLng().latitude;
                y = place.getLatLng().longitude;


            }
        }
    }

}

