package truckstationsa.truckstation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manal on 3/18/2018.
 */

public class viewCart extends AppCompatActivity {
    ////////////////


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference cartRef ;
    String cid = "";
    String mid = "";
    ListView listViewArtists;
    ArrayList<Item> artists;
    FirebaseAuth firebaseAuth;
    String id="";
    cart car ;
    private int i = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.public_tab3_menuitemforcustomeritem);
        cartRef = database.getReference("cart");
        //TextView view =(TextView) findViewById(R.id.foodmenu);
        //view.setText(cid);
        listViewArtists = (ListView) findViewById(R.id.listviewtracks);
        //list to store artists
        artists = new ArrayList<Item>();
        listViewArtists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Item artist = artists.get(position);
                firebaseAuth = FirebaseAuth.getInstance();
                //  Toast.makeText(ListPuplic.this, "ID="+artist.getUid()+"." ,Toast.LENGTH_SHORT).show();
                //   if (car == null){
                //car = new cart ();
              //  cartItem cartitem = new cartItem(artist.getIName() , artist.getIPrice() );
               /// cartRef.child(firebaseAuth.getUid()).child("item").child((i++)+"").setValue(cartitem);
                //Toast.makeText(tab3_itemlistforcustomer.this,artist.getIName()+"اضيف لسلتك" ,Toast.LENGTH_SHORT).show();
                //    String s =  artist.getIName();
                //  }
                //  else {
                // car.setQuntity(1);
                //  cartRef.child("cart").updateChildren(car);
                //  }Toast.makeText(tab3_itemlistforcustomer.this,artist.getIName()+"اخر اضيف لسلتك" ,Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // String id = user.getUid();//customer id is the same as rating id to make it easy to refer
        myRef.child("Menu").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mid = dataSnapshot.child("cart").getValue(String.class);
                firebaseAuth = FirebaseAuth.getInstance();
                myRef.child(firebaseAuth.getUid()).child("item").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        artists.clear();

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            Item artist = new Item(ds.getValue(Item.class));
                            artists.add(artist);
                        }


                        if (artists.size() > 0) {
                            Itemarrayforcustomer artistAdapter = new Itemarrayforcustomer(viewCart.this, artists);
                            listViewArtists.setAdapter((ListAdapter)artistAdapter);



                        } else {
                            Toast.makeText(viewCart.this, "No data", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }
}
