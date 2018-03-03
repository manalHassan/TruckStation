package truckstationsa.truckstation;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ListPrivate extends AppCompatActivity  {
    final static String DB_URL = "https://truckstation-3c3eb.firebaseio.com/";
    EditText nameeditText, urleditText;
    Button btnsave;
    ListView listView;
    FirebaseClient firebaseClient;
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_private);
/////////////
///////

        listView = (ListView) findViewById(R.id.listview);
        firebaseClient = new FirebaseClient(this, DB_URL, listView);
        //  firebaseClient.refreshdata();
        firebaseClient.savedata("pr");
        Toast.makeText(this, "you hear", Toast.LENGTH_LONG).show();
    }//crert

}
