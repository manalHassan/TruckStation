package truckstationsa.truckstation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
    static double xx=12.32;
    static double yy=12.32;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);



        databaseref = FirebaseDatabase.getInstance().getReference();
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
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
                phone.setText( ""+phonnum);

                cusine = (EditText) findViewById(R.id.cusin);
                cusine.setText(cusin);

                passwor = (EditText) findViewById(R.id.pass);
                passwor.setText(  password );

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
        final double x=12.321;
        final double y = 13.1;
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();//customer id is the same as rating id to make it easy to refer
        if (!TextUtils.isEmpty(emailp) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(phoneN) && !TextUtils.isEmpty(qusin) && !TextUtils.isEmpty(usern) && !TextUtils.isEmpty(wh)) {

            databaseref = FirebaseDatabase.getInstance().getReference("PublicFoodTruckOwner").child(id);

            PublicFoodTruckOwner owner = new PublicFoodTruckOwner(usern, pass, emailp, Integer.parseInt(phoneN), x, y, qusin,wh);
            databaseref.setValue(owner);
            Toast.makeText(editprofile.this, "تم حفظ التغييرات بنجاح!!", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(editprofile.this, "البريد الالكتروني مستخدم مسبقا", Toast.LENGTH_SHORT).show();


    }

}



