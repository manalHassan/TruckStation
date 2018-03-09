package truckstationsa.truckstation;

import android.app.Activity;
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

public class GoTOPrivatRegisterPage  extends AppCompatActivity {
    //new
    EditText password , phone , email , userName , Qusen ;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    Button rigister ;
    DatabaseReference fdb;
    DatabaseReference UDB;
    private ProgressDialog mProgress;
    private Button Upload_image,AddP;
    Uri FilePathUri;
    StorageReference storageReference;
    DatabaseReference databaseReference;
    int Image_Request_Code = 7;
    // Folder path for Firebase Storage.
    String Storage_Path = "Trucks Images/";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.go_to_private_register_page);
        mAuth = FirebaseAuth.getInstance();
        password = (EditText) findViewById(R.id.pass);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        Qusen = (EditText) findViewById(R.id.qusin);
        userName = (EditText) findViewById(R.id.username);
        rigister = (Button) findViewById(R.id.singup);
        UDB=FirebaseDatabase.getInstance().getReference("APPUsers");
        //Start Image code
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("PublicFoodTruckOwner");
        Upload_image = (Button) findViewById(R.id.Pimage);

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
        fdb = FirebaseDatabase.getInstance().getReference("PrivateFoodTruckOwner");
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                   // Intent intent = new Intent( GoTOPrivatRegisterPage.this, privateOnerLogIn.class); ///*************هذي privateOnerLogIn  ي منال مو موجدة *******************
                  //  startActivity(intent);
                   // finish();
                }
            }
        };}//creat
    ///
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
    ///

    // Creating Method to get the selected image file Extension from File Path URI.
    public String GetFileExtension(Uri uri) {
        ContentResolver contentResolver = this.getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }
    /////////////////

    public  void chickInfo(View view ){

        final String  pass =    password.getText().toString().trim();
        final String  phoneN =    phone.getText().toString().trim();
        final String  emailp =    email.getText().toString().trim();
        final String  username = userName.getText().toString().trim();
        final String  qusin =    Qusen.getText().toString().trim();



        if ( !TextUtils.isEmpty(emailp) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(phoneN) && !TextUtils.isEmpty(qusin)  && !TextUtils.isEmpty(username)&&FilePathUri != null ) {
            if (pass.matches("^(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,10}$")) {
            mProgress.setMessage("انتظر من فضلك....");
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
                                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                                String uid = user.getUid();
                                                //String id = fdb.push().getKey();
                                                PrivateFoodTruckOwner owner = new PrivateFoodTruckOwner(username, pass, emailp,Integer.parseInt(phoneN) , qusin , uid, finalUrl);
                                                fdb.child(uid).setValue(owner);

                                                //to know the type of the user
                                                String type="PrivateOwner";
                                                APPUsers user1=new APPUsers(type);
                                                UDB.child(uid).setValue(user1);
                                                Toast.makeText(GoTOPrivatRegisterPage.this, "تم التسجل بنجاح!!", Toast.LENGTH_SHORT).show();
                                                // Intent intent = new Intent(GoTOCustomerRegisterPage.this, .class);
                                                // startActivity(intent);
                                                finish();
                                            } else
                                                Toast.makeText(GoTOPrivatRegisterPage.this, "البريد الالكتروني غير صحيح ", Toast.LENGTH_SHORT).show();



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
                            Toast.makeText(GoTOPrivatRegisterPage.this , exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })

                    // On progress change upload time.
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            // Setting progressDialog Title.

                        }
                    });




        }else if ( !TextUtils.isEmpty(emailp) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(phoneN) && !TextUtils.isEmpty(qusin)  && !TextUtils.isEmpty(username) &&FilePathUri == null) {

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
                              //  String id = fdb.push().getKey();
                                PrivateFoodTruckOwner owner = new PrivateFoodTruckOwner(username, pass, emailp,Integer.parseInt(phoneN) , qusin , uid,"");
                                fdb.child(uid).setValue(owner);
                                //to know the type of the user
                                String type="PrivateOwner";
                                APPUsers user1=new APPUsers(type);
                                UDB.child(uid).setValue(user1);
                                Toast.makeText(GoTOPrivatRegisterPage.this, "تم التسجل بنجاح!!", Toast.LENGTH_SHORT).show();
                                // Intent intent = new Intent(GoTOCustomerRegisterPage.this, .class);
                                // startActivity(intent);
                                finish();
                            } else
                                Toast.makeText(GoTOPrivatRegisterPage.this, "البريد الالكتروني  غير صحيح او مستخدم مسبقا", Toast.LENGTH_SHORT).show();

                        }
                    });



            }
            else
            {      Toast.makeText(GoTOPrivatRegisterPage.this, "الرقم السري يجب ان يحتوي على رقم واحد على الاقل و حرف خاص واحد على الاقل وطوله ثمانية حروف ", Toast.LENGTH_SHORT).show();
            }}else {   Toast.makeText(GoTOPrivatRegisterPage.this, "تأكد من تعبئة جميع الحقول", Toast.LENGTH_SHORT).show();
        }

    }
}
