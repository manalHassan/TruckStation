package truckstationsa.truckstation;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

public class viewCart extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener  {


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    DatabaseReference myRef2 = database.getReference("Pre-order");
    DatabaseReference cartRef ;
    String cid = "";
    String mid = "";
    ListView listViewArtists;
    ArrayList<cartItem> artists;
    FirebaseAuth firebaseAuth;
    String id="";
    cart car ;
    private int i = 0 ;
    cartArray artistAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_cart_drawer);
        cartRef = database.getReference("Cart");
        //TextView view =(TextView) findViewById(R.id.foodmenu);
        //view.setText(cid);
        listViewArtists = (ListView) findViewById(R.id.listviewtracksNew1);
        //list to store artists
        artists = new ArrayList<cartItem>();
        firebaseAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listViewArtists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                cartItem artist = artists.get(position);

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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
//
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);   }


    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth = FirebaseAuth.getInstance();
        //attaching value event listener
        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // String id = user.getUid();//customer id is the same as rating id to make it easy to refer
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        String id = user.getUid();
        myRef.child("Cart").child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             //   mid = dataSnapshot.child("Cart").getValue(String.class);
               // firebaseAuth = FirebaseAuth.getInstance();
              //  myRef.child("Cart").child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
                 //   @Override
                  //  public void onDataChange(DataSnapshot dataSnapshot) {

                        artists.clear();

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            cartItem artist = new cartItem();//ds.getValue(Item.class)
                            artist.setCatItem(ds.getValue(cartItem.class).getCatItem());
                            artist.setPrice1(ds.getValue(cartItem.class).getPrice1());
                            artist.setFid(ds.getValue(cartItem.class).getFid());
                            artists.add(artist);
                        }

                       if(artists.size()==0)
                           Toast.makeText(viewCart.this,"اضيف لسلتك" ,Toast.LENGTH_SHORT).show();
                        ////creating adapter
                        artistAdapter = new cartArray(viewCart.this, (ArrayList<cartItem>) artists);
                        //attaching adapter to the listview
                        listViewArtists.setAdapter(artistAdapter);
                  //  }

                   // @Override
                   // public void onCancelled(DatabaseError databaseError) {

                   // }
             //   });

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }
    public void preorder (View view){
        for (int i =0 ; i<artists.size() ; i ++){
            cartItem item = artists.get(i);
            String itemId = myRef2.push().getKey();
           myRef2.child(firebaseAuth.getUid()).child(item.getFid()).child(itemId).setValue(item.getCatItem());
            Toast.makeText(viewCart.this, "تم ارسال طلبك:)",Toast.LENGTH_SHORT).show();
            myRef.child("Cart").child(firebaseAuth.getUid()).removeValue();
        }
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
                startActivity(new Intent(this , MainActivity.class));
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;

            }}
        else if (id == R.id.cart) {


            Intent intent = new Intent(this, viewCart.class);
            Bundle b=new Bundle();
            //  b.putString("id",user);
            //  intent.putExtras(b);
            startActivity(intent);
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;

        }

        return false;
    }
}
