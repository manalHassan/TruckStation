package truckstationsa.truckstation;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MyMain extends AppCompatActivity {

    final static String DB_URL = "https://truckstation-3c3eb.firebaseio.com/";
    EditText nameeditText, urleditText;
    Button btnsave;
    ListView listView;
    FirebaseClient firebaseClient;

//nmnmm
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_main);
       // Toast.makeText(this, "hi f1", Toast.LENGTH_SHORT).show();
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);

        listView = findViewById(R.id.listview);
        firebaseClient = new FirebaseClient(this,DB_URL, listView);
      //  firebaseClient.refreshdata();
        firebaseClient.savedata("ICECREM", "https://res.cloudinary.com/dlxwhpn3p/image/upload/t_media_lib_thumb/v1518470168/C5lvQRqXEAI11zy.jpg-large.jpg");
        Toast.makeText(this,"you hear",Toast.LENGTH_LONG).show();
       // https://res.cloudinary.com/dlxwhpn3p/image/upload/t_media_lib_thumb/v1518470168/C5lvQRqXEAI11zy.jpg-large.jpg
        //http://res.cloudinary.com/mrudula/image/upload/v1495808239/dog9_xyjjfb.jpg"
        //https://res.cloudinary.com/dlxwhpn3p/image/upload/t_media_lib_thumb/v1518472141/skBVf8mx_400x400.jpg


       // Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();

    }



}
