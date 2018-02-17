package truckstationsa.truckstation;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

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
    DatabaseReference fdb2;
    double x =0 , y =0 ;
    private ProgressDialog mProgress;
    //for imge
    private DatabaseReference mDatabase2;
    private EditText PName;
    private Button Upload_image,AddP;
    Uri FilePathUri;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    int Image_Request_Code = 7;
    ProgressDialog progressDialog ;

    // Folder path for Firebase Storage.
    String Storage_Path = "Trucks Images/";

    //////
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
        //Start Image code
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("PublicFoodTruckOwner");
        Upload_image = (Button) findViewById(R.id.Pimage);
        //Pimage = (ImageView) view.findViewById(R.id.imageView);

        //////////////
        // Adding click listener to Choose image button.
        Upload_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {// هذي تختار من الصور الي بالجهاز

                Intent intent = new Intent();

                // Setting intent type as image to select image from phone storage.
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Please Select Image"), Image_Request_Code);
            }
        });
        //////////////////

        mProgress = new ProgressDialog(this);
       // fdb = FirebaseDatabase.getInstance().getReference("PublicFoodTruckOwner");
       // fdb2=FirebaseDatabase.getInstance().getReference("truck");

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
        };}//creat


    /////
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) { //هذي تاكد من الصوره

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {
                // Getting selected image into Bitmap.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), FilePathUri);
                // Setting up bitmap selected image into ImageView.
                //Pimage.setImageBitmap(bitmap);
                // After selecting image change choose button above text.
                Upload_image.setText("Image Selected");
            } catch (IOException e) {e.printStackTrace(); }
        }
    }

    ////

    // Creating Method to get the selected image file Extension from File Path URI.
    public String GetFileExtension(Uri uri) {
        ContentResolver contentResolver = this.getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    /*   هذي شلتها عشان تتعارض نع وحدة اذا مالها داعي خلاص ماعرفت هي ايش له
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);

                x = place.getLatLng().latitude;
                y = place.getLatLng().longitude;


            }
        }
    }*/
    public  void chickInfo(View view ){

        final String  pass =    password.getText().toString().trim();
        final String  phoneN =    phone.getText().toString().trim();
        final String  emailp =    email.getText().toString().trim();
        final String  username = userName.getText().toString().trim();
        final String  qusin =    Qusen.getText().toString().trim();



        if ( !TextUtils.isEmpty(emailp) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(phoneN) && !TextUtils.isEmpty(qusin)  && !TextUtils.isEmpty(username) &&
                FilePathUri != null) {

            mProgress.setMessage("انتضر من فضلك....");
            mProgress.show();
            StorageReference storageReference2nd = storageReference.child(Storage_Path + System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));

// Adding addOnSuccessListener to second StorageReference.
            storageReference2nd.putFile(FilePathUri)

                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                             String url="";
                             if(! taskSnapshot.getDownloadUrl().toString().equals(null))
                            url=taskSnapshot.getDownloadUrl().toString();

                            final String finalUrl = url;
                            mAuth.createUserWithEmailAndPassword(emailp , pass)  // This method is inside firebaseauth class
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() { // to tell me if the method create.. is done
                                        // onComplete will be called when create method fineshed
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {

                                            mProgress.dismiss();  //End showing msg

                                            if (task.isSuccessful()) { // If we registerd the user
                                                //Add
                                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                                String uid = user.getUid();

                                                 try {
                                                     // truck t=new truck(username, finalUrl);
                                                     PublicFoodTruckOwner owner = new PublicFoodTruckOwner(finalUrl, username, pass, emailp, Integer.parseInt(phoneN), x, y, qusin,uid);
                                                     databaseReference.child(uid).setValue(owner);
                                                     // fdb2.child(t.getTruckname()).setValue(t);
                                                 }catch (Exception e){
                                                    e.printStackTrace();
                                                 }
                                                Toast.makeText(PublicOwnerRegistration.this, "تم التسجل بنجاح!!", Toast.LENGTH_SHORT).show();
                                                // Intent intent = new Intent(GoTOCustomerRegisterPage.this, .class);
                                                // startActivity(intent);
                                                finish();
                                            } else
                                                Toast.makeText(PublicOwnerRegistration.this, "البريد الالكتروني مستخدم مسبقا", Toast.LENGTH_SHORT).show();

                                        }
                                    });
                        }
                    })
                    // If something goes wrong .
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                            // Hiding the progressDialog.
                            mProgress.dismiss();

                            // Showing exception erro message.
                            /////////////////////////////
                             Toast.makeText(PublicOwnerRegistration.this , exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })

                    // On progress change upload time.
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            // Setting progressDialog Title.

                        }
                    });


        }else if (!TextUtils.isEmpty(emailp) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(phoneN) && !TextUtils.isEmpty(qusin)  && !TextUtils.isEmpty(username) &&
                FilePathUri == null){

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
//<<<<<<< Updated upstream
                                String id = databaseReference.push().getKey();
                                PublicFoodTruckOwner owner = new PublicFoodTruckOwner("" , username, pass, emailp,Integer.parseInt(phoneN) ,  x, y , qusin , uid);
                                databaseReference.child(id).setValue(owner);
//======
                             //   PublicFoodTruckOwner owner = new PublicFoodTruckOwner("", username, pass, emailp, Integer.parseInt(phoneN), x, y, qusin);
                              //  databaseReference.child(uid).setValue(owner);
//>>>>>>> Stashed changes
                                Toast.makeText(PublicOwnerRegistration.this, "تم التسجل بنجاح!!", Toast.LENGTH_SHORT).show();
                                // Intent intent = new Intent(GoTOCustomerRegisterPage.this, .class);
                                // startActivity(intent);
                                finish();
                            } else
                                Toast.makeText(PublicOwnerRegistration.this, "البريد الالكتروني مستخدم مسبقا", Toast.LENGTH_SHORT).show();

                        }
                    });









        }//second if

    }
}
