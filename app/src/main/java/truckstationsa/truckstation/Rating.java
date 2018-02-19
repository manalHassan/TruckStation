package truckstationsa.truckstation;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Rating extends AppCompatActivity {
    RatingBar mRatingBar;
    TextView mRatingScale;
    EditText mFeedback;
    Button mSendFeedback;
    FirebaseAuth firebaseAuth;
    int onleyOne=0;
    // DATABASE
    DatabaseReference RatingRef;
    DatabaseReference CommentRef;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);


         mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
         mRatingScale = (TextView) findViewById(R.id.tvRatingScale);
         mFeedback = (EditText) findViewById(R.id.etFeedback);
         mSendFeedback = (Button) findViewById(R.id.btnSubmit);

         //object in firebase
        database= FirebaseDatabase.getInstance();
        RatingRef = database.getReference("Rate"); // make sure its identical to the table name in the database
        CommentRef= database.getReference("Comment");

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        mRatingScale.setText("جدًا سيئة");
                        break;
                    case 2:
                        mRatingScale.setText("تحتاج إلى تطوير");
                        break;
                    case 3:
                        mRatingScale.setText("جيدة");
                        break;
                    case 4:
                        mRatingScale.setText("جيدة جدًا");
                        break;
                    case 5:
                        mRatingScale.setText("رائعة, لقد احببتها");
                        break;
                    default:
                        mRatingScale.setText("");
                }
            }
        });


    }



    public  void DoRating(View view ){
        //add comment
        final String Comment=mFeedback.getText().toString();

        if(!TextUtils.isEmpty(Comment)) {




            CommentRef.addListenerForSingleValueEvent(new ValueEventListener() {

                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    String id = user.getUid();//customer id is the same as comment id to make it easy to refer

                    final String FID = "FIDtst";
                    final String CID = id;

                    if (!TextUtils.isEmpty(FID) && !TextUtils.isEmpty(CID)&&onleyOne==0) {
                        onleyOne=1;
                        Comment commentObject= new Comment(CID ,FID ,Comment);
                        String commentID=CommentRef.push().getKey();

                        CommentRef.child(commentID).setValue(commentObject);
                        //Toast.makeText(Rating.this,  "شكرًا لمشاركتنا رأيك", Toast.LENGTH_SHORT).show();
                        // Intent intent = new Intent(GoTOCustomerRegisterPage.this, .class);
                        // startActivity(intent);
                        //finish();
                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }

        // rating

        RatingRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                String id = user.getUid();//customer id is the same as rating id to make it easy to refer

                final String FID="FIDtst";
                final String CID=id;
                final double ratingValue=(double)mRatingBar.getRating();
                final String strRate= String.valueOf(ratingValue).toString().trim();

                if ( !TextUtils.isEmpty(FID) && !TextUtils.isEmpty(CID) && !TextUtils.isEmpty(strRate)) {

                Rate rate= new Rate(CID,FID,ratingValue);

                RatingRef.child(id).setValue(rate);
                Toast.makeText(Rating.this, "شكرًا لمشاركتنا رأيك", Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(GoTOCustomerRegisterPage.this, .class);
                // startActivity(intent);
                finish();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });












        ///////////////////////////////////////////



        /*if ( !TextUtils.isEmpty(FID) && !TextUtils.isEmpty(CID) && !TextUtils.isEmpty(strRate)) {



                          //  if (task.isSuccessful()) { // b3deen a76 en user cant rate more than one



                           // } else
                                //Toast.makeText(GoTOCustomerRegisterPage.this, "البريد الالكتروني مستخدم مسبقا", Toast.LENGTH_SHORT).show();

                        }
                        else
            Toast.makeText(Rating.this, "wrong!!", Toast.LENGTH_SHORT).show();


*/
    }

}
