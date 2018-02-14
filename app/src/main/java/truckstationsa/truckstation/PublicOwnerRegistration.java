package truckstationsa.truckstation;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by manal on 2/10/2018.
 */

public class PublicOwnerRegistration extends AppCompatActivity {
    String address ;
    Context context;
    int PLACE_PICKER_REQUEST = 1;
    TextView location ;
    EditText password , phone , email , userName , Qusen ;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    Button rigister ;
    DatabaseReference fdb;
    double x =0 , y =0 ;
    private ProgressDialog mProgress;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.go_to_public_register_page);
        context = this;
        mAuth = FirebaseAuth.getInstance();
        location = (TextView) findViewById(R.id.location);
        password = (EditText) findViewById(R.id.pass);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        Qusen = (EditText) findViewById(R.id.qusin);
        userName = (EditText) findViewById(R.id.username);
        rigister = (Button) findViewById(R.id.singup);

        mProgress = new ProgressDialog(this);
        fdb = FirebaseDatabase.getInstance().getReference("PublicFoodTruckOwner");

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
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    Intent intent = new Intent(PublicOwnerRegistration.this, publicOnerLogIn.class);
                    startActivity(intent);
                    finish();
                }
            }
        };}

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

        final String  pass =    password.getText().toString().trim();
        final String  phoneN =    phone.getText().toString().trim();
        final String  emailp =    email.getText().toString().trim();
        final String  username = userName.getText().toString().trim();
        final String  qusin =    Qusen.getText().toString().trim();



        if ( !TextUtils.isEmpty(emailp) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(phoneN) && !TextUtils.isEmpty(qusin)  && !TextUtils.isEmpty(username) ) {

            mProgress.setMessage("انتضر من فضلك....");
            mProgress.show();

            mAuth.createUserWithEmailAndPassword(emailp , pass)  // This method is inside firebaseauth class
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() { // to tell me if the method create.. is done
                        // onComplete will be called when create method fineshed
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            mProgress.dismiss();  //End showing msg

                            if (task.isSuccessful()) { // If we registerd the user
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                String uid = user.getUid();
                                String id = fdb.push().getKey();
                                PublicFoodTruckOwner owner = new PublicFoodTruckOwner(username, pass, emailp,Integer.parseInt(phoneN) ,  x, y , qusin , uid);
                                fdb.child(id).setValue(owner);
                                Toast.makeText(PublicOwnerRegistration.this, "تم التسجل بنجاح!!", Toast.LENGTH_SHORT).show();
                                // Intent intent = new Intent(GoTOCustomerRegisterPage.this, .class);
                                // startActivity(intent);
                                finish();
                            } else
                                Toast.makeText(PublicOwnerRegistration.this, "البريد الالكتروني مستخدم مسبقا", Toast.LENGTH_SHORT).show();

                        }
                    });

        }

    }
}
