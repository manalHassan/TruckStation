package truckstationsa.truckstation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class publictruckownerprofile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DatabaseReference databaseref;
    String user = "-L5hPscXmLNDYe8_3KNL";
    TextView username;
    TextView mail;
    TextView phone;
    TextView wwh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        setContentView(R.layout.activity_main_public);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
*/

        databaseref = FirebaseDatabase.getInstance().getReference();

        // Read from the database
        databaseref.child("PublicFoodTruckOwner").child(user).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                String name = dataSnapshot.child("fusername").getValue(String.class);
                int phonnum = dataSnapshot.child("fponeNoumber").getValue(int.class);
                String email = dataSnapshot.child("femail").getValue(String.class);
                String cc = dataSnapshot.child("qusins").getValue(String.class);
                  String wh=dataSnapshot.child("fworkingHours").getValue(String.class);

                username = (TextView) findViewById(R.id.type);
                username.setText(" " + cc);
                TextView profilename = (TextView) findViewById(R.id.user);
                profilename.setText(name);
                wwh= (TextView) findViewById(R.id.wh);
                wwh.setText(" " + wh);
                mail = (TextView) findViewById(R.id.email);
                mail.setText(" " + email);
                phone = (TextView) findViewById(R.id.phone);
                phone.setText(" " + phonnum + " ");
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

                Toast.makeText(publictruckownerprofile.this, "لايوجد اتصال بالانترنت", Toast.LENGTH_LONG).show();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            Intent intent = new Intent(publictruckownerprofile.this, editprofile.class);
            Bundle b=new Bundle();
            b.putString("id",user);
            intent.putExtras(b);
            startActivity(intent);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;

        } else if (id == R.id.nav_preorder) {
            /*Intent intent = new Intent(MainActivity.this, postActivity.class);
            Bundle b=new Bundle();
            b.putString("id",user);
            intent.putExtras(b);
            startActivity(intent);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
            */
        }
        else if (id == R.id.nav_booking) {
            /*Intent intent = new Intent(MainActivity.this, postActivity.class);
            Bundle b=new Bundle();
            b.putString("id",user);
            intent.putExtras(b);
            startActivity(intent);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
            */
        }

        else if (id == R.id.nav_menu) {

  Intent intent = new Intent(publictruckownerprofile.this, ownermenu.class);
            Bundle b=new Bundle();
            b.putString("id",user);
            intent.putExtras(b);
            startActivity(intent);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }


        else if (id == R.id.nav_request) {
/*
  Intent intent = new Intent(MainActivity.this, editprofile.class);
            Bundle b=new Bundle();
            b.putString("id",user);
            intent.putExtras(b);
            startActivity(intent);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
 */
        }
        return false;
    }





}
