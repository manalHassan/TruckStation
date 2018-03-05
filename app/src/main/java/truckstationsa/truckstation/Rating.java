package truckstationsa.truckstation;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
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

public class Rating extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RatingBar mRatingBar;
    TextView mRatingScale;
    EditText mFeedback;
    Button mSendFeedback;
    FirebaseAuth firebaseAuth;

    // DATABASE
    DatabaseReference RatingRef;
    DatabaseReference CommentRef;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rating_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
         mRatingScale = (TextView) findViewById(R.id.tvRatingScale);
         mFeedback = (EditText) findViewById(R.id.etFeedback);
         mSendFeedback = (Button) findViewById(R.id.btnSubmit);
        firebaseAuth = FirebaseAuth.getInstance();
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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    public  void DoRating(View view ){

        RatingRef.addValueEventListener(new ValueEventListener() {
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
                //finish();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });


        //add comment
       /* final String Comment=mFeedback.getText().toString();

if(!TextUtils.isEmpty(Comment)) {

    CommentRef.addValueEventListener(new ValueEventListener() {

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String id = user.getUid();//customer id is the same as comment id to make it easy to refer

            final String FID = "FIDtst";
            final String CID = id;
            if (!TextUtils.isEmpty(FID) && !TextUtils.isEmpty(CID)) {

              Comment commentObject= new Comment(CID ,FID ,Comment);

                CommentRef.child(id).setValue(commentObject);
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




*/

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent intent = new Intent(this, customer_profilenew.class);
            Bundle b=new Bundle();
            //b.putString("id",user);
            //intent.putExtras(b);
            startActivity(intent);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;

        } else if (id == R.id.nav_publiclist) {
            Intent intent = new Intent(this, ListPuplic.class);
            Bundle b=new Bundle();
            // b.putString("id",user);
            // intent.putExtras(b);
            startActivity(intent);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;

        }
        else if (id == R.id.nav_privatelist) {
            Intent intent = new Intent(this, ListPrivate.class);
            Bundle b=new Bundle();
            // b.putString("id",user);
            // intent.putExtras(b);
            startActivity(intent);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;

        }
        else if (id == R.id.nav_map) {
            Intent intent = new Intent(this, VisitorHomePage.class);
            Bundle b=new Bundle();
            //  b.putString("id",user);
            // intent.putExtras(b);
            startActivity(intent);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;

        }

        else if (id == R.id.nav_nearmap) {
            Intent intent = new Intent(this, NearByTrucks.class);
            Bundle b=new Bundle();
            //  b.putString("id",user);
            // intent.putExtras(b);
            startActivity(intent);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;

        }

        else if (id == R.id.nav_pre_request) {
/*
            Intent intent = new Intent(this, ownermenu.class);
            Bundle b=new Bundle();
            b.putString("id",user);
            intent.putExtras(b);
            startActivity(intent);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
            */
        }


        else if (id == R.id.nav_pre_preorder) {
/*
  Intent intent = new Intent(this, editprofile.class);
            Bundle b=new Bundle();
            b.putString("id",user);
            intent.putExtras(b);
            startActivity(intent);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
 */
        }
        else if (id == R.id.nav_app) {

            Intent intent = new Intent(this, Chart.class);
            Bundle b=new Bundle();
            //  b.putString("id",user);
            //  intent.putExtras(b);
            startActivity(intent);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;

        }
        else if (id == R.id.nav_logout) {

            firebaseAuth.signOut();
            if(firebaseAuth.getCurrentUser() == null){
                Toast.makeText(this , "تم تسجيل الدخول بنجاح" , Toast.LENGTH_SHORT).show();
                startActivity(new Intent (this , CustomerLogInPage.class));
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;

            }}

        return false;
    }

}
