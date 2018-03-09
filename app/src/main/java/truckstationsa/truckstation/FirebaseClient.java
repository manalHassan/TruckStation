package truckstationsa.truckstation;

/**
 * Created by amerah on 2/12/2018 AD.
 */
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.*;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//////////////////////////////////
public class FirebaseClient extends AppCompatActivity  {

    Context c;
    String DB_URL;
    ListView listView;
    Firebase firebase;
    ArrayList<PublicFoodTruckOwner> dogies= new ArrayList<>();
    CustomAdapter customAdapter;
    ArrayList<PrivateFoodTruckOwner> dogies1= new ArrayList<>();
    CustomAdapterPrivate customAdapter1;
    DatabaseReference f;


    public  FirebaseClient(Context c, String DB_URL,ListView listView)
    {
        this.c= c;
        this.listView= listView;
        this.DB_URL= DB_URL;

        Firebase.setAndroidContext(c);

    }

    public  void savedata(String name) {
        //Dog d= new Dog();
        // d.setName(name);
        //d.setUrl(url);

        // f.child("dog").push().setValue(d);
        if (name.equals("pu")) {
            f = FirebaseDatabase.getInstance().getReference().child("PublicFoodTruckOwner");

            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getChildren() == null) {
                        Toast.makeText(c, "no trucks", Toast.LENGTH_SHORT).show();

                        //Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(getActivity(), AdminHome2.class));
                    }
                    dogies.clear();

                    for (com.google.firebase.database.DataSnapshot ds : dataSnapshot.getChildren()) {
                        PublicFoodTruckOwner d = new PublicFoodTruckOwner();
                        d.setUid(""+(ds.getValue(PublicFoodTruckOwner.class).getUid()));
                        d.setFUsername(""+(ds.getValue(PublicFoodTruckOwner.class).getFUsername()));
                        d.setUrl(ds.getValue(PublicFoodTruckOwner.class).getUrl());
                        d.setQusins(ds.getValue(PublicFoodTruckOwner.class).getQusins());

                        dogies.add(d);

                    }
                    if (dogies.size() > 0) {
                        customAdapter = new CustomAdapter(c, dogies);
                        listView.setAdapter((ListAdapter) customAdapter);
                    } else {
                        Toast.makeText(c, "No data", Toast.LENGTH_SHORT).show();
                    }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(c, "cancelled", Toast.LENGTH_SHORT).show();
                }
            };
            f.addListenerForSingleValueEvent(eventListener);

        }else if (name.equals("pr")) {
            f = FirebaseDatabase.getInstance().getReference().child("PrivateFoodTruckOwner");

            ValueEventListener eventListener = new ValueEventListener() {
                @Override
                public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getChildren() == null) {
                        Toast.makeText(c, "no trucks", Toast.LENGTH_SHORT).show();

                        //Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
                        //startActivity(new Intent(getActivity(), AdminHome2.class));
                    }
                    dogies1.clear();

                    for (com.google.firebase.database.DataSnapshot ds : dataSnapshot.getChildren()) {
                        PrivateFoodTruckOwner d= new PrivateFoodTruckOwner();
                        d.setUid(""+(ds.getValue(PrivateFoodTruckOwner.class).getUid()));
                        d.setFUsername(ds.getValue(PrivateFoodTruckOwner.class).getFUsername());
                        d.setUrl(ds.getValue(PrivateFoodTruckOwner.class).getUrl());
                        d.setQusins(ds.getValue(PrivateFoodTruckOwner.class).getQusins());
                        dogies1.add(d);

                    }
                    if (dogies1.size() > 0) {
                        customAdapter1 = new CustomAdapterPrivate(c, dogies1);
                        listView.setAdapter((ListAdapter) customAdapter1);
                    } else {
                        Toast.makeText(c, "No data", Toast.LENGTH_SHORT).show();
                    }



                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(c, "cancelled", Toast.LENGTH_SHORT).show();
                }
            };
            f.addListenerForSingleValueEvent(eventListener);

        }//if private
    }

/////////////////
    public ListView reListView(){return  listView;}

    public ArrayList<PrivateFoodTruckOwner> getDogies1(){return  dogies1;}
    public CustomAdapterPrivate custom(){return  customAdapter1;}
    public ArrayList<PublicFoodTruckOwner> getDogies(){return  dogies;}
    public CustomAdapter customPublic(){return  customAdapter;}
    public Context contes(){return  c;}


    //////////////////////

}
