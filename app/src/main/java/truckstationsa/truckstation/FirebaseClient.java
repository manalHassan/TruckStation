package truckstationsa.truckstation;

/**
 * Created by amerah on 2/12/2018 AD.
 */

import android.content.Context;
import android.content.Intent;
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


public class FirebaseClient  {

    Context c;
    String DB_URL;
    ListView listView;
    Firebase firebase;
    ArrayList<PublicFoodTruckOwner> dogies= new ArrayList<>();
    CustomAdapter customAdapter;
    DatabaseReference f;


    public  FirebaseClient(Context c, String DB_URL,ListView listView)
    {
        this.c= c;
        this.listView= listView;
        this.DB_URL= DB_URL;

        Firebase.setAndroidContext(c);
        //firebase=new Firebase(DB_URL);
    }

    public  void savedata(String name, String url)
    {
        //Dog d= new Dog();
       // d.setName(name);
        //d.setUrl(url);

       // f.child("dog").push().setValue(d);
       f= FirebaseDatabase.getInstance().getReference().child("PublicFoodTruckOwner");

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
                    PublicFoodTruckOwner d= new PublicFoodTruckOwner();
                        d.setFUsername(ds.getValue(PublicFoodTruckOwner.class).getFUsername());
                        d.setUrl(ds.getValue(PublicFoodTruckOwner.class).getUrl());
                        dogies.add(d);

                    }
                    if(dogies.size()>0)
                    {
                        customAdapter=new CustomAdapter(c, dogies);
                        listView.setAdapter((ListAdapter) customAdapter);
                    }else
                    {
                        Toast.makeText(c, "No data", Toast.LENGTH_SHORT).show();
                    }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(c, "cancelled", Toast.LENGTH_SHORT).show();
            }
        };
        f.addListenerForSingleValueEvent(eventListener);

    }

    /*
    public  void refreshdata()
    {
        f.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getupdates(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getupdates(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }*/



}
