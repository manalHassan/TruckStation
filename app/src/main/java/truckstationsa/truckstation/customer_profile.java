package truckstationsa.truckstation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.core.Context;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class customer_profile extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
//jbjbjbj

    //view objects
    private DatabaseReference databaseReference;
    private FirebaseUser user;
    private EditText TextName;
    private EditText TextEmail;
    private EditText TextPhone;
    private Button ButtonEdit;


    private String username;
    private String email;
    private String phone;
    private String locationx;
    private String locationy;
    private  Customer c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);
        TextName = (EditText) findViewById(R.id.editname);
        TextEmail = (EditText) findViewById(R.id.editemail);
        TextPhone = (EditText) findViewById(R.id.editphone);

        user = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Customer").child(user.getUid());

        ValueEventListener EventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    //Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
                 //c=new Customer();
                 c = dataSnapshot.getValue(Customer.class);
                TextName.setText(c.getCFirstName());
                TextPhone.setText(c.getCPoneNoumber());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        databaseReference.addListenerForSingleValueEvent(EventListener);

       // TextEmail.setText(user.getEmail());
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




}//class
