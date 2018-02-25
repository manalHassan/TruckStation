package truckstationsa.truckstation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;


// //هذا الجديد************************//////////
//////
public class customer_profilenew extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
//jbjbjbj

    //view objects
    String address;
    Context context;
    int PLACE_PICKER_REQUEST = 1;
    private double x =0 , y =0 ;
    private DatabaseReference databaseReference;
    private EditText TextName;
    private EditText TextEmail;
    private EditText TextPhone;
    private TextView Textmain;
    private TextView location;



    private String username;
    private String email;
    private String phone;
    private String locationx;
    private String locationy;
    private  Customer c=new Customer();
    private FirebaseAuth mAuth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_customer_profilenew);
        Textmain=(TextView)findViewById(R.id.name);
        TextName = (EditText) findViewById(R.id.editname);
        TextEmail = (EditText) findViewById(R.id.editemail);
        TextPhone = (EditText) findViewById(R.id.editphone);
        location = (TextView) findViewById(R.id.editlocation);


         user=FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();//
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Customer").child(id);
        Toast.makeText(this, databaseReference.toString(), Toast.LENGTH_SHORT).show();

        ValueEventListener EventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Toast.makeText(getApplicationContext(),"hdfhdsg", Toast.LENGTH_SHORT).show();
                // c=new Customer();
                try {
                    c = dataSnapshot.getValue(Customer.class);
                    Textmain.setText(c.getCFirstName()+c.getCLastName());
                    TextName.setText(c.getCFirstName());
                    TextPhone.setText(String.format("%d", c.getCPoneNoumber()));

                } catch (Exception e){
                    e.getMessage();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                TextPhone.setText("4756478564786546576378475346");
            }
        };
        databaseReference.addListenerForSingleValueEvent(EventListener);

        TextEmail.setText(user.getEmail());
        //to create listner, update info
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditCustomerInfo();
                //  Fragment fr = new ListCategories();
                //FragmentManager fm = getFragmentManager();
                // FragmentTransaction ft = fm.beginTransaction();


// ft.replace(R.id.content_frame, fr);
                // ft.commit();
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Fragment fr = new ListCategories();
                // FragmentManager fm = getFragmentManager();
                // FragmentTransaction ft = fm.beginTransaction();

                // ft.replace(R.id.content_frame, fr);
                // ft.commit();
            }
        });

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
        });

    }//creat

    private void EditCustomerInfo(){
        username = TextName.getText().toString().trim();
        email = TextEmail.getText().toString().trim();
        phone = TextPhone.getText().toString().trim();
        //empty feild
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(username)){
            Toast.makeText(this,"Please enter username",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(phone)){
            Toast.makeText(this,"Please enter phone",Toast.LENGTH_LONG).show();
            Toast.makeText(this,"Please enter phone",Toast.LENGTH_LONG).show();
            return;
        }

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (!email.matches(emailPattern))
        {
            Toast.makeText(this,"Invalid email formate", Toast.LENGTH_LONG).show();
            return;
        }


        c.setCEmail(email);
        c.setCFirstName(username);
        c.setCPoneNoumber(Integer.parseInt(phone));


        databaseReference.setValue(c);
        user.updateEmail(email);
        //user.updatePassword(password);
        Toast.makeText(this, "information saved...", Toast.LENGTH_LONG).show();
        //startActivity(new Intent(getActivity(), AdminHome2.class));
    }//edit

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

