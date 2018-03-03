package truckstationsa.truckstation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by wafaa7maD on 20/02/18.
 */

public class itemlist extends AppCompatActivity {


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    String id = "-L5hPscXmLNDYe8_3KNL";
   String cid="";
    String mid="";
    ListView listViewArtists;
    List<Item> artists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_list);

       Bundle b = getIntent().getExtras();
        cid = b.getString("cid");
        //TextView view =(TextView) findViewById(R.id.foodmenu);
        //view.setText(cid);

        listViewArtists = (ListView) findViewById(R.id.listViewTracks);
        //list to store artists
        artists = new ArrayList<>();

        listViewArtists.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item artist = artists.get(i);
                //   String
                Intent intent = new Intent(itemlist.this, edititem.class);
                Bundle b=new Bundle();
                b.putString("tid",artist.getItemID());
                intent.putExtras(b);
                startActivity(intent);
           return true;
            }
        });

    }

    public void additem(View view) {

        String tid =myRef.push().getKey();
        Item t1= new Item ("برجر لحم" ,"http://www.overcaffeinated.org/sites/default/files/styles/large/public/products/tazo_black_shaken_iced_tea.jpg?itok=u6CezagO" ,"مثجلجات بنكهة الشاي" , 23  ,cid , tid );
        myRef.child("Item").child(mid).child(cid).child(tid).setValue(t1);
        Toast.makeText(itemlist.this, "تمت اللإضافة", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener

        myRef.child("Menu").child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mid = dataSnapshot.child("mid").getValue(String.class);
                myRef.child("Item").child(mid).child(cid).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        artists.clear();

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                           Item artist =new Item(ds.getValue(Item.class));
                            artists.add(artist);
                        }


                        //creating adapter
                        Itemarray artistAdapter = new Itemarray(itemlist.this, artists);
                        //attaching adapter to the listview
                        listViewArtists.setAdapter(artistAdapter);
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
        //start