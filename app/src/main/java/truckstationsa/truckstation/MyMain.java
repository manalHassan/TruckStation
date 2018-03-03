package truckstationsa.truckstation;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MyMain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    final static String DB_URL = "https://truckstation-3c3eb.firebaseio.com/";
    EditText nameeditText, urleditText;
    Button btnsave;
    private FirebaseAuth firebaseAuth;
    ListView listView;
    FirebaseClient firebaseClient;
    DrawerLayout drawer;

    //nmnmm
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       // listView = findViewById(R.id.listview);
      //  firebaseClient = new FirebaseClient(this,DB_URL, listView);
        ///firebaseClient.refreshdata();
       // firebaseClient.savedata("pu", "");
       // Toast.makeText(this,"you hear",Toast.LENGTH_LONG).show();

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

            drawer.addDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);


       // https://res.cloudinary.com/dlxwhpn3p/image/upload/t_media_lib_thumb/v1518470168/C5lvQRqXEAI11zy.jpg-large.jpg
        //http://res.cloudinary.com/mrudula/image/upload/v1495808239/dog9_xyjjfb.jpg"
        //https://res.cloudinary.com/dlxwhpn3p/image/upload/t_media_lib_thumb/v1518472141/skBVf8mx_400x400.jpg




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
            Intent intent = new Intent(MyMain.this, customer_profilenew.class);
            Bundle b=new Bundle();
            //b.putString("id",user);
            //intent.putExtras(b);
            startActivity(intent);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;

        } else if (id == R.id.nav_menu_P) {
            Intent intent = new Intent(MyMain.this, ListPuplic.class);
            Bundle b=new Bundle();
           // b.putString("id",user);
           // intent.putExtras(b);
            startActivity(intent);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;

        }
        else if (id == R.id.nav_menu_V) {
            Intent intent = new Intent(MyMain.this, ListPrivate.class);
            Bundle b=new Bundle();
          //  b.putString("id",user);
          //  intent.putExtras(b);
            startActivity(intent);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;

        }

        else if (id == R.id.nav_menu) {
/*
            Intent intent = new Intent(publictruckownerprofile.this, ownermenu.class);
            Bundle b=new Bundle();
            b.putString("id",user);
            intent.putExtras(b);
            startActivity(intent);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
            */
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
